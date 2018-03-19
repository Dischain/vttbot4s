package actor

import actor.messages.{TelegramSendMessage, Tick, UpdateLastPostId}
import akka.actor.Actor
import info.mukel.telegrambot4s.methods.ParseMode
import info.mukel.telegrambot4s.models.Message
import telegram.VtTTelegramBot
import vk.basic.VkWallPost

class TelegramActor (tb: VtTTelegramBot) extends Actor {
  import util.Utils._
  import scala.concurrent.ExecutionContext.Implicits.global

  def receive: Actor.Receive = {
    case TelegramSendMessage(post: VkWallPost) =>
      val s = sender()
      println("TelegramActor: TelegramSendMessage: " + post)
      tb.sendMessage(formPost(post, tb.chatId), Some(ParseMode.HTML))
        .foreach {
          case m: Message => s ! UpdateLastPostId(post.id)
          case _ => s ! Tick
        }
  }
}
