package forth.commands;

import forth.Context;
import forth.exceptions.ForthMemoryException;

public class PushVarAddrCommand implements Command {
  @Override
  public String execute(Context context) {
    if (!context.variables.containsKey(context.parsedVariable)) {
      throw new ForthMemoryException("Variable not found: " + context.parsedVariable);
    }

    context.pushNum(context.variables.get(context.parsedVariable));
    context.parsedVariable = "";
    return "";
  }
}
