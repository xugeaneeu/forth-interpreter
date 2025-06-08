package forth.commands;

import forth.Context;
import forth.exceptions.ForthMemoryException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PushVarAddrCommandTest {

  @Test
  void pushVarAddrCommand() {
    // подготовка context
    Context ctx = new Context();
    ctx.variables.put("X", 42);
    ctx.parsedVariable = "X";

    Command cmd = new PushVarAddrCommand();
    String ret = cmd.execute(ctx);

    assertEquals("", ret);
    assertEquals(1, ctx.numStackSize());
    assertEquals("[42]", ctx.printNumStack());
    assertEquals("", ctx.parsedVariable);
  }

  @Test
  void pushVarAddrCommand_unknownVariable() {
    Context ctx = new Context();
    ctx.parsedVariable = "UNKNOWN";

    PushVarAddrCommand cmd = new PushVarAddrCommand();

    ForthMemoryException ex = assertThrows(
            ForthMemoryException.class,
            () -> cmd.execute(ctx)
    );
    assertTrue(ex.getMessage().contains("Variable not found: UNKNOWN"));
  }
}