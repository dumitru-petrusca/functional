package taglessfinal;

import static java.lang.String.*;

public class AlgebraPrinterNaive implements Algebra<String> {

  @Override
  public String lit(String name, String s) {
    return s;
  }

  @Override
  public String add(String x, String y) {
    return format("%s + %s", x, y);
  }

  @Override
  public String sub(String x, String y) {
    return format("%s - %s", x, y);
  }

  @Override
  public String mul(String x, String y) {
    return format("(%s) * (%s)", x, y);
  }

  @Override
  public String div(String x, String y) {
    return format("(%s) / (%s)", x, y);
  }

}
