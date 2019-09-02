package functions;

/**
 * Represents a function that accepts two arguments and produces a result.
 *
 * @param <P1> the type of the first input to the function
 * @param <P2> the type of the second input to the function
 * @param <R>  the type of the result of the function
 */
@FunctionalInterface
public interface UncheckedFunction2<P1, P2, R> {
  R apply(P1 p1, P2 p2) throws Exception;
}
