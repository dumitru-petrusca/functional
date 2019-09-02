package taglessfinal;

public interface Algebra<T> {

  T lit(String name, String value);

  T add(T x, T y);

  T sub(T x, T y);

  T mul(T x, T y);

  T div(T x, T y);

}
