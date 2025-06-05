package forth.commands;

import forth.Context;

public class ReadVarCommand implements Command {
  @Override
  public String execute(Context context) {
    int addr = context.popNum();
    if (addr >= context.nextFreeAddress) {
      throw new RuntimeException("Invalid address: " + addr);
    }

    context.pushNum(context.memory[addr]);
    return "";
  }
}
