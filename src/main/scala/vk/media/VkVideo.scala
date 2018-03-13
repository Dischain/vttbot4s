package vk.media

import java.net.URL

/**
  * Represents (not all) fields of Vk `Video` object
  *
  * @param id video identifier
  * @param title owner identifier
  * @param description description
  * @param duration duration
  * @param photo_130 URL of the image of the movie cover with a size of 130x98
  * @param photo_320 URL of the image of the movie cover with a size of 320x240
  * @param photo_640 URL of the image of the movie cover with a size of 640x480
  * @param photo_800 URL of the image of the movie cover with a size of 800x450
  * @param date date (in Unixtime) the video has been added
  */
final case class VkVideo(
                           id: Int,
                           title: String,
                           description: String,
                           duration: Int,
                           photo_130: Option[URL],
                           photo_320: Option[URL],
                           photo_640: Option[URL],
                           photo_800: Option[URL],
                           photo_1280: Option[URL],
                           photo_2560: Option[URL],
                           date: Int
                         ) extends VkMedia
