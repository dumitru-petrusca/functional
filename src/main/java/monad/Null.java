package monad;

import functions.UncheckedFunction;
import functions.UncheckedPredicate;
import functions.UncheckedSupplier;

import java.util.function.Consumer;

import static monad.Just.doTry;

public class Null<T> implements Value<T> {
  public static final Null<?> NULL = new Null<>();

  private Null() {
  }

  public static <T> Null<T> of() {
    return (Null<T>) NULL;
  }

  @Override
  public void ifPresent(Consumer<? super T> action) {
    // do nothing
  }

  @Override
  public Value<T> filter(UncheckedPredicate<? super T> predicate) {
    return this;
  }

  @Override
  public <U> Value<U> map(UncheckedFunction<? super T, ? extends U> mapper) {
    return (Value<U>) this;
  }

  @Override
  public <U> Value<U> flatMap(UncheckedFunction<? super T, ? extends Value<? extends U>> mapper) {
    return (Value<U>) this;
  }

  @Override
  public Value<T> or(UncheckedSupplier<? extends Value<? extends T>> supplier) {
    return doTry(() -> (Value<T>)supplier.get());
  }

  @Override
  public T orElseGet(UncheckedSupplier<? extends T> supplier) {
    try {
      return supplier.get();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public T get() {
    throw new NullPointerException(getError());
  }

  @Override
  public String getError() {
    return "The value is null";
  }

  @Override
  public boolean isPresent() {
    return false;
  }

  @Override
  public String toString() {
    return "[null]";
  }

}
