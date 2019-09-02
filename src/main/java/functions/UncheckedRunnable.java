package functions;

@FunctionalInterface
public interface UncheckedRunnable {
  void run() throws Throwable;
}
