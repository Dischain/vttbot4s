package vk.media

/**
  * Represents (not all) fields of Vk `Document` object
  *
  * @param id    `Int` document identifier
  * @param title `String` document title
  * @param size  `Int` document size in Bytes
  * @param ext   `String` document extension
  * @param url   `String` loadable url
  * @param date  `Int` date (in Unixtime) the document has been added
  */
final case class VkDocument(
                           id: Int,
                           title: String,
                           size: Int,
                           ext: String,
                           url: String,
                           date: Int
                           ) extends VkMedia

object VkDocument {
  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  implicit val vkDocumentReads: Reads[VkDocument] = (
    (JsPath \ "id").read[Int] and
    (JsPath \ "title").read[String] and
    (JsPath \ "size").read[Int] and
    (JsPath \ "ext").read[String] and
    (JsPath \ "url").read[String] and
    (JsPath \ "date").read[Int]
  )(VkDocument.apply _)
}