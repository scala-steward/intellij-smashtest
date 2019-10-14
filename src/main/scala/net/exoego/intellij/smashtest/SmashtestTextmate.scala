package net.exoego.intellij.smashtest

import com.intellij.lexer.{ Lexer, LexerBase, LexerPosition, LookAheadLexer }
import com.intellij.psi.tree.IElementType
import org.eclipse.tm4e.core.grammar.{ IToken, ITokenizeLineResult }
import org.eclipse.tm4e.core.registry.Registry

object SmashtestLexer extends LexerBase   {
  val grammar = {
    val registry = new Registry()
    val path = "/smash.tmLanguage.json"
    registry.loadGrammarFromPathSync(path, getClass.getResourceAsStream(path))
  }

  private var myBuffer: CharSequence = null
  private var myStartOffset: Int = 0
  private var myEndOffset: Int = 0
  private var currentState: Int = 0
  private var myTokenType: IElementType = null
  private var textmateTokens: Array[IToken] = null
  private var tokenPosition: Int  = 0

  /**
   * Prepare for lexing character data from {@code buffer} passed. Internal lexer state is supposed to be {@code initialState}. It is guaranteed
   * that the value of initialState is the same as returned by {@link #getState()} method of this lexer at condition {@code startOffset=getTokenStart()}.
   * This method is used to incrementally re-lex changed characters using lexing data acquired from this particular lexer sometime in the past.
   *
   * @param buffer       character data for lexing.
   * @param startOffset  offset to start lexing from
   * @param endOffset    offset to stop lexing at
   * @param initialState the initial state of the lexer.
   */
  override def start(buffer: CharSequence, startOffset: Int, endOffset: Int, initialState: Int): Unit = {
    myBuffer = buffer;
    myStartOffset = startOffset;
    myEndOffset = endOffset;
    currentState = initialState
    textmateTokens = grammar.tokenizeLine(buffer.toString.slice(startOffset,endOffset)).getTokens
  }

  override def getTokenType: IElementType = {
    if (textmateTokens.length > tokenPosition) null
    else textmateTokens.apply(tokenPosition).getScopes

  }

  override def getState: Int = currentState

  override def getTokenStart: Int = myStartOffset

  override def getTokenEnd: Int = myEndOffset

  override def getBufferEnd: Int = myEndOffset

  override def advance(): Unit = ???

  override def getBufferSequence: CharSequence = myBuffer

}
