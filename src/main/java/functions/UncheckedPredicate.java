package functions;

/**
 * Represents a predicate (boolean-valued function) of one argument.
 */
@FunctionalInterface
public interface UncheckedPredicate<T> {
  boolean test(T t);
}
