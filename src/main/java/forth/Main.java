package forth;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader input = null;
        try {
            if (args.length == 0) {
                input = new BufferedReader(new InputStreamReader(System.in));
            } else if (args.length == 1) {
                input = new BufferedReader(new FileReader("src/main/java/forth/" + args[0]));
            } else {
                System.out.println("Usage: REPL or specify filename");
                return;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file: " + args[0]);
            return;
        }

        Interpreter interpreter = new Interpreter();
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        String inputLine = null;
        StringBuilder outputLine = new StringBuilder();

        while ((inputLine = input.readLine()) != null) {
            interpreter.interpret(inputLine, outputLine); //TODO: RAII - убрать стрингбилдер в интерпретатор
            output.write(outputLine.toString() + '\n');
            output.flush();
            outputLine.setLength(0);
        }

        output.close();
        input.close();
    }
}
