package util

import vk.basic.VkWallPost
import vk.media.{VkDocument, VkPhoto, VkVideo}

object Utils {
  def formPost(post: VkWallPost, domain: String): String = {
    val previewImage = post.attachments(0) match {
      case d: VkDocument => d.url
      case p: VkPhoto =>
        p.photo_604 getOrElse(p.photo_807 getOrElse(p.photo_1280 getOrElse(p.photo_130 get)))
      case v: VkVideo => formVkUrlToVideo(domain, v.id, v.owner_id)
      case _ => ""
    }

    val text = post.text
    val postUrl = formVkUrlToPost(domain, post.id, post.from_id)
    s""""$text<a href="$previewImage">&#160</a>\n$postUrl""""
  }

  def formVkUrlToVideo(domain: String, id: Int, count: Int): String =
    s"https://vk.com/$domain?z=video${count}_$id"

  def formVkUrlToPost(domain: String, id: Int, fromId: Int): String =
    s"https://vk.com/$domain?w=wall${fromId}_$id"
}
