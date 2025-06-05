package forth.commands;

import forth.Context;

public class CreateVarCommand implements Command {
  @Override
  public String execute(Context context) {
    if (context.variables.containsKey(context.parsedVariable)) {
      throw new RuntimeException("Variable already exists: " + context.parsedVariable);
    }
    if (context.nextFreeAddress == 1024) {
      throw new RuntimeException("Not enough space in memory for variables");
    }

    context.variables.put(context.parsedVariable, context.nextFreeAddress);
    context.parsedVariable = "";
    context.nextFreeAddress++;
    return "";
  }
}
