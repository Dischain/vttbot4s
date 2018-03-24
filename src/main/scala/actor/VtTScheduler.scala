package actor

import akka.actor.{Actor, ActorLogging, ActorRef}

import scala.concurrent.duration._
import actor.messages._
import util.KVStoreHandler
import vk.basic.VkWallPost

class VtTScheduler (
                     vkActor: ActorRef,
                     telegramActor: ActorRef,
                     delay: Int
                   ) extends Actor with ActorLogging
{
  import scala.concurrent.ExecutionContext.Implicits.global

  val kvh = new KVStoreHandler("kv_store")

  var lastVkPostId: Int = _

  kvh.readValue("id") map { value => lastVkPostId = value.getOrElse("-1").toInt }

  def receive: Actor.Receive = {
    case Tick => vkActor ! LookupNewPost
    case PostReceived(post: VkWallPost, vkDomain: String) =>
      if (lastVkPostId != post.id) {
        telegramActor ! TelegramSendMessage(post, vkDomain)
      } else {
        schedule()
      }
    case LookupFailure => schedule()
    case UpdateLastPostId(id: Int) =>
      kvh.storeValue("id", id) map { _ =>
        lastVkPostId = id
        schedule()
      }
  }

  private[this] def schedule() = {
    context.system.scheduler.scheduleOnce(delay.seconds, self, Tick)
  }
}
