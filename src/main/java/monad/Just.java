package monad;

import functions.UncheckedFunction;
import functions.UncheckedPredicate;
import functions.UncheckedSupplier;

import java.util.Objects;
import java.util.function.Consumer;

public class Just<T> implements Value<T> {
  private final T value;

  private Just(T value) {
    this.value = value;
  }

  public static <T> Value<T> of(T value) {
    return value == null ?
        Null.of() :
        new Just<>(value);
  }

  @Override
  public void ifPresent(Consumer<? super T> action) {
    action.accept(value);
  }

  public Value<T> filter(UncheckedPredicate<? super T> predicate) {
    if (predicate == null) {
      return Error.of("Cannot filter with a null predicate");
    }
    return doTry(() -> predicate.test(value) ? this : Null.of());
  }

  public <U> Value<U> map(UncheckedFunction<? super T, ? extends U> mapper) {
    if (mapper == null) {
      return Error.of("Cannot map with a null mapper");
    }
    return doTry(() -> of(mapper.apply(value)));
  }

  public <U> Value<U> flatMap(UncheckedFunction<? super T, ? extends Value<? extends U>> mapper) {
    if (mapper == null) {
      return Error.of("Cannot flatMap with a null mapper");
    }
    return doTry(() -> {
      Value<? extends U> newValue = mapper.apply(this.value);
      return newValue == null ?
          Error.of("flatMap mapper produced a null value") :
          (Value<U>) newValue;
    });
  }

  public Value<T> or(UncheckedSupplier<? extends Value<? extends T>> supplier) {
    return this;
  }

  public T orElseGet(UncheckedSupplier<? extends T> supplier) {
    return value;
  }

  @Override
  public T get() {
    return value;
  }

  @Override
  public String getError() {
    throw new IllegalStateException(String.format("The value %s is not an error.", this));
  }

  @Override
  public boolean isPresent() {
    return true;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Just)) {
      return false;
    }

    return Objects.equals(value, ((Just<?>) obj).value);
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }

  @Override
  public String toString() {
    return String.format("just[%s]", value);
  }

  protected static <M> Value<M> doTry(UncheckedSupplier<Value<M>> function) {
    try {
      return function.get();
    } catch (Exception e) {
      return Threw.of(e);
    }
  }
}
