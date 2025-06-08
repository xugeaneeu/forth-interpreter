package forth.commands;

import forth.Context;

public class DropCommand implements Command {
    @Override
    public String execute(Context context) {
        context.popNum();
        return "";
    }
}
