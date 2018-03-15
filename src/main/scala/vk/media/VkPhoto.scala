package vk.media

/**
  * Represents (not all) fields of Vk `Photo` object
  *
  * @param id photo identifier
  * @param text text of description
  * @param date date (in Unixtime) the photo has been added
  * @param photo_75 `Optional String` of the copy of the photo with a maximum size of 75x75px
  * @param photo_130 `Optional String` of the copy of the photo with a maximum size of 130x130
  * @param photo_604 `Optional String` of the copy of the photo with a maximum size of 604x604
  * @param photo_807 `Optional String` of the copy of the photo with a maximum size of 807x807
  * @param photo_1280 `Optional String` of the copy of the photo with a maximum size of 1280x1024
  * @param photo_2560 `Optional String` of the copy of the photo with a maximum size of  2560x2048
  */
final case class VkPhoto(
                          id: Int,
                          text: String,
                          date: Int,
                          photo_75: Option[String],
                          photo_130: Option[String],
                          photo_604: Option[String],
                          photo_807: Option[String],
                          photo_1280: Option[String],
                          photo_2560: Option[String],
                          mediaType: String = "photo"
                        ) extends VkMedia

object VkPhoto {
  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  implicit val vkPhotoReads: Reads[VkPhoto] = (
    (JsPath \ "id").read[Int] and
    (JsPath \ "text").read[String] and
    (JsPath \ "date").read[Int] and
    (JsPath \ "photo_75").readNullable[String] and
    (JsPath \ "photo_130").readNullable[String] and
    (JsPath \ "photo_604").readNullable[String] and
    (JsPath \ "photo_807").readNullable[String] and
    (JsPath \ "photo_1280").readNullable[String] and
    (JsPath \ "photo_2560").readNullable[String] and
    (JsPath \ "type").read[String]
  )(VkPhoto.apply _)
}