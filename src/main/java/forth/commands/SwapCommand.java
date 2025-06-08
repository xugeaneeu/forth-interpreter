package forth.commands;

import forth.Context;

public class SwapCommand implements Command {
    @Override
    public String execute(Context context) {
        int b = context.popNum();
        int a = context.popNum();
        context.pushNum(b);
        context.pushNum(a);
        return "";
    }
}
