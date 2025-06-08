package forth.commands;

import forth.Context;
import forth.exceptions.ParsingException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WhileCommandTest {

  @Test
  void withNonEmptyStack() {
    Context ctx = new Context();
    ctx.pushNum(1);
    Command cmd = new WhileCommand();
    cmd.execute(ctx);
    assertEquals(1, ctx.whileState);
    ctx.pushNum(0);
    cmd.execute(ctx);
    assertEquals(0, ctx.whileState);
  }

  @Test
  void withEmptyStack() {
    Context ctx = new Context();
    Command cmd = new WhileCommand();
    ParsingException ex = assertThrows(
            ParsingException.class,
            () -> cmd.execute(ctx)
    );
    assertEquals("Data stack is empty, error in loop syntax", ex.getMessage());
  }
}