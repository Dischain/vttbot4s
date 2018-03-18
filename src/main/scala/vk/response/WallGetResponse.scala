package vk.response

import vk.basic.VkWallPost

/**
  * Describes fields (not all) of `wall.get` method
  *
  * @param count `Int` count
  * @param items `Seq[VkWallPost]` sequence of wall post objects
  */
final case class WallGetResponse(count: Int, items: Seq[VkWallPost]) extends ApiResponse

object WallGetResponse {
  import play.api.libs.json._
  import play.api.libs.functional.syntax._
  import vk.basic.VkWallPost

  implicit val wallGetRespReads: Reads[WallGetResponse] = (
    (JsPath \ "response" \ "count").read[Int] and
    (JsPath \ "response" \ "items").read[Seq[VkWallPost]]
  )(WallGetResponse.apply _)
}
