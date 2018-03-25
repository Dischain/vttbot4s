package vk

import akka.actor.ActorSystem
import akka.stream.Materializer
import vk.api.{RequestHandler, WallApi}

/**
  * Basic vk api class. Use it for all api method calls.
  * Example usage:
  * {{{
  *   vk.wall.get("kometa_tuning_simferopol", Some(2), Some(1)) match {
  *     case r: WallGetResponse =>
  *       r.items forEach { item =>
  *         doSomething(item)
  *       }
  *     case VkApiFailure(msg: String) => logger.log(msg)
  *   }
  * }}}
  *
  * @param token `String` service token
  */
final class VkApi(val token: String, val domain: String)
                 (implicit system: ActorSystem, materializer: Materializer)
{
  val request = new RequestHandler(token)

  def wall = new WallApi(request)
}
