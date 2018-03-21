import actor.messages.Tick
import actor.{MainActor, TelegramActor, VkActor, VtTScheduler}
import akka.actor.{ActorSystem, Props}
import info.mukel.telegrambot4s.methods.ParseMode
import telegram.VtTTelegramBot
import vk.VkApi
import vk.response.{ApiResponse, VkApiFailure, WallGetResponse}

import scala.concurrent.Future

object Test extends App {
  import util.Utils._
  override def main(args: Array[String]): Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global
    import scala.concurrent.duration._

    lazy val vkToken = scala.util.Properties
      .envOrNone("vk_token")
      .get

    lazy val vkDomain = scala.util.Properties
      .envOrNone("vk_domain")
      .get

    val vk = new VkApi(vkToken, vkDomain)

    lazy val telegramToken = scala.util.Properties
      .envOrNone("telegram_token")
      .get

    lazy val telegramDomain = scala.util.Properties
      .envOrNone("telegram_domain")
      .get

    val tb = new VtTTelegramBot(telegramToken, telegramDomain)

    val system = ActorSystem("vttbot")

    val mainActor = system.actorOf(Props(new MainActor(vk, tb)))

    mainActor ! "start"
    /*val vkActor = system.actorOf(Props(new VkActor(vk)))
    val telegramActor = system.actorOf(Props(new TelegramActor(tb)))
    val vttScheduler = system.actorOf(Props(new VtTScheduler(vkActor, telegramActor)))

    system.scheduler.scheduleOnce(1.second, vttScheduler, Tick)*/

    /*val resp: Future[ApiResponse] = vk.wall.get("kometa_tuning_simferopol", Some(2), Some(1))
    resp map {
      case r: WallGetResponse =>
        r.items map { item =>
          println(formPost(item, "kometa_tuning_simferopol"))
          tb.sendMessage(formPost(item, "kometa_tuning_simferopol"), Some(ParseMode.HTML))
        }
      case VkApiFailure => ???
    }*/
  }
}
