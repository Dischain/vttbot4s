package actor

import akka.actor.{Actor, ActorLogging, ActorRef}

import scala.concurrent.duration._
import actor.messages._
import vk.basic.VkWallPost

class VtTScheduler (
                     vkActor: ActorRef,
                     telegramActor: ActorRef
                   ) extends Actor with ActorLogging
{
  import scala.concurrent.ExecutionContext.Implicits.global

  val delay = 15
  var lastVkPostId: Int = _


  //schedule

  override def preStart(): Unit = {
    super.preStart()
    println("Scheduler started")
  }

  override def postStop(): Unit = {
    super.postStop()
    println("Scheduler stopped")
  }

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    super.preRestart(reason, message)
    println("Scheduler pre restarted")
  }

  override def postRestart(reason: Throwable): Unit = {
    super.postRestart(reason)
    println("Scheduler post restarted")
  }

  def receive: Actor.Receive = {
    case Tick =>
      println("Scheduler: Tick")
      vkActor ! LookupNewPost
    case PostReceived(post: VkWallPost) =>
      println("Scheduler: PostReceived: " + post)
      if (lastVkPostId != post.id) {
        telegramActor ! TelegramSendMessage(post)
      } else {
        repeat()
      }
    case LookupFailure =>
      println("Scheduler: LookupFailure")
      repeat()
    case UpdateLastPostId(id: Int) =>
      println("Scheduler: UpdateLastPostId: " + id)
      lastVkPostId = id
      repeat()
  }

  private[this] def repeat() = {
    println("Scheduler: scheduled")
    context.system.scheduler.scheduleOnce(delay.seconds, self, Tick)
  }
}
