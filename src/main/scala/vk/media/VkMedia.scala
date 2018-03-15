package vk.media

import io.leonard.TraitFormat

/**
  * Trait for information about media attachment
  */
trait VkMedia {
  val mediaType: String
}

object VkMedia {
  import io.leonard.TraitFormat.traitFormat
  import play.api.libs.json.Json.format

  implicit val vkMediaFormat: TraitFormat[VkMedia] = traitFormat[VkMedia] <<
    format[VkPhoto] << format[VkDocument] << format[VkVideo]
}