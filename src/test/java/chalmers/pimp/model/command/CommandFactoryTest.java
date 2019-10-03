package chalmers.pimp.model.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CommandFactoryTest {

  @Test
  void createStrokeCommand() {
    assertThrows(NullPointerException.class, () -> CommandFactory.createStrokeCommand(null, null));
  }
}