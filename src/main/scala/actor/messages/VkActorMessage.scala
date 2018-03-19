package actor.messages

sealed trait VkActorMessage

case object LookupNewPost extends VkActorMessage

case object LookupFailure extends VkActorMessage