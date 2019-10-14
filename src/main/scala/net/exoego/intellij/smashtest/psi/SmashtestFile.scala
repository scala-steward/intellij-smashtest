package net.exoego.intellij.smashtest.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import javax.swing.Icon
import net.exoego.intellij.smashtest.{SmashtestFileType, SmashtestLanguage}
import org.jetbrains.annotations.NotNull

class SmashtestFile(@NotNull viewProvider: FileViewProvider) extends PsiFileBase(viewProvider, SmashtestLanguage) {
  override def getFileType: FileType = SmashtestFileType

  override def toString: String = "Smashtest file"

  override def getIcon(flags: Int): Icon = super.getIcon(flags)
}
