package forth;


import java.util.Stack;

public class Context {
    private final Stack<Integer> numStack;
    public int parsedInt;
    public String parsedString;

    public Context() {
        numStack = new Stack<>();
        parsedInt = 0;
        parsedString = "";
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
