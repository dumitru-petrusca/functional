package taglessfinal;

public class Symbol<T> {
  public final String name;
  public final T value;

  public Symbol(String name, T value) {
    this.value = value;
    this.name = name;
  }

  @Override
  public String toString() {
    return name + " = " + value;
  }
}
