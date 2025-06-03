package forth.commands;

import forth.Context;

public class EmitCommand implements Command {
    @Override
    public String execute(Context context) {
        int a = context.popNum();
        return Character.toString((char)a);
    }
}
