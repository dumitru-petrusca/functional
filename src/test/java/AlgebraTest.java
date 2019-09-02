import monad.Value;
import org.junit.jupiter.api.Test;
import taglessfinal.*;

public class AlgebraTest {

  @Test
  public void test1() {
    Double d = calculate(new DoubleAlgebra(true));
    Term s = calculate(new ValuePrinter());
    System.out.println(s.text + " = " + d);
  }

  @Test
  public void test2() {
    Value<Symbol<Integer>> value = improper(new FancyAlgebra());
    Term term = improper(new ValuePrinter());
    System.out.println(term.text + " = " + value);
  }

  <T> T calculate(Algebra<T> a) {
    T zero = a.lit("x", "0");
    T one = a.lit("y", "1");
    T two = a.lit("z", "2");
    T y = a.mul(a.mul(two, two), one);
    return a.mul(a.div(a.sub(a.add(a.lit("t", "2"), a.lit("b", "3")), zero), y), two);
  }

  <T> T improper(Algebra<T> a) {
    return a.div(a.add(a.lit("a", "2"), a.lit("b", "3")), a.lit("c", "0"));
  }

}
