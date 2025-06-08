package forth.commands;

import forth.Context;

public class DupCommand implements Command {
    @Override
    public String execute(Context context) {
        int a = context.peekNum();
        context.pushNum(a);
        return "";
    }
}
