package actor

import actor.messages.{LookupFailure, LookupNewPost, PostReceived}
import akka.actor.{Actor, ActorLogging}
import vk.VkApi
import vk.response.{VkApiFailure, WallGetResponse}

class VkActor (vk: VkApi) extends Actor with ActorLogging{
  import scala.concurrent.ExecutionContext.Implicits.global

  def receive: Actor.Receive = {
    case LookupNewPost =>
      val s = sender()
      vk.wall
        .get(vk.domain, Some(1), Some(1))
        .foreach {
          case r: WallGetResponse => r.items foreach { item =>
            log.info(s"received vk post with id ${item.id}")
            s ! PostReceived(item, vk.domain)
          }

          case VkApiFailure(msg) =>
            log.error(s"failed to fetch post: $msg")
            s ! LookupFailure
        }
  }
}