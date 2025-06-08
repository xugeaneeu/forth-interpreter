package forth.commands;

import forth.Context;

public class OverCommand implements Command {
    @Override
    public String execute(Context context) {
        int b = context.popNum();
        int a = context.peekNum();
        context.pushNum(b);
        context.pushNum(a);
        return "";
    }
}
