package forth.commands;

import forth.Context;
import forth.exceptions.ForthMemoryException;

public class ReadVarCommand implements Command {
  @Override
  public String execute(Context context) {
    int addr = context.popNum();
    if (addr >= context.nextFreeAddress) {
      throw new ForthMemoryException("Invalid address: " + addr);
    }

    context.pushNum(context.memory[addr]);
    return "";
  }
}
