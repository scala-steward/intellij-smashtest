package net.exoego.intellij.smashtest

import com.intellij.lang.{ASTNode, ParserDefinition, PsiParser}
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.{FileViewProvider, PsiElement, PsiFile, TokenType}
import com.intellij.psi.tree.{IFileElementType, TokenSet}
import net.exoego.intellij.smashtest.parser.SmashtestParser
import net.exoego.intellij.smashtest.psi.{SmashtestFile, SmashtestTypes}

object SmashtestParserDefinition extends ParserDefinition {
  val FILE: IFileElementType = new IFileElementType(SmashtestLanguage)
  val WHITE_SPACES: TokenSet = TokenSet.create(TokenType.WHITE_SPACE)
  val COMMENTS: TokenSet     = TokenSet.create(SmashtestTypes.COMMENT)

  override def createLexer(project: Project): Lexer = new SmashtestLexerAdapter

  override def createParser(project: Project): PsiParser = new SmashtestParser()

  override def getFileNodeType: IFileElementType = SmashtestParserDefinition.FILE

  override def getCommentTokens: TokenSet = SmashtestParserDefinition.COMMENTS

  override def getStringLiteralElements: TokenSet = TokenSet.EMPTY

  override def createElement(astNode: ASTNode): PsiElement = SmashtestTypes.Factory.createElement(astNode)

  override def createFile(fileViewProvider: FileViewProvider): PsiFile = new SmashtestFile(fileViewProvider)
}
