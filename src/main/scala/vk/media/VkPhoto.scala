package vk.media

import java.net.URL

/**
  * Represents (not all) fields of Vk `Photo` object
  *
  * @param id photo identifier
  * @param text text of description
  * @param date date (in Unixtime) the photo has been added
  * @param photo_75 URL of the copy of the photo with a maximum size of 75x75px
  * @param photo_130 URL of the copy of the photo with a maximum size of 130x130
  * @param photo_604 URL of the copy of the photo with a maximum size of 604x604
  * @param photo_807 URL of the copy of the photo with a maximum size of 807x807
  * @param photo_1280 URL of the copy of the photo with a maximum size of 1280x1024
  * @param photo_2560 URL of the copy of the photo with a maximum size of  2560x2048
  */
final case class VkPhoto(
                          id: Int,
                          text: String,
                          date: Int,
                          photo_75: Option[URL],
                          photo_130: Option[URL],
                          photo_604: Option[URL],
                          photo_807: Option[URL],
                          photo_1280: Option[URL],
                          photo_2560: Option[URL]
                        ) extends VkMedia
