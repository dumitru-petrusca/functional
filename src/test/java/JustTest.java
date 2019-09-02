import complex.Complex;
import functions.UncheckedFunction;
import monad.Error;
import monad.Just;
import monad.Value;
import org.junit.jupiter.api.Test;

import static monad.Just.*;
import static monad.Value.*;

public class JustTest {

  @Test
  public void test1() {
    String str = "abc";
    var value = of(str)
        .flatMap(s -> of(s.toUpperCase()))
        .filter(s -> s.contains("D"))
//        .flatMap(s -> Just.value("" + (1 / 0)))
        .or(() -> Error.of("Does not contain D"))
//        .flatMap(s -> null);

        ;
//    System.out.println(value);
  }

  @Test
  public void test2() {
    var p1 = of(1.);
    var p2 = of(2.);
    var c1 = Complex.of(p1, p1);
    var c2 = Complex.of(p2, p2);

    var addComplex = lift(Complex::add);
    UncheckedFunction<Value<Complex>, Value<Complex>> addC1 = x -> addComplex.apply(c1, x);
    UncheckedFunction<Value<Complex>, Value<Complex>> addC2 = x -> addComplex.apply(c2, x);

    var c = Complex.of(1., 1.);

    System.out.println(c);
  }

}
