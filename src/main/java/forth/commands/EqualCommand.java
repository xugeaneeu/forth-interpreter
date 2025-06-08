package forth.commands;

import forth.Context;

public class EqualCommand implements Command {
    @Override
    public String execute(Context context) {
        int b = context.popNum();
        int a = context.popNum();
        context.pushNum(a == b ? 1 : 0);
        return "";
    }
}
