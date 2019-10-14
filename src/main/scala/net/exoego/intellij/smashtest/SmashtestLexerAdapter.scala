package net.exoego.intellij.smashtest

import com.intellij.lexer.FlexAdapter

class SmashtestLexerAdapter extends FlexAdapter(new SmashtestLexer(null.asInstanceOf[java.io.Reader])) {}
