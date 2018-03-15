package vk.basic

import play.api.libs.json.{JsPath, Reads}
import vk.media.VkMedia

/**
  * Describes fields (not all) of the post on the wall
  *
  * @param id post identifier on the wall
  * @param fromId author identifier
  * @param createdBy administrator identifier who posted the post
  * @param date date (in Unixtime) the post has been added
  * @param text post text
  * @param attachments information about attachments to the post (photos,
  *                    links, etc.), if any
  */
final case class VkWallPost(
                          id: Int,
                          fromId: Int,
                          createdBy: Option[Int],
                          date: Int,
                          text: String,
                          attachments: Seq[VkMedia]
                         )

object VkWallPost {
  import vk.media.VkMedia

  implicit val vkWallPostReads: Reads[VkWallPost] = for {
    id <- (JsPath \ "id").read[Int]
    fromId <- (JsPath \ "from_id").read[Int]
    createdBy <- (JsPath \ "created_by").readNullable[Int]
    date <- (JsPath \ "date").read[Int]
    text <- (JsPath \ "text").read[String]
    attachments <- (JsPath \ "attachments").read[Seq[VkMedia]]
  } yield VkWallPost(id, fromId, createdBy, date, text, attachments)
}
