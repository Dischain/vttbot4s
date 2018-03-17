package vk.media

/**
  * Represents (not all) fields of Vk `Video` object
  *
  * @param id          `Int` video identifier
  * @param title       `String` owner identifier
  * @param description `String` description
  * @param duration    `Int` duration
  * @param photo_130 `Optional String` of the image of the movie cover with a size of 130x98
  * @param photo_320 `Optional String` of the image of the movie cover with a size of 320x240
  * @param photo_640 `Optional String` of the image of the movie cover with a size of 640x480
  * @param photo_800 `Optional String` of the image of the movie cover with a size of 800x450
  * @param date date `Int` (in Unixtime) the video has been added
  */
final case class VkVideo(
                           id: Int,
                           title: String,
                           description: String,
                           duration: Int,
                           photo_130: Option[String],
                           photo_320: Option[String],
                           photo_640: Option[String],
                           photo_800: Option[String],
                           photo_1280: Option[String],
                           photo_2560: Option[String],
                           date: Int
                         ) extends VkMedia

object VkVideo {
  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  implicit val vkVideoReads: Reads[VkVideo] = (
    (JsPath \ "id").read[Int] and
    (JsPath \ "title").read[String] and
    (JsPath \ "description").read[String] and
    (JsPath \ "duration").read[Int] and
    (JsPath \ "photo_130").readNullable[String] and
    (JsPath \ "photo_320").readNullable[String] and
    (JsPath \ "photo_640").readNullable[String] and
    (JsPath \ "photo_800").readNullable[String] and
    (JsPath \ "photo_1280").readNullable[String] and
    (JsPath \ "photo_2560").readNullable[String] and
    (JsPath \ "date").read[Int]
  )(VkVideo.apply _)
}
