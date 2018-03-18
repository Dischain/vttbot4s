package telegram

import java.net.URL

import info.mukel.telegrambot4s.api.TelegramBot
import info.mukel.telegrambot4s.methods.ParseMode.ParseMode
import info.mukel.telegrambot4s.methods.{SendDocument, SendMessage, SendPhoto, SendVideo}
import info.mukel.telegrambot4s.models.{ChatId, InputFile, Message, ReplyMarkup}

import scala.concurrent.Future

final class VTTTelegramBot(val token: String) extends TelegramBot {
  def sendVideo(
                 chatId              : ChatId,
                 videoName           : String,
                 videoURL            : String,
                 duration            : Option[Int] = None,
                 width               : Option[Int] = None,
                 height              : Option[Int] = None,
                 caption             : Option[String] = None,
                 disableNotification : Option[Boolean] = None,
                 replyToMessageId    : Option[Long] = None,
                 replyMarkup         : Option[ReplyMarkup] = None
               ): Future[Message] =
  {
    val in = new URL(videoURL).openConnection().getInputStream
    val byteArr = Stream.continually(in.read).takeWhile(-1 !=).map(_.toByte).toArray

    request(SendVideo(
      chatId,
      InputFile(videoName, byteArr),
      duration,
      width,
      height,
      caption,
      disableNotification,
      replyToMessageId,
      replyMarkup
    ))
  }

  def sendDocument(
                    chatId              : ChatId,
                    documentURL         : String,
                    documentName        : String,
                    caption             : Option[String] = None,
                    disableNotification : Option[Boolean] = None,
                    replyToMessageId    : Option[Long] = None,
                    replyMarkup         : Option[ReplyMarkup] = None
                  ): Future[Message] =
  {
    val in = new URL(documentURL).openConnection().getInputStream
    val byteArr = Stream.continually(in.read).takeWhile(-1 !=).map(_.toByte).toArray

    request(SendDocument(
      chatId,
      InputFile(documentName, byteArr),
      caption,
      disableNotification,
    replyToMessageId,
    replyMarkup
    ))
  }

  def sendPhoto(
                 chatId              : ChatId,
                 photoName           : String,
                 photoURL            : String,
                 caption             : Option[String] = None,
                 disableNotification : Option[Boolean] = None,
                 replyToMessageId    : Option[Int] = None,
                 replyMarkup         : Option[ReplyMarkup] = None
               ): Future[Message] =
  {
    val in = new URL(photoURL).openConnection().getInputStream
    val byteArr = Stream.continually(in.read).takeWhile(-1 !=).map(_.toByte).toArray

    request(SendPhoto(
      chatId,
      InputFile(photoName, byteArr),
      caption,
      disableNotification,
      replyToMessageId,
      replyMarkup))
  }

  def sendMessage(
                   chatId                : ChatId,
                   text                  : String,
                   parseMode             : Option[ParseMode] = None,
                   disableWebPagePreview : Option[Boolean] = None,
                   disableNotification   : Option[Boolean] = None,
                   replyToMessageId      : Option[Int] = None,
                   replyMarkup           : Option[ReplyMarkup] = None
                 ): Future[Message] =
  {
    request(SendMessage(
      chatId,
      text,
      parseMode,
      disableWebPagePreview,
      disableNotification,
      replyToMessageId,
      replyMarkup
    ))
  }
}