package vk.methods

trait ApiRequest {
  val methodName: String
  val withAccessToken: Boolean
}
