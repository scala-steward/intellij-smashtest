package net.exoego.intellij.smashtest

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon
import org.jetbrains.annotations.NotNull

object SmashtestFileType extends LanguageFileType(SmashtestLanguage) {
  @NotNull def getName = "Smashtest"

  override def getDescription: String = "A language for rapidly describing and deploying test cases."

  override def getDefaultExtension: String = "smash"

  override def getIcon: Icon = SmashtestIcons.SmashtestFileType
}
