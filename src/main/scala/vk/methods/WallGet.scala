package vk.methods

/**
  * Returns a list of posts on a user wall or community wall
  *
  * @param domain `String` user or community short address
  * @param count  `Optional Int` number of posts to return (maximum 100)
  * @param offset `Optional Int` offset needed to return a specific subset
  *              of posts
  */
final case class WallGet(
                        domain: String,
                        count: Option[Int] = None,
                        offset: Option[Int] = None
                        ) extends VkApiRequestQS
{
  val methodName: String = "wall.get"
  val withAccessToken: Boolean = true
}
