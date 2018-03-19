package vk.api

import play.api.libs.json.{JsError, JsSuccess, Json}
import vk.methods.WallGet
import vk.response.{ApiResponse, VkApiFailure, WallGetResponse}

import scala.concurrent.{ExecutionContext, Future}

/**
  * Api for `wall` vk object
  *
  * @param rh `RequestHandle` request handler
  */
final class WallApi(rh: RequestHandler) {
  /**
    * Returns a list of posts on a user wall or community wall
    *
    * @param domain `String` user or community short address
    * @param count `Optional Int` number of posts to return (maximum 100)
    * @param offset `Optional Int` offset needed to return a specific
    *              subset of posts
    * @param ec `ExecutionContext` execution context
    * @return `Future ApiResponse`
    */
  def get(domain: String,
          count: Option[Int] = None,
          offset: Option[Int] = None)(implicit ec: ExecutionContext): Future[ApiResponse] =
  {
    implicit val vgr = WallGetResponse.wallGetRespReads

    rh(WallGet(domain, count, offset))
      .map { response => Json.fromJson(response) }
      .map {
      case JsSuccess(resp: WallGetResponse, _) => resp
      case e: JsError => VkApiFailure(e.errors.mkString(" "))
    }
  }
}
