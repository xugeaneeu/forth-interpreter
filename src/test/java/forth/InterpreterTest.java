package forth;

import forth.exceptions.CommandNotFoundException;
import forth.exceptions.ParsingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InterpreterTest {
  private final Interpreter interpreter = new Interpreter();

  @Test
  void emptyInput_returnsNull() {
    String result = interpreter.interpret("");
    assertNull(result);
  }

  @Test
  void commentLine_returnsEmptyString() {
    String result = interpreter.interpret("/ this is a comment");
    assertEquals("", result);
  }

  @Test
  void stringLiteral_returnsInnerText() {
    String result = interpreter.interpret(".\" Hello, World! \"");
    assertEquals("Hello, World!", result);
  }

  @Test
  void unterminatedString_throwsParsingException() {
    assertThrows(ParsingException.class,
            () -> interpreter.interpret(".\" unterminated"));
  }

  @Test
  void variableCommandWithoutName_throwsParsingException() {
    assertThrows(ParsingException.class,
            () -> interpreter.interpret("VARIABLE"));
  }

  @Test
  void unknownToken_throwsCommandNotFoundException() {
    assertThrows(CommandNotFoundException.class,
            () -> interpreter.interpret("FOOBAR"));
  }

  @Test
  void loopSyntaxParsing () {
    String input = "1 5 BEGIN DUP 1 > WHILE SWAP OVER * SWAP 1 - REPEAT DROP .";
    String result = interpreter.interpret(input);
    assertEquals("120", result);
  }

  @Test
  void variableSyntaxParsing () {
    String input = "VARIABLE V1 123 V1 ! V1 @ . CR";
    String result = interpreter.interpret(input);
    assertEquals("123", result);
  }
}