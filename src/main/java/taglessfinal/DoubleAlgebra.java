package taglessfinal;

public class DoubleAlgebra implements Algebra<Double> {
  private boolean print;

  public DoubleAlgebra(boolean print) {
    this.print = print;
  }

  @Override
  public Double lit(String name, String s) {
    return Double.parseDouble(s);
  }

  @Override
  public Double add(Double x, Double y) {
    if (print) System.out.printf("%s + %s = %s\n", x, y, x + y);
    return x + y;
  }

  @Override
  public Double sub(Double x, Double y) {
    if (print) System.out.printf("%s - %s = %s\n", x, y, x - y);
    return x - y;
  }

  @Override
  public Double mul(Double x, Double y) {
    if (print) System.out.printf("%s * %s = %s\n", x, y, x * y);
    return x * y;
  }

  @Override
  public Double div(Double x, Double y) {
    if (print) System.out.printf("%s / %s = %s\n", x, y, x / y);
    return x / y;
  }

}
