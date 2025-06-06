package forth.commands;

import forth.Context;

public class BeginCommand implements Command {
  @Override
  public String execute(Context context) {
    context.pushPc(context.currentPC);
    return "";
  }
}
