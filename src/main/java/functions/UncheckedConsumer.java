package functions;

@FunctionalInterface
public interface UncheckedConsumer<T> {

    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     */
    void accept(T t) throws Throwable;

}
