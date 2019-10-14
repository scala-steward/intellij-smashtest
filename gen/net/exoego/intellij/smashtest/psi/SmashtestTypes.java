// This is a generated file. Not intended for manual editing.
package net.exoego.intellij.smashtest.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import net.exoego.intellij.smashtest.impl.*;

public interface SmashtestTypes {

  IElementType STEP = new SmashtestElementType("STEP");
  IElementType VARIABLE = new SmashtestElementType("VARIABLE");

  IElementType COMMENT = new SmashtestTokenType("comment");
  IElementType GLOBALVARIABLE = new SmashtestTokenType("globalVariable");
  IElementType LOCALVARIABLE = new SmashtestTokenType("localVariable");
  IElementType TEXT = new SmashtestTokenType("text");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == STEP) {
        return new SmashtestStepImpl(node);
      }
      else if (type == VARIABLE) {
        return new SmashtestVariableImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
