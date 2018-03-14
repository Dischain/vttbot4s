package vk.methods

final case class WallGet(
                        domain: String,
                        count: Option[Int] = None,
                        offset: Option[Int] = None
                        ) extends ApiRequestQS
{
  val methodName: String = "wall.get"
  val withAccessToken: Boolean = true
}
