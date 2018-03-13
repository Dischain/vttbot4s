package vk.basic

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
                          createdBy: Int,
                          date: Int,
                          text: String,
                          attachments: Option[Seq[VkMedia]]
                         )