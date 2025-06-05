package forth.commands;

import forth.Context;

public class ChangeVarCommand implements Command {
  @Override
  public String execute(Context context) {
    int addr = context.popNum();
    int newVal = context.popNum();

    context.memory[addr] = newVal;
    return "";
  }
}
