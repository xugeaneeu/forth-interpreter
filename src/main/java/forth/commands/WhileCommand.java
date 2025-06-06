package forth.commands;

import forth.Context;
import forth.exceptions.ParsingException;

public class WhileCommand implements Command {
  @Override
  public String execute(Context context) {
    if (context.numStackIsEmpty()) {
      throw new ParsingException("Data stack is empty, error in loop syntax");
    }

    context.whileState = (context.popNum() == 1) ? 1 : 0;
    return "";
  }
}
