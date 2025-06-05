package forth;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Context {
  private final Stack<Integer> numStack;
  public int parsedInt;
  public String parsedString;
  public String parsedVariable;

  // Map of variables and storage for it
  public Map<String, Integer> variables;
  public int[] memory;
  public int nextFreeAddress;

  public Context() {
    numStack = new Stack<>();
    parsedInt = 0;
    parsedString = "";
    parsedVariable = "";
    variables = new HashMap<>();
    memory = new int[1024];
    nextFreeAddress = 0;
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
