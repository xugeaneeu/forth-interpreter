package forth.commands;

import forth.Context;

public class NumberCommand implements Command {
    @Override
    public String execute(Context context) {
        context.pushNum(context.parsedInt);
        return "";
    }
}
