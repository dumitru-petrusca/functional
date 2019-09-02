package exceptions;

import functions.UncheckedConsumer;
import functions.UncheckedRunnable;
import functions.UncheckedSupplier;

import java.util.function.Consumer;

public enum Exceptions {
  ;

  /**
   * Generics are erased in Java. So this basically throws an Exception.
   * The real type of T is lost during the compilation
   */
  public static <T extends Throwable> void throwUnchecked(Throwable e) throws T {
    // Since the type is erased, this cast actually does nothing!!! We can throw any exception.
    throw (T) e;
  }

  /**
   * Executes given supplier and catches and rethrows checked exceptions as unchecked exceptions,
   * without wrapping exception.
   *
   * @return result of function
   */
  public static <R> R unchecked(UncheckedSupplier<R> supplier) {
    try {
      return supplier.get();
    } catch (Throwable e) {
      throwUnchecked(e);
      return null; // This will never return since the line above throws, confused?!
    }
  }

  /**
   * Executes given runnable and catches and rethrows checked exceptions as unchecked exceptions,
   * without wrapping exception.
   */
  public static void unchecked(UncheckedRunnable runnable) {
    try {
      runnable.run();
    } catch (Throwable e) {
      throwUnchecked(e);
    }
  }

  /**
   * Executes given runnable and catches and rethrows checked exceptions as unchecked exceptions,
   * without wrapping exception.
   */
  public static <T> Consumer<T> unchecked(UncheckedConsumer<T> consumer) {
    return (t) -> {
      try {
        consumer.accept(t);
      } catch (Throwable e) {
        throwUnchecked(e);
      }
    };
  }
}