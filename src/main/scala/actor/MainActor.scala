package actor

import actor.messages.Tick
import akka.actor.SupervisorStrategy.Restart
import akka.actor.{Actor, OneForOneStrategy, Props, SupervisorStrategy}
import telegram.VtTTelegramBot
import vk.VkApi

import scala.concurrent.duration._

class MainActor(vk: VkApi, tb: VtTTelegramBot) extends Actor {
  override def supervisorStrategy: SupervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 2.minute) {
      case _ => println("Main: Exeption"); Restart
    }
/*
  val supervisionStrategy: OneForOneStrategy =
    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 2.minute) {
      case _ => println("Main: Exeption"); Restart
    }*/

  /*private[this] val vkActor = context.actorOf(Props(new VkActor(vk)), "vkActor")
  private[this] val telegramActor = context.actorOf(Props(new TelegramActor(tb)), "telegramActor")
  private[this] val vttScheduler = context.actorOf(Props(new VtTScheduler(vkActor, telegramActor)), "actorScheduler")*/

  def receive: Actor.Receive = {
    case "start" =>
      println("start")
      val vkActor = context.actorOf(Props(new VkActor(vk)), "vkActor")
      val telegramActor = context.actorOf(Props(new TelegramActor(tb)), "telegramActor")
      val vttScheduler = context.actorOf(Props(new VtTScheduler(vkActor, telegramActor)), "actorScheduler")
      vttScheduler ! Tick
  }
}
