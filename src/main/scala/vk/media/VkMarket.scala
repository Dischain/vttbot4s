package vk.media

/**
  * Represents (not all) fields of Vk `market` object
  *
  * @param id `String` item id
  * @param title `String` item title
  * @param description `String` item description
  */
final case class VkMarket (
                          id: Int,
                          title: String,
                          description: String
                          ) extends VkMedia

object VkMarket {
  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  implicit val vkMarketReads: Reads[VkMarket] = (
    (JsPath \ "id").read[Int] and
    (JsPath \ "title").read[String] and
    (JsPath \ "description").read[String]
  )(VkMarket.apply _)
}