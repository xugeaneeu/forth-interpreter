package forth.commands;

import forth.Context;
import forth.exceptions.ParsingException;

public class RepeatCommand implements Command {
  @Override
  public String execute(Context context) {
    if (context.controlFlowStackIsEmpty()) {
      throw new ParsingException("Error in loop syntax, control flow stack is empty");
    }

    if (context.whileState == 1) {
      context.currentPC = context.peekPc();
    } else {
      context.whileState = 1;
      context.popPc();
    }
    return "";
  }
}
