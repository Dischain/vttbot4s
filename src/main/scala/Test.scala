import play.api.libs.json._
import play.api.libs.functional.syntax._
import vk.api.RequestHandler
import vk.basic.VkWallPost

object Test extends App {
  override def main(args: Array[String]): Unit = {
    val rh = new RequestHandler("ac5a5c5dac5a5c5dac5a5c5dcfac3b9c5aaac5aac5a5c5df6fce59d80c0f8478c54df3e")

    case class WallGetResp(count: Int, items: Seq[VkWallPost])

    implicit val wallGetRespReads: Reads[WallGetResp] = (
      (JsPath \ /*"response" \*/ "count").read[Int] and
      (JsPath \ /*"response" \*/ "items").read[Seq[VkWallPost]]
    )(WallGetResp.apply _)

    /*implicit val vkWallPostReads: Reads[VkWallPost] = new Reads[VkWallPost] {
      def reads(json: JsValue): JsResult[VkWallPost] = {
        import vk.media.VkMedia

        implicit val vkmf = VkMedia.vkMediaFormat

        for {
          id <- (JsPath \ "id").read[Int]
          fromId <- (JsPath \ "fromId").read[Int]
          createdBy <- (JsPath \ "createdBy").read[Int]
          date <- (JsPath \ "date").read[Int]
          text <- (JsPath \ "text").read[String]
          attachments <- (JsPath \ "attachments").read[Seq[VkMedia]]
        } yield VkWallPost(id, fromId, createdBy, date, text, attachments)
      }
    }*/
    /*implicit val vkWallPostReads: Reads[VkWallPost] = (
      (JsPath \ "id").read[Int] and
      (JsPath \ "fromId").read[Int] and
      (JsPath \ "createdBy").read[Int] and
      (JsPath \ "date").read[Int] and
      (JsPath \ "text").read[String] and
      (JsPath \ "attachments").read[Seq[VkMedia]]
    )(VkWallPost.apply _)*/
//    import vk.media.VkMedia
//
//    implicit val vkmf = VkMedia.vkMediaFormat
//    implicit val vkWallPostReads: Reads[VkWallPost] = for {
//      id <- (JsPath \ "id").read[Int]
//      fromId <- (JsPath \ "fromId").read[Int]
//      createdBy <- (JsPath \ "createdBy").read[Int]
//      date <- (JsPath \ "date").read[Int]
//      text <- (JsPath \ "text").read[String]
//      attachments <- (JsPath \ "attachments").read[Seq[VkMedia]]
//    } yield VkWallPost(id, fromId, createdBy, date, text, attachments)

    /*implicit val vkPhotoReads: Reads[VkPhoto] = (
      (JsPath \ "id").read[Int] and
      (JsPath \ "text").read[String] and
      (JsPath \ "date").read[Int] and
      (JsPath \ "photo_75").readNullable[String] and
      (JsPath \ "photo_130").readNullable[String] and
      (JsPath \ "photo_604").readNullable[String] and
      (JsPath \ "photo_807").readNullable[String] and
      (JsPath \ "photo_1280").readNullable[String] and
      (JsPath \ "photo_2560").readNullable[String]
    )(VkPhoto.apply _)

    implicit val vkDocumentReads: Reads[VkDocument] = (
      (JsPath \ "id").read[Int] and
      (JsPath \ "title").read[String] and
      (JsPath \ "size").read[Int] and
      (JsPath \ "ext").read[String] and
      (JsPath \ "url").read[String] and
      (JsPath \ "date").read[Int]
    )(VkDocument.apply _)

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
    )(VkVideo.apply _)*/
    //implicit val wallPostBR =
    //val r = Json.fromJson(Json.parse("""{"response":{"count":409,"items":[{"id":426,"from_id":-140233986,"owner_id":-140233986,"date":1515844752,"marked_as_ads":0,"post_type":"post","text":"","is_pinned":1,"attachments":[{"type":"photo","photo":{"id":456239480,"album_id":-7,"owner_id":-140233986,"user_id":100,"photo_75":"https://pp.userapi.com/c824601/v824601577/82983/jnzitTr4qWw.jpg","photo_130":"https://pp.userapi.com/c824601/v824601577/82984/S6b9mRiMO5k.jpg","photo_604":"https://pp.userapi.com/c824601/v824601577/82985/ovkC8awmyAw.jpg","photo_807":"https://pp.userapi.com/c824601/v824601577/82986/BVK4r16ZyiQ.jpg","photo_1280":"https://pp.userapi.com/c824601/v824601577/82987/r7hpcP_mYZU.jpg","width":1280,"height":720,"text":"","date":1515844751,"post_id":426,"access_key":"4a265dcbd6ce582218"}},{"type":"page","page":{"id":55470960,"group_id":140233986,"title":"Меню","who_can_view":2,"who_can_edit":0,"edited":0,"created":1515844630,"views":58,"view_url":"https://m.vk.com/page-140233986_55470960?api_view=9e73fa275dd74d09ead3873fa27b5e"}}],"post_source":{"type":"vk"},"comments":{"count":0,"groups_can_post":true,"can_post":1},"likes":{"count":8,"user_likes":0,"can_like":1,"can_publish":1},"reposts":{"count":1,"user_reposted":0},"views":{"count":1157}}]}}"""))
    val r = Json.fromJson(Json.parse("""{"count":409,"items":[{"id":426,"from_id":-140233986,"owner_id":-140233986,"date":1515844752,"marked_as_ads":0,"post_type":"post","text":"","is_pinned":1,"attachments":[{"type":"photo","photo":{"id":456239480,"album_id":-7,"owner_id":-140233986,"user_id":100,"photo_75":"https://pp.userapi.com/c824601/v824601577/82983/jnzitTr4qWw.jpg","photo_130":"https://pp.userapi.com/c824601/v824601577/82984/S6b9mRiMO5k.jpg","photo_604":"https://pp.userapi.com/c824601/v824601577/82985/ovkC8awmyAw.jpg","photo_807":"https://pp.userapi.com/c824601/v824601577/82986/BVK4r16ZyiQ.jpg","photo_1280":"https://pp.userapi.com/c824601/v824601577/82987/r7hpcP_mYZU.jpg","width":1280,"height":720,"text":"","date":1515844751,"post_id":426,"access_key":"4a265dcbd6ce582218"}},{"type":"page","page":{"id":55470960,"group_id":140233986,"title":"Меню","who_can_view":2,"who_can_edit":0,"edited":0,"created":1515844630,"views":58,"view_url":"https://m.vk.com/page-140233986_55470960?api_view=9e73fa275dd74d09ead3873fa27b5e"}}],"post_source":{"type":"vk"},"comments":{"count":0,"groups_can_post":true,"can_post":1},"likes":{"count":8,"user_likes":0,"can_like":1,"can_publish":1},"reposts":{"count":1,"user_reposted":0},"views":{"count":1157}}]}"""))
    println(r)
    /*for {
      f <- rh(WallGet("kometa_tuning_simferopol", Some(1)))
    } println(f)*/
  }
}
