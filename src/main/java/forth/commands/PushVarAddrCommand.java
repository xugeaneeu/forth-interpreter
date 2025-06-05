package forth.commands;

import forth.Context;

public class PushVarAddrCommand implements Command {
  @Override
  public String execute(Context context) {
    if (!context.variables.containsKey(context.parsedVariable)) {
      throw new RuntimeException("Variable not found: " + context.parsedVariable);
    }

    context.pushNum(context.variables.get(context.parsedVariable));
    context.parsedVariable = "";
    return "";
  }
}
