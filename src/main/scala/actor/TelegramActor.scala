package actor

import actor.messages.{TelegramSendMessage, Tick, UpdateLastPostId}
import akka.actor.{Actor, ActorLogging}
import info.mukel.telegrambot4s.methods.ParseMode
import info.mukel.telegrambot4s.models.Message
import telegram.VtTTelegramBot
import vk.basic.VkWallPost

class TelegramActor (tb: VtTTelegramBot) extends Actor with ActorLogging{
  import util.Utils._

  def receive: Actor.Receive = {
    case TelegramSendMessage(post: VkWallPost, vkDomain: String) =>
      val s = sender()
      import scala.concurrent.ExecutionContext.Implicits.global

      tb.sendMessage(formPost(post, vkDomain), Some(ParseMode.HTML))
        .foreach {
          case m: Message =>
            log.info(s"message ${post.id} sent")
            s ! UpdateLastPostId(post.id)
          case _ =>
            log.error(s"message ${post.id} not sent")
            s ! Tick
        }
  }
}
