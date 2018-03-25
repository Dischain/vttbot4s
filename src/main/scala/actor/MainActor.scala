package actor

import actor.messages.Tick
import akka.actor.SupervisorStrategy.Restart
import akka.actor.{Actor, OneForOneStrategy, Props, SupervisorStrategy}
import telegram.VtTTelegramBot
import vk.VkApi

import scala.concurrent.duration._

class MainActor(vk: VkApi, tb: VtTTelegramBot, period: Int) extends Actor {
  override def supervisorStrategy: SupervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 2.minute) {
      case _ => Restart
    }

  def receive: Actor.Receive = {
    case "start" =>
      val vkActor =
        context.actorOf(Props(new VkActor(vk)), "vkActor")
      val telegramActor =
        context.actorOf(Props(new TelegramActor(tb)), "telegramActor")
      val vttScheduler =
        context.actorOf(Props(new VtTScheduler(vkActor, telegramActor, period)), "actorScheduler")
      vttScheduler ! Tick
  }
}
