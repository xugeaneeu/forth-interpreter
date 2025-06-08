package forth;

import java.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
  private static final Logger log = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) throws IOException {
    log.info("Запуск Forth-интерпретатора");

    BufferedReader input;
    try {
      if (args.length == 0) {
        log.info("Режим REPL: читаем из STDIN, пишем в STDOUT");
        input = new BufferedReader(new InputStreamReader(System.in));
      } else if (args.length == 1) {
        log.info("Режим пакетного выполнения: читаем файл '{}'", args[0]);
        input = new BufferedReader(new FileReader("src/main/java/forth/" + args[0]));
      } else {
        log.warn("Неверное число аргументов: {}", (Object) args);
        System.out.println("Usage: REPL or specify filename");
        return;
      }
    } catch (FileNotFoundException e) {
      log.error("Error opening file: {}", args[0], e);
      return;
    }

    Interpreter interpreter = new Interpreter();
    BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
    String inputLine;

    while ((inputLine = input.readLine()) != null) {
      log.debug("Входная строка: '{}'", inputLine);
      String result = interpreter.interpret(inputLine);
      log.debug("Результат интерпретации: '{}'", result);
      if (result.isEmpty()) {
        continue;
      }
      output.write(result);
      output.newLine();
      output.flush();
    }

    output.close();
    input.close();
    log.info("Forth-интерпретатор завершил работу");
  }
}