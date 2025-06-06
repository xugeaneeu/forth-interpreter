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

  private final Stack<Integer> controlFlowStack;
  public int currentPC;
  public int whileState;

  public Context() {
    numStack = new Stack<>();
    parsedInt = 0;
    parsedString = "";
    parsedVariable = "";
    variables = new HashMap<>();
    memory = new int[1024];
    nextFreeAddress = 0;
    controlFlowStack = new Stack<>();
    currentPC = 0;
    whileState = 1;
  }

  // Interface for numStack
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

  // Interface for controlFlowStack
  public void pushPc(int num) {
    controlFlowStack.push(num);
  }
  public int popPc() {
    return controlFlowStack.pop();
  }
  public int peekPc() {
    return controlFlowStack.peek();
  }
  public boolean controlFlowStackIsEmpty() {
    return controlFlowStack.isEmpty();
  }
  public String printControlFlowStack() {
    return controlFlowStack.toString();
  }
}
