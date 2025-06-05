package forth;

import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader input;
    try {
      if (args.length == 0) {
        input = new BufferedReader(new InputStreamReader(System.in));
      } else if (args.length == 1) {
        input = new BufferedReader(new FileReader("src/main/java/forth/" + args[0]));
      } else {
        System.out.println("Usage: REPL or specify filename");
        return;
      }
    } catch (FileNotFoundException e) {
      System.err.println("Error opening file: " + args[0]);
      return;
    }

    Interpreter interpreter = new Interpreter();
    BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
    String inputLine;

    while ((inputLine = input.readLine()) != null) {
      output.write(interpreter.interpret(inputLine) + '\n');
      output.flush();
    }

    output.close();
    input.close();
  }
}