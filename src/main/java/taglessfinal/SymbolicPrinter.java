package taglessfinal;

import static java.lang.String.*;
import static taglessfinal.Term.Kind.*;

public class SymbolicPrinter implements Algebra<Term> {

  @Override
  public Term lit(String name, String s) {
    return new Term(name, LIT);
  }

  @Override
  public Term add(Term x, Term y) {
    return new Term(format("%s + %s", x.text, y.text), ADD);
  }

  @Override
  public Term sub(Term x, Term y) {
    return new Term(format("%s - %s", x.text, y.text), SUB);
  }

  @Override
  public Term mul(Term x, Term y) {
    String a = x.text(x.isAdditive());
    String b = y.text();
    return new Term(format("%s * %s", a, b), MUL);
  }

  @Override
  public Term div(Term x, Term y) {
    String a = x.text(x.isAdditive());
    String b = y.text();
    return new Term(format("%s / %s", a, b), DIV);
  }

}
