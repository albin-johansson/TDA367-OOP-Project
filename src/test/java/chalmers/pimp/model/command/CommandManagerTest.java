package chalmers.pimp.model.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandManagerTest {

  private CommandManager commandManager;
  private MockCommand command;

  @BeforeEach
  void setUp() {
    commandManager = new CommandManager();
    command = new MockCommand();
  }

  @Test
  void insertCommand() {
    assertThrows(NullPointerException.class, () -> commandManager.insertCommand(null));

    // The command shouldn't be executed
    final int before = command.getExecutedAmount();
    commandManager.insertCommand(command);
    assertEquals(before, command.getExecutedAmount());
  }

  @Test
  void undo() {
    final int before = command.getRevertedAmount();
    commandManager.insertCommand(command);

    commandManager.undo();

    final int expected = before + 1;
    assertEquals(expected, command.getRevertedAmount());
  }

  @Test
  void redo() {
    final int before = command.getExecutedAmount();
    commandManager.insertCommand(command);

    commandManager.undo();
    commandManager.redo();

    final int expected = before + 1;
    assertEquals(expected, command.getExecutedAmount());
  }
}