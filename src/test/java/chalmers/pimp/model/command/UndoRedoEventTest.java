package chalmers.pimp.model.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UndoRedoEventTest {

  private static final String UNDO_NAME = "UNDO_COMMAND";
  private static final String REDO_NAME = "REDO_COMMAND";

  private UndoRedoEvent event;
  private boolean isUndoable;
  private boolean isRedoable;

  @BeforeEach
  void setUp() {
    event = new UndoRedoEvent();

    var rnd = new Random(System.currentTimeMillis());
    isUndoable = rnd.nextBoolean();
    isRedoable = rnd.nextBoolean();

    event.setUndoable(isUndoable);
    event.setRedoable(isRedoable);
  }

  @Test
  void setUndoable() {
    event.setUndoable(isUndoable);
    assertEquals(isUndoable, event.isUndoable());
  }

  @Test
  void setRedoable() {
    event.setRedoable(isRedoable);
    assertEquals(isRedoable, event.isRedoable());
  }

  @Test
  void setUndoCommandName() {
    assertThrows(NullPointerException.class, () -> event.setUndoCommandName(null));

    event.setUndoCommandName(UNDO_NAME);
    assertEquals(UNDO_NAME, event.getUndoCommandName());
  }

  @Test
  void setRedoCommandName() {
    assertThrows(NullPointerException.class, () -> event.setRedoCommandName(null));

    event.setRedoCommandName(REDO_NAME);
    assertEquals(REDO_NAME, event.getRedoCommandName());
  }

  @Test
  void getUndoCommandName() {
    assertEquals(UndoRedoEvent.DEFAULT_COMMAND_NAME, event.getUndoCommandName());

    event.setUndoCommandName(UNDO_NAME);
    assertEquals(UNDO_NAME, event.getUndoCommandName());
  }

  @Test
  void getRedoCommandName() {
    assertEquals(UndoRedoEvent.DEFAULT_COMMAND_NAME, event.getRedoCommandName());

    event.setRedoCommandName(REDO_NAME);
    assertEquals(REDO_NAME, event.getRedoCommandName());
  }

  @Test
  void isUndoable() {
    assertEquals(isUndoable, event.isUndoable());
  }

  @Test
  void isRedoable() {
    assertEquals(isRedoable, event.isRedoable());
  }
}