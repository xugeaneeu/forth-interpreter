package forth.commands;

import forth.Context;

public class RotCommand implements Command {
    @Override
    public String execute(Context context) {
        int c = context.popNum();
        int b = context.popNum();
        int a = context.popNum();
        context.pushNum(c);
        context.pushNum(a);
        context.pushNum(b);
        return "";
    }
}
