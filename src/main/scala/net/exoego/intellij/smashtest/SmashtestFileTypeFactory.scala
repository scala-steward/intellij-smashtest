package net.exoego.intellij.smashtest

import com.intellij.openapi.fileTypes.{FileTypeConsumer, FileTypeFactory}

class SmashtestFileTypeFactory extends FileTypeFactory {
  override def createFileTypes(fileTypeConsumer: FileTypeConsumer): Unit = {
    fileTypeConsumer.consume(SmashtestFileType)
  }
}
