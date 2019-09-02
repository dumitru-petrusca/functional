package monad;

import functions.UncheckedFunction;
import functions.UncheckedFunction2;
import functions.UncheckedPredicate;
import functions.UncheckedSupplier;

import java.util.function.Consumer;
import java.util.function.Function;

public interface Value<T> {

  /**
   * Filters the Value using the provided predicate function.
   * If this value is a Just the result is a mapped Value.
   * If this value is a Null, Error or Threw the value is lft unchanged.
   *
   * @param predicate
   */
  Value<T> filter(UncheckedPredicate<? super T> predicate);

  /**
   * Maps the Value using the provided mapper function which returns a simple value.
   * The method will wrap the simple value into a Value internally
   * If this value is a Just the result is a mapped Value.
   * If this value is a Null, Error or Threw the value is lft unchanged.
   */
  <U> Value<U> map(UncheckedFunction<? super T, ? extends U> mapper);

  /**
   * Maps the Value using the provided mapper function which returns a Value.
   * If this value is a Just the result is a mapped Value.
   * If this value is a Null, Error or Threw the value is lft unchanged.
   */
  <U> Value<U> flatMap(UncheckedFunction<? super T, ? extends Value<? extends U>> mapper);

  default <R> Value<R> apply(Function<T, R> f) {
    return flatMap(v ->
        Just.of(f.apply(v))
    );
  }

  /**
   * Performs a logical 'or' between this value and the supplied one.
   * If this value is a Just the result is unchanged.
   * If this value is a Null, Error or Threw the result is the supplied value.
   */
  Value<T> or(UncheckedSupplier<? extends Value<? extends T>> supplier);

  /**
   * If a value is present (Just), returns the value, otherwise (Null, Error and Threw) returns
   * the provided default  value.
   */
  default T orElse(T defaultValue) {
    return orElseGet(() -> defaultValue);
  }

  /**
   * If a value is present (Just), returns the value, otherwise (Null, Error and Threw) returns
   * the result produced by the supplying function.
   */
  T orElseGet(UncheckedSupplier<? extends T> supplier);

  /**
   * Returns the value if the value exists (it is a Just).
   * Throws an exception if called on Null, Error and Threw.
   */
  T get();

  /**
   * Returns a string representation of the error message for Null, Error and Threw.
   * Throws an exception if called on a Just.
   */
  String getError();

  /**
   * Returns true if the value is a Just and false for Null, Error, Threw
   */
  boolean isPresent();

  /**
   * If a value is present, performs the given action with the value,
   * otherwise does nothing.
   */
  void ifPresent(Consumer<? super T> action);

  /**
   * Maps a value over a function producing a value as a result.
   */
  static <P, R> Value<R> map1(Value<P> value, UncheckedFunction<P, R> function) {
    return value.flatMap(v ->
        Just.of(function.apply(v))
    );
  }

  /**
   * Maps two values over a function producing a value as a result.
   */
  static <P1, P2, R> Value<R> map2(Value<P1> value1, Value<P2> value2, UncheckedFunction2<P1, P2, R> function) {
    return value1.flatMap(v1 -> value2.flatMap(v2 ->
        Just.of(function.apply(v1, v2))
    ));
  }

  /**
   * Maps a value over a function which produces a value as a result.
   */
  static <P, R> Value<R> flatMap1(Value<P> value, UncheckedFunction<P, Value<R>> function) {
    return value.flatMap(function);
  }

  /**
   * Maps two values over a function that produces a value as a result.
   */
  static <P1, P2, R> Value<R> flatMap2(Value<P1> value1, Value<P2> value2, UncheckedFunction2<P1, P2, Value<R>> function) {
    return value1.flatMap(v1 -> value2.flatMap(v2 -> function.apply(v1, v2)));
  }

  /**
   * Folds an array of values from left to right starting with an initial value and using an aggregation function.
   * The result is a single value.
   */
  static <T, V> Value<V> foldr(Value<T>[] array, V initial, UncheckedFunction2<T, V, V> function) {
    Value<V> result = Just.of(initial);
    for (Value<T> element : array) {
      result = map2(result, element, (r, e) -> function.apply(e, r));
    }
    return result;
  }

  static <P1, P2, R> UncheckedFunction2<Value<P1>, Value<P2>, Value<R>> lift(UncheckedFunction2<P1, P2, R> f) {
    return (p1, p2) -> apply(p1, p2, f);
  }

  static <P1, P2, R> Value<R> apply(Value<P1> p1, Value<P2> p2, UncheckedFunction2<P1, P2, R> f) {
    if (p1 != null && p2 != null) {
      return p1.flatMap(v1 -> p2.flatMap(v2 ->
          Just.of(f.apply(v1, v2))
      ));
    }
    return Null.of();
  }

}
