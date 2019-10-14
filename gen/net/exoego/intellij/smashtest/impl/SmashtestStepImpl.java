// This is a generated file. Not intended for manual editing.
package net.exoego.intellij.smashtest.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static net.exoego.intellij.smashtest.psi.SmashtestTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import net.exoego.intellij.smashtest.psi.*;

public class SmashtestStepImpl extends ASTWrapperPsiElement implements SmashtestStep {

  public SmashtestStepImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SmashtestVisitor visitor) {
    visitor.visitStep(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SmashtestVisitor) accept((SmashtestVisitor)visitor);
    else super.accept(visitor);
  }

}
