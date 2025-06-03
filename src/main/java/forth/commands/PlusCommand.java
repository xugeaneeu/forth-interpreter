package forth.commands;

import forth.Context;

public class PlusCommand implements Command {
    @Override
    public String execute(Context context) {
        int a = context.popNum();
        int b = context.popNum();
        context.pushNum(a + b);
        return context.printNumStack();
    }
}
