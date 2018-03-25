package vk.methods

trait VkApiRequest {
  val methodName: String
  val withAccessToken: Boolean
}
