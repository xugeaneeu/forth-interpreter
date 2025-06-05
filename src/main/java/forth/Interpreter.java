package forth;

import forth.commands.Command;

public class Interpreter {
  private final Context context;
  private final StringBuilder output;

  public Interpreter() {
    context = new Context();
    output = new StringBuilder();
  }

  public String interpret(String input) {
    if (input.isEmpty()) {
      return null;
    }
    output.setLength(0);

    String[] commands = input.split(" ");
    String command;
    for (int i = 0; i < commands.length; i++) {
      command = commands[i];

      // Check if command is number
      try {
        context.parsedInt = Integer.parseInt(command);
        command = "pushNumber";
      } catch (NumberFormatException _ignored) {
        //noinspection EmptyCatchBlock
      }

      // Parsing string for printing
      if (command.equals(".\"")) {
        StringBuilder sb = new StringBuilder();
        i++;
        while (i < commands.length) {
          sb.append(commands[i]);
          if (commands[i].endsWith("\"")) {
            break;
          }
          sb.append(" ");
          i++;
        }
        if (i == commands.length && !commands[i-1].endsWith("\"")) {
          throw new RuntimeException("Syntax error: .\" without ending \"");
        }
        context.parsedString = sb.substring(0, sb.toString().length() - 1);
        command = "printString";
      }

      //Processing VARIABLE command
      if (command.equals("VARIABLE")) {
        if (i + 1 >= commands.length) {
          throw new RuntimeException("VARIABLE without name");
        }
        context.parsedVariable = commands[++i];
      }

      // Check if command is a name of variable
      if (context.variables.containsKey(command)) {
        context.parsedVariable = command;
        command = "pushVarAddr";
      }

      Command cmd = CommandFactory.create(command);
      output.append(cmd.execute(context)).append(" ");
    }
    return output.toString();
  }
}