package monad;


import functions.UncheckedFunction;
import functions.UncheckedPredicate;
import functions.UncheckedSupplier;

import java.util.Objects;
import java.util.function.Consumer;

import static monad.Just.doTry;


public class Error<T> implements Value<T> {
  private final String errorMessage;

  private Error(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public static <T> Value<T> of(String message) {
    return new Error<>(message);
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
    throw new IllegalStateException(errorMessage);
  }

  public String getError() {
    return errorMessage;
  }

  @Override
  public boolean isPresent() {
    return false;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Error)) {
      return false;
    }

    return Objects.equals(errorMessage, ((Error<?>) obj).errorMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(errorMessage);
  }

  @Override
  public String toString() {
    return String.format("error[%s]", errorMessage);
  }
}
