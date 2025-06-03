package forth;

import forth.commands.Command;

public class Interpreter {
    private final Context context;
    //TODO: StringBuilder instance

    public Interpreter() {
        context = new Context();
    }

    public void interpret(String input, StringBuilder output) {
        if (input.isEmpty()) {
            return;
        }
        String[] commands = input.split(" ");
        for (String command: commands) {
            try {
                context.parsedInt = Integer.parseInt(command);
                command = "pushNumber";
            } catch (NumberFormatException _ignored) {
                //noinspection EmptyCatchBlock
            }

            Command cmd = CommandFactory.create(command);
            output.append(cmd.execute(context)).append(" ");
        }
    }
}