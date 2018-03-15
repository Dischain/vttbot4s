package vk.media

/**
  * Represents (not all) fields of Vk `Object` object
  *
  * @param id document identifier
  * @param title document title
  * @param size document size in Bytes
  * @param ext document extension
  * @param url loadable url
  * @param date date (in Unixtime) the document has been added
  */
final case class VkDocument(
                           id: Int,
                           title: String,
                           size: Int,
                           ext: String,
                           url: String,
                           date: Int,
                           mediaType: String = "document"
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
    (JsPath \ "date").read[Int] and
    (JsPath \ "type").read[String]
  )(VkDocument.apply _)
}