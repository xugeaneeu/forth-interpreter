package forth.commands;

import forth.Context;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BaseCommandsTest {

  @Test
  void minusCommand() {
    Context ctx = new Context();
    ctx.pushNum(10);
    ctx.pushNum(4);
    Command cmd = new MinusCommand();
    String ret = cmd.execute(ctx);
    assertEquals("", ret);
    assertEquals(6, ctx.popNum());
  }

  @Test
  void plusCommand() {
    Context ctx = new Context();
    ctx.pushNum(10);
    ctx.pushNum(4);
    Command cmd = new PlusCommand();
    String ret = cmd.execute(ctx);
    assertEquals("", ret);
    assertEquals(14, ctx.popNum());
  }

  @Test
  void multiplyCommand() {
    Context ctx = new Context();
    ctx.pushNum(10);
    ctx.pushNum(4);
    Command cmd = new MultiplyCommand();
    String ret = cmd.execute(ctx);
    assertEquals("", ret);
    assertEquals(40, ctx.popNum());
  }

  @Test
  void modCommand() {
    Context ctx = new Context();
    ctx.pushNum(10);
    ctx.pushNum(4);
    Command cmd = new ModCommand();
    String ret = cmd.execute(ctx);
    assertEquals("", ret);
    assertEquals(2, ctx.popNum());
  }

  @Test
  void divideCommand() {
    Context ctx = new Context();
    ctx.pushNum(10);
    ctx.pushNum(5);
    Command cmd = new DivideCommand();
    String ret = cmd.execute(ctx);
    assertEquals("", ret);
    assertEquals(2, ctx.popNum());
  }

  @Test
  void dropCommand() {
    Context ctx = new Context();
    ctx.pushNum(10);
    ctx.pushNum(5);
    Command cmd = new DropCommand();
    String ret = cmd.execute(ctx);
    assertEquals("", ret);
    assertEquals(1, ctx.numStackSize());
    assertEquals("[10]", ctx.printNumStack());
  }

  @Test
  void dupCommand() {
    Context ctx = new Context();
    ctx.pushNum(10);
    ctx.pushNum(5);
    Command cmd = new DupCommand();
    String ret = cmd.execute(ctx);
    assertEquals("", ret);
    assertEquals(3, ctx.numStackSize());
    assertEquals("[10, 5, 5]", ctx.printNumStack());
  }

  @Test
  void crCommand() {
    Context ctx = new Context();
    Command cmd = new CrCommand();
    String ret = cmd.execute(ctx);
    assertEquals("\n", ret);
  }

  @Test
  void emitCommand() {
    Context ctx = new Context();
    ctx.pushNum(5);
    Command cmd = new EmitCommand();
    String ret = cmd.execute(ctx);
    assertEquals(String.valueOf((char)5), ret);
    assertEquals(0, ctx.numStackSize());
    assertEquals("[]", ctx.printNumStack());
  }

  @Test
  void equalCommand() {
    Context ctx = new Context();
    ctx.pushNum(10);
    ctx.pushNum(5);
    Command cmd = new EqualCommand();
    String ret = cmd.execute(ctx);
    assertEquals("", ret);
    assertEquals(1, ctx.numStackSize());
    assertEquals("[0]", ctx.printNumStack());
    ctx.pushNum(10);
    ctx.pushNum(10);
    cmd = new EqualCommand();
    ret = cmd.execute(ctx);
    assertEquals("", ret);
    assertEquals(2, ctx.numStackSize());
    assertEquals("[0, 1]", ctx.printNumStack());
  }

  @Test
  void greaterCommand() {
    Context ctx = new Context();
    ctx.pushNum(10);
    ctx.pushNum(5);
    Command cmd = new GreaterCommand();
    String ret = cmd.execute(ctx);
    assertEquals("", ret);
    assertEquals(1, ctx.numStackSize());
    assertEquals("[1]", ctx.printNumStack());
    ctx.pushNum(10);
    ctx.pushNum(15);
    cmd = new GreaterCommand();
    ret = cmd.execute(ctx);
    assertEquals("", ret);
    assertEquals(2, ctx.numStackSize());
    assertEquals("[1, 0]", ctx.printNumStack());
  }

  @Test
  void lessCommand() {
    Context ctx = new Context();
    ctx.pushNum(10);
    ctx.pushNum(5);
    Command cmd = new LessCommand();
    String ret = cmd.execute(ctx);
    assertEquals("", ret);
    assertEquals(1, ctx.numStackSize());
    assertEquals("[0]", ctx.printNumStack());
    ctx.pushNum(10);
    ctx.pushNum(15);
    cmd = new LessCommand();
    ret = cmd.execute(ctx);
    assertEquals("", ret);
    assertEquals(2, ctx.numStackSize());
    assertEquals("[0, 1]", ctx.printNumStack());
  }

  @Test
  void overCommand() {
    Context ctx = new Context();
    ctx.pushNum(10);
    ctx.pushNum(5);
    Command cmd = new OverCommand();
    String ret = cmd.execute(ctx);
    assertEquals("", ret);
    assertEquals(3, ctx.numStackSize());
    assertEquals("[10, 5, 10]", ctx.printNumStack());
  }

  @Test
  void printNumCommand() {
    Context ctx = new Context();
    ctx.pushNum(5);
    Command cmd = new PrintNumCommand();
    String ret = cmd.execute(ctx);
    assertEquals(String.valueOf(5), ret);
    assertEquals(0, ctx.numStackSize());
    assertEquals("[]", ctx.printNumStack());
  }

  @Test
  void printStringCommand() {
    Context ctx = new Context();
    ctx.parsedString = "Hello World";
    Command cmd = new PrintStringCommand();
    String ret = cmd.execute(ctx);
    assertEquals("Hello World", ret);
    assertEquals("", ctx.parsedString);
    assertEquals(0, ctx.numStackSize());
    assertEquals("[]", ctx.printNumStack());
  }

  @Test
  void rotCommand() {
    Context ctx = new Context();
    ctx.pushNum(1);
    ctx.pushNum(2);
    ctx.pushNum(3);
    Command cmd = new RotCommand();
    String ret = cmd.execute(ctx);
    assertEquals("", ret);
    assertEquals(3, ctx.numStackSize());
    assertEquals("[3, 1, 2]", ctx.printNumStack());
  }

  @Test
  void swapCommand() {
    Context ctx = new Context();
    ctx.pushNum(1);
    ctx.pushNum(2);
    Command cmd = new SwapCommand();
    String ret = cmd.execute(ctx);
    assertEquals("", ret);
    assertEquals(2, ctx.numStackSize());
    assertEquals("[2, 1]", ctx.printNumStack());
  }

  @Test
  void beginCommand() {
    Context ctx = new Context();
    ctx.currentPC = 1;
    Command cmd = new BeginCommand();
    String ret = cmd.execute(ctx);
    assertEquals("", ret);
    assertEquals(1, ctx.controlFlowStackSize());
    assertEquals("[1]", ctx.printControlFlowStack());
  }

  @Test
  void changeVarCommand() {
    Context ctx = new Context();
    ctx.pushNum(5);
    ctx.pushNum(0);
    Command cmd = new ChangeVarCommand();
    String ret = cmd.execute(ctx);
    assertEquals("", ret);
    assertEquals(0, ctx.numStackSize());
    assertEquals(5, ctx.memory[0]);
  }

  @Test
  void numberCommand() {
    Context ctx = new Context();
    ctx.parsedInt = 5;
    Command cmd = new NumberCommand();
    String ret = cmd.execute(ctx);
    assertEquals("", ret);
    assertEquals(1, ctx.numStackSize());
    assertEquals("[5]", ctx.printNumStack());
  }
}