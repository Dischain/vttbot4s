package vk.media

/**
  * Represents (not all) fields of Vk wiki `Page` object
  *
  * @param id       `Int` page id
  * @param title    `String` page title
  * @param html     `Optional String` page text in HTML format (if requested)
  * @param view_url `String` URL of the preview
  */
final case class VkPage(
                       id: Int,
                       title: String,
                       html: Option[String],
                       view_url: String
                       ) extends VkMedia

object VkPage {
  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  implicit val vkPageReads: Reads[VkPage] = (
    (JsPath \ "id").read[Int] and
      (JsPath \ "title").read[String] and
      (JsPath \ "html").readNullable[String] and
      (JsPath \ "view_url").read[String]
    )(VkPage.apply _)
}