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

    //Comment support
    if (commands[0].equals("/")) {
      return "";
    }

    for (context.currentPC = 0; context.currentPC < commands.length; context.currentPC++) {
      command = commands[context.currentPC];

      if (context.whileState == 0 && !command.equals("REPEAT")) {
        continue;
      }

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
          throw new RuntimeException("Syntax error: .\" without ending \"");
        }
        context.parsedString = sb.substring(0, sb.toString().length() - 1);
        command = "printString";
      }

      //Processing VARIABLE command
      if (command.equals("VARIABLE")) {
        if (context.currentPC + 1 >= commands.length) {
          throw new RuntimeException("VARIABLE without name");
        }
        context.parsedVariable = commands[++context.currentPC];
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