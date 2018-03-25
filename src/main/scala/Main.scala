import actor.MainActor
import akka.actor.{ActorSystem, Props}
import akka.stream.ActorMaterializer
import telegram.VtTTelegramBot
import vk.VkApi

object Main extends App {
  override def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("vttbot")
    implicit val materializer = ActorMaterializer()

    lazy val vkToken = scala.util.Properties
      .envOrNone("vk_token")
      .get

    lazy val vkDomain = scala.util.Properties
      .envOrNone("vk_domain")
      .get

    lazy val telegramToken = scala.util.Properties
      .envOrNone("telegram_token")
      .get

    lazy val telegramDomain = scala.util.Properties
      .envOrNone("telegram_domain")
      .get

    lazy val period = scala.util.Properties
      .envOrNone("period")
      .get.toInt

    val vk = new VkApi(vkToken, vkDomain)
    val tb = new VtTTelegramBot(telegramToken, telegramDomain)

    val mainActor = system.actorOf(Props(new MainActor(vk, tb, period)))

    mainActor ! "start"
  }
}
