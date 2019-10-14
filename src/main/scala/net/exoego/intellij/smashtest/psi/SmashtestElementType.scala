package net.exoego.intellij.smashtest.psi

import com.intellij.psi.tree.IElementType
import net.exoego.intellij.smashtest.SmashtestLanguage
import org.jetbrains.annotations.NotNull

class SmashtestElementType(@NotNull debugName: String) extends IElementType(debugName, SmashtestLanguage) {
  override def toString: String = s"SmashtestTokenType.${super.toString}"
}
