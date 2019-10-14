package net.exoego.intellij.smashtest;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static net.exoego.intellij.smashtest.psi.SmashtestTypes.*;

%%

%{
  public SmashtestLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class SmashtestLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

NEWLINE=[\n\r]+
WHITE_SPACE=\s+
LOCAL_VARIABLE="{{" [^\n\r]+ "}}"
GLOBAL_VARIABLE="{" [^\n\r]+ "}"
END_OF_LINE_COMMENT="//" [^\n\r]*
TEXT=[^\n\r\t ]+
SPACING=[ \t]+

%state STEP

%%
<YYINITIAL> {
  {WHITE_SPACE}          { yybegin(YYINITIAL); return WHITE_SPACE; }
  {END_OF_LINE_COMMENT}  { yybegin(YYINITIAL); return COMMENT; }
  {TEXT}                 { yybegin(STEP); return TEXT; }
}

<STEP> {
  {END_OF_LINE_COMMENT}  { return COMMENT; }
  {TEXT}                 { return TEXT; }
  {SPACING}              { return WHITE_SPACE; }
  {NEWLINE}              { yybegin(YYINITIAL); return WHITE_SPACE; }
}



[^] { return BAD_CHARACTER; }
