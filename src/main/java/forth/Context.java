package forth;

import forth.commands.Command;

import java.util.Stack;

public class Context {
    private final Stack<Integer> numStack;
    public int parsedInt;

//    private final Stack<Class<? extends Command>> cmdStack;
//    private int cmdPointer;

    public Context() {
        numStack = new Stack<>();
//        cmdStack = new Stack<>();
//        cmdPointer = 0;
        parsedInt = 0;
    }

    public void pushNum(int num) {
        numStack.push(num);
    }
    public int popNum() {
        return numStack.pop();
    }
    public int peekNum() {
        return numStack.peek();
    }
    public boolean numStackIsEmpty() {
        return numStack.isEmpty();
    }
    public String printNumStack() {
        return numStack.toString();
    }
}
