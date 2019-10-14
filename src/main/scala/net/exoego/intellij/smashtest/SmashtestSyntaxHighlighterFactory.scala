package net.exoego.intellij.smashtest

import com.intellij.openapi.fileTypes.{ SyntaxHighlighter, SyntaxHighlighterFactory }
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

class SmashtestSyntaxHighlighterFactory extends SyntaxHighlighterFactory {
  override def getSyntaxHighlighter(project: Project, virtualFile: VirtualFile): SyntaxHighlighter = SmashtestSyntaxHighlighter
}
