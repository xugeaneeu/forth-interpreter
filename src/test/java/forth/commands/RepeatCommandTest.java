package forth.commands;

import forth.Context;
import forth.exceptions.ParsingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepeatCommandTest {
  private Context ctx;
  private RepeatCommand cmd;

  @BeforeEach
  void setUp() {
    ctx = new Context();
    cmd = new RepeatCommand();
  }

  @Test
  void whenControlFlowStackEmpty() {
    ParsingException ex = assertThrows(
            ParsingException.class,
            () -> cmd.execute(ctx)
    );
    assertEquals("Error in loop syntax, control flow stack is empty", ex.getMessage());
  }

  @Test
  void firstRepeat_pushThenPopScenario_setsWhileStateAndRemovesPc() {
    ctx.pushPc(7);
    ctx.currentPC = 3;
    ctx.whileState = 0;

    String ret = cmd.execute(ctx);
    assertEquals("", ret);

    assertEquals(1, ctx.whileState);
    assertTrue(ctx.controlFlowStackIsEmpty());
  }

  @Test
  void execute_secondRepeat_withWhileState1_jumpsToSavedPc() {
    ctx.pushPc(42);
    ctx.currentPC = 10;
    ctx.whileState = 1;

    String ret = cmd.execute(ctx);
    assertEquals("", ret);

    assertEquals(42, ctx.currentPC);
    assertEquals(1, ctx.whileState);
    assertFalse(ctx.controlFlowStackIsEmpty());
  }
}