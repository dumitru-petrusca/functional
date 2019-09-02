package functions;

/**
 * Represents a function that does not accept any arguments and produces a result.
 *
 * @param <T> the type of the result of the function
 */
@FunctionalInterface
public interface UncheckedSupplier<T> {
  T get() throws Exception;
}
