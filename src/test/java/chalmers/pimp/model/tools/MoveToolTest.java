package chalmers.pimp.model.tools;

import static org.junit.jupiter.api.Assertions.assertThrows;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.ModelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MoveToolTest {

  private IModel model;
  private ITool moveTool;

  @BeforeEach
  void setUp() {
    model = ModelFactory.createModel();
    moveTool = new MoveTool(model);
  }

  @Test
  void ctor() {
    assertThrows(NullPointerException.class, () -> new MoveTool(null));
  }

  @Test
  void pressed() {
    assertThrows(NullPointerException.class, () -> moveTool.pressed(null));
  }

  @Test
  void dragged() {
    assertThrows(NullPointerException.class, () -> moveTool.dragged(null));
  }

  @Test
  void released() {
    assertThrows(NullPointerException.class, () -> moveTool.released(null));
  }
}