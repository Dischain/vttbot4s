package actor

import actor.messages.{LookupFailure, LookupNewPost, PostReceived}
import akka.actor.Actor
import vk.VkApi
import vk.response.{VkApiFailure, WallGetResponse}

class VkActor (vk: VkApi) extends Actor {
  import scala.concurrent.ExecutionContext.Implicits.global

  def receive: Actor.Receive = {
    case LookupNewPost =>
      val s = sender()
      println("VkActor: LookupNewPost")
      vk.wall
        .get(vk.domain, Some(1), Some(1))
        .foreach {
          case r: WallGetResponse => r.items foreach { item =>
              println("VkActor: LookupNewPost: " + item)
              s ! PostReceived(item)
          }

          case VkApiFailure(msg) =>
            println("VkActor: VkApiFailure " + msg)
            s ! LookupFailure
        }
  }
}