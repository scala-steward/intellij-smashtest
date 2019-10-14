// This is a generated file. Not intended for manual editing.
package net.exoego.intellij.smashtest.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static net.exoego.intellij.smashtest.psi.SmashtestTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class SmashtestParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return smashtestFile(b, l + 1);
  }

  /* ********************************************************** */
  // comment|step
  static boolean pattern_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pattern_")) return false;
    if (!nextTokenIs(b, "", COMMENT, TEXT)) return false;
    boolean r;
    r = consumeToken(b, COMMENT);
    if (!r) r = step(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // pattern_*
  static boolean smashtestFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "smashtestFile")) return false;
    while (true) {
      int c = current_position_(b);
      if (!pattern_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "smashtestFile", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // text+ comment?
  public static boolean step(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "step")) return false;
    if (!nextTokenIs(b, TEXT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = step_0(b, l + 1);
    r = r && step_1(b, l + 1);
    exit_section_(b, m, STEP, r);
    return r;
  }

  // text+
  private static boolean step_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "step_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TEXT);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, TEXT)) break;
      if (!empty_element_parsed_guard_(b, "step_0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // comment?
  private static boolean step_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "step_1")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  /* ********************************************************** */
  // localVariable|globalVariable
  public static boolean variable(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable")) return false;
    if (!nextTokenIs(b, "<variable>", GLOBALVARIABLE, LOCALVARIABLE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VARIABLE, "<variable>");
    r = consumeToken(b, LOCALVARIABLE);
    if (!r) r = consumeToken(b, GLOBALVARIABLE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

}
