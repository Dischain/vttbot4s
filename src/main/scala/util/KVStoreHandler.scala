package util

import java.io.{File, FileNotFoundException}

import util.AsyncFileHandler._

import scala.concurrent.{ExecutionContext, Future}

/**
  * Provides a utility methods for storing/readings key-value pairs to file
  *
  * @param path `String` path to file
  * @param ec `ExecutionContext` implicit execution context
  */
class KVStoreHandler(path: String) (implicit ec: ExecutionContext) {
  def storeValue(key: String, value: Any): Future[Unit] =
    writeFile(path, s"$key $value") map {
      case Left(FileNotFoundException) =>
        new File(path).createNewFile()
        storeValue(key, value)
      case Left(SecurityException) =>
        new File(path).setWritable(true)
        storeValue(key, value)
      case _ => Unit
    }

  def readValue(key: String): Future[Option[String]] =
    readFile(path) map { contents =>
      for {
        line <- contents.split("\n")
        if line contains key
      } yield line.mkString
    } map { _.headOption map { _.split(" ")(1)} }
}
