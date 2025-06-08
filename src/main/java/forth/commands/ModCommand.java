package forth.commands;

import forth.Context;

public class ModCommand implements Command {
    @Override
    public String execute(Context context) {
        int b = context.popNum();
        if (b == 0) {
            throw new ArithmeticException("Divide by zero");
        }
        int a = context.popNum();
        context.pushNum(a % b);
        return "";
    }
}
