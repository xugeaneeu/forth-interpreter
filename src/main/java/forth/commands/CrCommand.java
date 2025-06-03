package forth.commands;

import forth.Context;

public class CrCommand implements Command {
    @Override
    public String execute(Context context) {
        return "\n";
    }
}
