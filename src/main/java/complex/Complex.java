package complex;

import monad.Just;
import monad.Value;

import static monad.Value.apply;

public class Complex {
  public final double r;
  public final double i;

  public Complex(double r, double i) {
    this.r = r;
    this.i = i;
  }

  public static Value<Complex> of(Double r, Double i) {
    return Just.of(new Complex(r, i));
  }

  public static Value<Complex> of(Value<Double> r, Value<Double> i) {
    return apply(r, i, Complex::new);
  }

  public Complex add(Complex c) {
    return new Complex(r + c.r, i + c.i);
  }

  @Override
  public String toString() {
    return String.format("%s + i * %s", r, i);
  }
}
