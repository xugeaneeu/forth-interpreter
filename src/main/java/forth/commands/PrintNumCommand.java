package forth.commands;

import forth.Context;

public class PrintNumCommand implements Command {
    @Override
    public String execute(Context context) {
        int a = context.popNum();
        return Integer.toString(a);
    }
}
