package net.exoego.intellij.smashtest

import com.intellij.lexer.Lexer
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import net.exoego.intellij.smashtest.psi.SmashtestTypes

object SmashtestSyntaxHighlighter extends SyntaxHighlighterBase {

  import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
  import com.intellij.openapi.editor.HighlighterColors
  import com.intellij.openapi.editor.colors.TextAttributesKey

  val COMMENT: TextAttributesKey =
    createTextAttributesKey("SIMPLE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
  val BAD_CHARACTER: TextAttributesKey =
    createTextAttributesKey("SIMPLE_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

  import com.intellij.openapi.editor.colors.TextAttributesKey

  private val BAD_CHAR_KEYS = Array[TextAttributesKey](BAD_CHARACTER)
  private val COMMENT_KEYS  = Array[TextAttributesKey](COMMENT)
  private val EMPTY_KEYS    = new Array[TextAttributesKey](0)

  override def getHighlightingLexer: Lexer = new SmashtestLexerAdapter

  override def getTokenHighlights(tokenType: IElementType): Array[TextAttributesKey] = {
    if (tokenType.equals(SmashtestTypes.COMMENT)) COMMENT_KEYS
    else if (tokenType.equals(TokenType.BAD_CHARACTER)) BAD_CHAR_KEYS
    else EMPTY_KEYS
  }
}
