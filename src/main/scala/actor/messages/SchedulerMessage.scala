package actor.messages

import vk.basic.VkWallPost

sealed trait SchedulerMessage

case object Tick extends SchedulerMessage

case class PostReceived(post: VkWallPost, vkDomain: String) extends SchedulerMessage

case class UpdateLastPostId(id: Int) extends SchedulerMessage