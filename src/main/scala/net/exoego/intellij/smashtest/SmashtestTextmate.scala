package net.exoego.intellij.smashtest

//import com.intellij.lexer.{ Lexer, LexerBase, LexerPosition, LookAheadLexer }
//import com.intellij.psi.tree.IElementType
//import com.intellij.psi.TokenType
import org.eclipse.tm4e.core.grammar.{IToken, ITokenizeLineResult}
import org.eclipse.tm4e.core.registry.Registry

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

case class MyToken(tokens: Seq[IToken], indentLevel: Int) {
  def show(buffer: String): String = {
    s"${" ".repeat(indentLevel)}${tokens.map(t => buffer.slice(t.getStartIndex, t.getEndIndex)).mkString("")}"
  }
}

object SmashtestLexer {
  val grammar = {
    val registry = new Registry()
    val path     = "/smash.tmLanguage.json"
    registry.loadGrammarFromPathSync(path, getClass.getResourceAsStream(path))
  }

  private var currentIndentLevel: Int = 0
  private var tokenPosition: Int      = 0
  private var newLineParsed: Boolean  = false

  def main(args: Array[String]): Unit = {
    val buffer =
      """Open Chrome
        |
        |    Navigate to 'google.com' comment // aa
        |
        |        Click 'one'
        |        Click 'two'
        |
        |    Navigate to 'yahoo.com' comment // aa
        |
        |        Click 'one'
        |        |""".stripMargin
    val tokens = grammar.tokenizeLine(buffer.stripMargin).getTokens
    for (token <- tokens) {
      println(s"${token}\n${buffer.slice(token.getStartIndex, token.getEndIndex)}")
    }
    println

    while (tokenPosition < tokens.length) {
      println(grouping(tokens, buffer).show(buffer))
    }
  }

  private def getNonEmptyToken(tokens: Array[IToken], buffer: String): Option[(IToken, Int)] = {
    if (tokenPosition >= tokens.length) None
    else {
      val token = tokens(tokenPosition)
      buffer.slice(token.getStartIndex, token.getEndIndex) match {
        case "\r" | "\n" | "\r\n" =>
          tokenPosition += 1
          newLineParsed = false
          getNonEmptyToken(tokens, buffer)
        case tokenStr =>
          tokenPosition += 1
          if (!newLineParsed && tokenStr.isBlank) {
            newLineParsed = true
            val indentLevel = tokenStr.replaceAll("\r|\n","").length
            currentIndentLevel = indentLevel
            None
          } else {
            newLineParsed = true
            Some((token, currentIndentLevel))
          }
      }
    }
  }

  def grouping(tokens: Array[IToken], buffer: String): MyToken = {
    if (tokenPosition >= tokens.length) null
    else {
      val tmp                          = ListBuffer.empty[IToken]
      var token: Option[(IToken, Int)] = None
      var indentLevel: Int             = 0
      while ({
        token = getNonEmptyToken(tokens, buffer)
        token.isDefined
      }) {
        indentLevel = token.get._2
        tmp.addOne(token.get._1)
      }
      MyToken(tmp.toSeq, indentLevel)
    }
  }
//  private var myBuffer: CharSequence = null
//  private var myStartOffset: Int = 0
//  private var myEndOffset: Int = 0
//  private var currentState: Int = 0
//  private var myTokenType: IElementType = null
//  private var textmateTokens: Array[IToken] = null
//  private var tokenPosition: Int  = 0
//
//  /**
//   * Prepare for lexing character data from {@code buffer} passed. Internal lexer state is supposed to be {@code initialState}. It is guaranteed
//   * that the value of initialState is the same as returned by {@link #getState()} method of this lexer at condition {@code startOffset=getTokenStart()}.
//   * This method is used to incrementally re-lex changed characters using lexing data acquired from this particular lexer sometime in the past.
//   *
//   * @param buffer       character data for lexing.
//   * @param startOffset  offset to start lexing from
//   * @param endOffset    offset to stop lexing at
//   * @param initialState the initial state of the lexer.
//   */
//  override def start(buffer: CharSequence, startOffset: Int, endOffset: Int, initialState: Int): Unit = {
//    myBuffer = buffer;
//    myStartOffset = startOffset;
//    myEndOffset = endOffset;
//    currentState = initialState
//    textmateTokens = grammar.tokenizeLine(buffer.toString.slice(startOffset,endOffset)).getTokens
//  }
//
//  override def getTokenType: IElementType = {
//    if (textmateTokens.length > tokenPosition) null
//    else textmateTokens.apply(tokenPosition).getScopes
//
//  }
//
//  override def getState: Int = currentState
//
//  override def getTokenStart: Int = myStartOffset
//
//  override def getTokenEnd: Int = myEndOffset
//
//  override def getBufferEnd: Int = myEndOffset
//
//  override def advance(): Unit = ???
//
//  override def getBufferSequence: CharSequence = myBuffer

}
