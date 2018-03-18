package vk.api

import vk.methods._
import play.api.libs.json.Json.JsValueWrapper

import scala.concurrent.Future

import play.api.libs.ws.ahc._
import play.api.libs.json._
import play.api.libs.ws.{BodyWritable, InMemoryBody}

import akka.util.ByteString

/**
  * Facilitates requests to vk api
  *
  * @param accessToken `String` vk access token
  * @param vkApiHost `String` host
  * @param apiVersion `Int` api version
  */
final class RequestHandler(
                            accessToken: String,
                            vkApiHost: String = "api.vk.com",
                            apiVersion: String = "5.73"
                          )
{
  private[this] val apiBaseUrl = s"https://$vkApiHost/method/"

  private[this] def camelToUnderscore(name: String): String =
    "[A-Z\\d]".r.replaceAllIn(name, { m => "_" + m.group(0).toLowerCase() })

  private[this] def unwrapToString(obj: Any): String = obj match {
    case Some(inner) => unwrapToString(inner)
    case _ => obj.toString
  }

  private[this] def unwrapToJson(obj: Any): JsValueWrapper = obj match {
    case Some(inner) => unwrapToJson(inner)
    case _ => JsString(obj.toString)
  }

  private[this] def fieldsToKVStringSeq(req: ApiRequest) =
    req.getClass.getDeclaredFields.map { field =>
      field.setAccessible(true)
      val name = field.getName
      val value = field.get(req)
      (camelToUnderscore(name), unwrapToString(value))
    }.filterNot{ p =>
      p._2 == "None" || p._1 == "method_name" || p._1 == "with_access_token"
    }.toSeq

  private[this] def fieldsToKVJsonSeq(req: ApiRequest) =
    req.getClass.getDeclaredFields.map { field =>
      val name = field.getName
      val value = field.get(req)
      (camelToUnderscore(name), unwrapToJson(value))
    }.filterNot{ p => p._2 == JsString("None") && p._1 == "method_name"}.toSeq

  def apply(req: ApiRequest): Future[JsValue] = {
    import akka.actor.ActorSystem
    import akka.stream.ActorMaterializer
    import scala.concurrent.ExecutionContext.Implicits.global

    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()

    implicit val bw = BodyWritable[JsObject]({ obj =>
      val s = Json.stringify(obj)
      InMemoryBody(ByteString.fromString(s))
    }, "application/json")

    val methodName = req.methodName
    val initialQSParams = (if (req.withAccessToken) {
      Seq("access_token" -> accessToken)
    } else {
      Seq()
    }) ++ Seq("v" -> apiVersion)

    val ws = StandaloneAhcWSClient()
      .url(s"$apiBaseUrl$methodName")
      .addQueryStringParameters(initialQSParams: _*)

    req match {
      case r: ApiRequestQS =>
        ws.addQueryStringParameters(fieldsToKVStringSeq(r): _*)
          .get() map { response => Json.parse(response.body)}

      case r: ApiRequestJson =>
        ws.post(Json.obj(fieldsToKVJsonSeq(r): _*)) map { response =>
          Json.parse(response.body)
        }
    }
  }
}