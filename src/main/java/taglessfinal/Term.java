package taglessfinal;

import static taglessfinal.Term.Kind.*;

public class Term {
  public final String text;
  public final Kind kind;

  public Term(String text, Kind kind) {
    this.text = text;
    this.kind = kind;
  }

  public String text() {
    return kind == LIT ? text : "(" + text + ")";
  }

  public String text(boolean paren) {
    return kind != LIT && paren ? "(" + text + ")" : text;
  }

  public boolean isAdditive() {
    return kind == ADD || kind == SUB;
  }

  public enum Kind {
    LIT,
    ADD,
    SUB,
    DIV,
    MUL
  }
}
