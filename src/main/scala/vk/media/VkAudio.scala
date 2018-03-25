package vk.media

/**
  * Represents (not all) fields of Vk `Audio` object
  *
  * @param id `Int` audio id
  * @param artist `String` artist name
  * @param title `String`
  * @param url
  * @param date
  */
case class VkAudio(
                  id: Int,
                  artist: String,
                  title: String,
                  url: String,
                  date: Int
                  ) extends VkMedia

object VkAudio {
  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  implicit val vkAudioReads: Reads[VkAudio] = (
    (JsPath \ "id").read[Int] and
    (JsPath \ "artist").read[String] and
    (JsPath \ "title").read[String] and
    (JsPath \ "url").read[String] and
    (JsPath \ "date").read[Int]
    )(VkAudio.apply _)
}
