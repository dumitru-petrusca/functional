package monad;


import functions.UncheckedFunction;
import functions.UncheckedPredicate;
import functions.UncheckedSupplier;

import java.util.function.Consumer;

import static monad.Just.doTry;

public final class Threw<T> implements Value<T> {
  private Throwable t;

  public Threw(Exception t) {
    this.t = t;
  }

  public static <U> Value<U> of(Exception e) {
    return new Threw<>(e);
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
    throw new IllegalStateException(t);
  }

  @Override
  public String getError() {
    return t.getClass().getName() + ": " + t.getMessage();
  }

  public Throwable getException() {
    return t;
  }

  @Override
  public boolean isPresent() {
    return false;
  }

  @Override
  public String toString() {
    return String.format("threw[%s]", t);
  }
}
