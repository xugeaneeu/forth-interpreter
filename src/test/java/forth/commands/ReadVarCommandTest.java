package forth.commands;

import forth.Context;
import forth.exceptions.ForthMemoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadVarCommandTest {

  private Context ctx;
  private Command cmd;

  @BeforeEach
  void setUp() {
    ctx = new Context();
    cmd = new ReadVarCommand();

    ctx.nextFreeAddress = 5;
    ctx.memory = new int[10];
    ctx.memory[2] = 123;
    ctx.memory[4] = 999;
  }

  @Test
  void withValidAddress() {
    ctx.pushNum(2);
    String ret = cmd.execute(ctx);

    assertEquals("", ret);
    assertEquals(1, ctx.numStackSize());
    assertEquals(123, ctx.popNum());
  }

  @Test
  void withAddressEqualNextFree() {
    ctx.pushNum(5);

    ForthMemoryException ex = assertThrows(
            ForthMemoryException.class,
            () -> cmd.execute(ctx)
    );
    assertTrue(ex.getMessage().contains("Invalid address: 5"));
  }

  @Test
  void withAddressGreaterThanNextFree() {
    ctx.pushNum(8);

    ForthMemoryException ex = assertThrows(
            ForthMemoryException.class,
            () -> cmd.execute(ctx)
    );
    assertTrue(ex.getMessage().contains("Invalid address: 8"));
  }
}