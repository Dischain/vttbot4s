import info.mukel.telegrambot4s.methods.ParseMode
import telegram.VTTTelegramBot
import vk.VkApi
import vk.response.{ApiResponse, VkApiFailure, WallGetResponse}

import scala.concurrent.Future

object Test extends App {
  import util.Utils._
  override def main(args: Array[String]): Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global

    lazy val vkToken = scala.util.Properties
      .envOrNone("vk_token")
      .get

    val vk = new VkApi(vkToken)

    lazy val telegramToken = scala.util.Properties
      .envOrNone("telegram_token")
      .get

    val tb = new VTTTelegramBot(telegramToken)

    val resp: Future[ApiResponse] = vk.wall.get("kometa_tuning_simferopol", Some(2), Some(1))
    resp map {
      case r: WallGetResponse =>
        r.items map { item =>
          println(formPost(item, "kometa_tuning_simferopol"))
          tb.sendMessage("@vttbottest",
            formPost(item, "kometa_tuning_simferopol"),
            Some(ParseMode.HTML))
        }
      case VkApiFailure => ???
    }
  }
}
