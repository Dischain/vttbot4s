package util

import java.io.{File, PrintWriter}

import scala.concurrent.{ExecutionContext, Future}
import scala.io.Source

object AsyncFileHandler {

  /**
    * Asynchronously writes given text string to file by specified path
    *
    * @param path `String` path to file
    * @param text `String` text to write
    * @throws java.io.FileNotFoundException if some error occurs while
    *                                       while opening or creating file
    * @throws SecurityException if a security manager is present and
    *                           [[SecurityManager]] denies write access to the file
    * @return `Future[Either[scala.Exception, Unit]`
    */
  def writeFile(path: String, text: String)
               (implicit ec: ExecutionContext) = Future {
    val pw = new PrintWriter(new File(path))
    try {
      Right(pw.println(text))
    } catch {
      case e: Exception => Left(e)
    } finally {
      pw.close()
    }
  }

  /**
    * Read file contents by specified path
    *
    * @param path `String` path to file
    * @return `Future[String]`
    */
  def readFile(path: String) (implicit ec: ExecutionContext) = Future {
    val source = Source.fromFile(path)
    try {
      source.getLines().mkString("\n")
    } finally {
      source.close()
    }
  }
}
