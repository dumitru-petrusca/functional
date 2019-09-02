import io.IO;

import java.util.Scanner;

public class IOTest {

  public static void main(String[] args) {
    IO.value("Please enter your name: DD")
        .output(System.out::print)
        .input(() -> new Scanner(System.in).nextLine())
        .output(name -> System.out.println("You entered: " + name))
        .performIO();
  }

}
