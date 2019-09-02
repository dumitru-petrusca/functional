package io;

import functions.UncheckedConsumer;
import functions.UncheckedFunction;
import functions.UncheckedSupplier;

import static exceptions.Exceptions.unchecked;

public interface IO<T> {

  T performIO();

  static <R> IO<R> value(R value) {
    return () -> value;
  }

  default <R> IO<R> io(UncheckedSupplier<R> function) {
    return () -> unchecked(function);
  }

  default <R> IO<R> map(UncheckedFunction<T, R> mapper) {
    return () -> unchecked(() -> mapper.apply(performIO()));
  }

  default <R> IO<R> output(UncheckedConsumer<T> consumer) {
    return () -> {
      unchecked(() -> consumer.accept(performIO()));
      return null;
    };
  }

  default <R> IO<R> input(UncheckedSupplier<R> supplier) {
    return () -> {
      performIO();
      return unchecked(supplier);
    };
  }

  default <R> IO<R> flatMap(UncheckedFunction<T, IO<R>> mapper) {
    return () -> unchecked(() -> mapper.apply(performIO())).performIO();
  }

}
