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
    createTextAttributesKey("SMASHTEST_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
  val LOCAL_VARIABLE: TextAttributesKey =
    createTextAttributesKey("SMASHTEST_LOCAL_VARIABLE", DefaultLanguageHighlighterColors.LOCAL_VARIABLE)
  val GLOBAL_VARIABLE: TextAttributesKey =
    createTextAttributesKey("SMASHTEST_GLOBAL_VARIABLE", DefaultLanguageHighlighterColors.GLOBAL_VARIABLE)
  val BAD_CHARACTER: TextAttributesKey =
    createTextAttributesKey("SMASHTEST_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)
  val STEP: TextAttributesKey =
    createTextAttributesKey("SMASHTEST_STEP", DefaultLanguageHighlighterColors.STRING)
  val NORMAL_TEXT: TextAttributesKey =
    createTextAttributesKey("SMASHTEST_TEXT", HighlighterColors.TEXT)

  import com.intellij.openapi.editor.colors.TextAttributesKey

  private val BAD_CHAR_KEYS = Array[TextAttributesKey](BAD_CHARACTER)
  private val COMMENT_KEYS  = Array[TextAttributesKey](COMMENT)
  private val EMPTY_KEYS    = new Array[TextAttributesKey](0)
  private val LOCAL_VARIABLE_KEYS = Array[TextAttributesKey](LOCAL_VARIABLE)
  private val GLOBAL_VARIABLE_KEYS = Array[TextAttributesKey](GLOBAL_VARIABLE)
  private val STEP_KEYS = Array[TextAttributesKey](LOCAL_VARIABLE)
  private val NORMALTEXT_KEYS = Array[TextAttributesKey](NORMAL_TEXT)

  override def getHighlightingLexer: Lexer = new SmashtestLexerAdapter

  override def getTokenHighlights(tokenType: IElementType): Array[TextAttributesKey] = {
    if (tokenType.equals(SmashtestTypes.COMMENT)) COMMENT_KEYS
    else if (tokenType.equals(TokenType.BAD_CHARACTER)) BAD_CHAR_KEYS
    else if (tokenType.equals(SmashtestTypes.LOCALVARIABLE)) LOCAL_VARIABLE_KEYS
    else if (tokenType.equals(SmashtestTypes.GLOBALVARIABLE)) STEP_KEYS
    else if (tokenType.equals(SmashtestTypes.TEXT)) NORMALTEXT_KEYS
    else if (tokenType.equals(SmashtestTypes.STEP)) GLOBAL_VARIABLE_KEYS
    else EMPTY_KEYS
  }
}
