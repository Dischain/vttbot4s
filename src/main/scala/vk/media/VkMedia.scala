package vk.media

import play.api.libs.json._

/**
  * Trait for information about media attachment
  */
trait VkMedia

object VkMedia {

  implicit val vkMediaReads: Reads[VkMedia] = (json: JsValue) =>
    (json \ "type").get match {
      case JsString("photo") => VkPhoto.vkPhotoReads.reads((json \ "photo").get)
      case JsString("video") => VkVideo.vkVideoReads.reads((json \ "video").get)
      case JsString("document") => VkDocument.vkDocumentReads.reads((json \ "document").get)
      case JsString("page") => VkPage.vkPageReads.reads((json \ "page").get)
      case unsupported @ _ => throw new Exception(s"Unsupported media type: $unsupported")
    }

}