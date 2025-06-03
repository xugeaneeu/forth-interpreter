package forth.commands;

import forth.Context;

public class PrintStringCommand implements Command {
    @Override
    public String execute(Context context) {
        String out = context.parsedString;
        context.parsedString = "";
        return out;
    }
}
