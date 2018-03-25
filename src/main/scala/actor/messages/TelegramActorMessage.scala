package actor.messages

import vk.basic.VkWallPost

sealed trait TelegramActorMessage

case class TelegramSendMessage(post: VkWallPost, vkDomain: String) extends TelegramActorMessage
