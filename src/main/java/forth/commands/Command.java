package forth.commands;

import forth.Context;

public interface Command {
    String execute(Context context);
}