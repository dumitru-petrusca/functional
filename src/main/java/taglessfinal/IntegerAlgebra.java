package taglessfinal;

import monad.Just;
import monad.Value;

import static monad.Value.*;

public class IntegerAlgebra implements Algebra<Value<Integer>> {
  @Override
  public Value<Integer> lit(String name, String value) {
    return Just.of(value).map(Integer::parseInt);
  }

  @Override
  public Value<Integer> add(Value<Integer> x, Value<Integer> y) {
    return map2(x, y, (a, b) -> a + b);
  }

  @Override
  public Value<Integer> sub(Value<Integer> x, Value<Integer> y) {
    return map2(x, y, (a, b) -> a - b);
  }

  @Override
  public Value<Integer> mul(Value<Integer> x, Value<Integer> y) {
    return map2(x, y, (a, b) -> a * b);
  }

  @Override
  public Value<Integer> div(Value<Integer> x, Value<Integer> y) {
    return map2(x, y, (a, b) -> a / b);
  }
}
