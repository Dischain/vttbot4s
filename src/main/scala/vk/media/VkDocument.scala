package vk.media

/**
  * Represents (not all) fields of Vk `Object` object
  *
  * @param id document identifier
  * @param title document title
  * @param size document size in Bytes
  * @param ext document extension
  * @param url loadable url
  * @param date date (in Unixtime) the document has been added
  */
final case class VkDocument(
                           id: Int,
                           title: String,
                           size: Integer,
                           ext: String,
                           url: String,
                           date: Int
                           ) extends VkMedia
