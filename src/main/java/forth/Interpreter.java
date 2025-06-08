package forth;

import forth.commands.Command;
import forth.exceptions.ParsingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Interpreter {
  private static final Logger log = LoggerFactory.getLogger(Interpreter.class);

  private final Context context;
  private final StringBuilder output;

  public Interpreter() {
    context = new Context();
    output = new StringBuilder();
  }

  public String interpret(String input) {
    log.info("Starting interpretation: '{}'", input);

    if (input.isEmpty()) {
      log.info("Received empty input, returning null");
      return null;
    }
    output.setLength(0);

    String[] commands = input.split(" ");
    log.debug("Split input into {} tokens: {}", commands.length, String.join(", ", commands));
    String command;

    //Comment support
    if (commands[0].equals("/")) {
      log.info("Comment detected, skipping execution");
      return "";
    }

    for (context.currentPC = 0; context.currentPC < commands.length; context.currentPC++) {
      command = commands[context.currentPC];
      log.debug("PC={} -> token='{}'", context.currentPC, command);

      if (context.whileState == 0 && !command.equals("REPEAT")) {
        log.debug("Ignoring token '{}' inside REPEAT block", command);
        continue;
      }

      // Check if command is number
      try {
        context.parsedInt = Integer.parseInt(command);
        log.debug("Parsed number: {}", context.parsedInt);
        command = "pushNumber";
      } catch (NumberFormatException _ignored) {
        //noinspection EmptyCatchBlock
      }

      // Parsing string for printing
      if (command.equals(".\"")) {
        log.debug("Starting string literal parsing at position {}", context.currentPC);
        StringBuilder sb = new StringBuilder();
        context.currentPC++;
        while (context.currentPC < commands.length) {
          sb.append(commands[context.currentPC]);
          if (commands[context.currentPC].endsWith("\"")) {
            break;
          }
          sb.append(" ");
          context.currentPC++;
        }
        if (context.currentPC == commands.length && !commands[context.currentPC-1].endsWith("\"")) {
          log.error("Syntax error: .\" without closing quote");
          throw new ParsingException("Syntax error: .\" without ending \"");
        }
        context.parsedString = sb.substring(0, sb.toString().length() - 1);
        log.debug("Parsed string literal: \"{}\"", context.parsedString);
        command = "printString";
      }

      //Processing VARIABLE command
      if (command.equals("VARIABLE")) {
        if (context.currentPC + 1 >= commands.length) {
          log.error("VARIABLE without a name");
          throw new ParsingException("VARIABLE without name");
        }
        context.parsedVariable = commands[++context.currentPC];
        log.debug("Parsed variable name: {}", context.parsedVariable);
      }

      // Check if command is a name of variable
      if (context.variables.containsKey(command)) {
        context.parsedVariable = command;
        log.debug("Detected variable '{}' – pushVarAddr", command);
        command = "pushVarAddr";
      }

      Command cmd = CommandFactory.create(command);
      log.debug("Command implementation: {}", cmd.getClass().getSimpleName());
      log.debug("Num stack before execution: {} \n Control flow stack before execution {}",
                context.printNumStack(), context.printControlFlowStack());
      String executionResult = cmd.execute(context);
      log.debug("Num stack after execution: {} \n Control flow stack after execution {}",
              context.printNumStack(), context.printControlFlowStack());

      log.info("Command execution result='{}'", executionResult);
      output.append(executionResult).append(" ");
    }

    String finalOutput = output.toString().trim();
    log.info("Finished line interpretation, output: '{}'", finalOutput);
    return finalOutput;
  }
}