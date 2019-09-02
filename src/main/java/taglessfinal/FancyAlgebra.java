package taglessfinal;

import monad.Just;
import monad.Value;

import static java.lang.String.*;
import static monad.Value.map2;

public class FancyAlgebra implements Algebra<Value<Symbol<Integer>>> {
  private Symbol<Integer> print(Symbol<Integer> symbol) {
    System.out.println(symbol);
    return symbol;
  }

  @Override
  public Value<Symbol<Integer>> lit(String name, String value) {
    return Just.of(value)
        .map(Integer::parseInt)
        .map(p -> print(new Symbol<>(name, p)));
  }

  @Override
  public Value<Symbol<Integer>> add(Value<Symbol<Integer>> x, Value<Symbol<Integer>> y) {
    return map2(x, y, (a, b) -> print(new Symbol<>(format("%s + %s", a.name, b.name), a.value + b.value)));
  }

  @Override
  public Value<Symbol<Integer>> sub(Value<Symbol<Integer>> x, Value<Symbol<Integer>> y) {
    return map2(x, y, (a, b) -> print(new Symbol<>(format("%s - %s", a.name, b.name), a.value - b.value)));
  }

  @Override
  public Value<Symbol<Integer>> mul(Value<Symbol<Integer>> x, Value<Symbol<Integer>> y) {
    return map2(x, y, (a, b) -> print(new Symbol<>(format("%s * %s", a.name, b.name), a.value * b.value)));
  }

  @Override
  public Value<Symbol<Integer>> div(Value<Symbol<Integer>> x, Value<Symbol<Integer>> y) {
    return map2(x, y, (a, b) -> print(new Symbol<>(format("%s / %s", a.name, b.name), a.value / b.value)));
  }
}
