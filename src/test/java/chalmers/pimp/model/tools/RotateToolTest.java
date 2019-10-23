package chalmers.pimp.model.tools;

import static org.junit.jupiter.api.Assertions.assertThrows;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.ModelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RotateToolTest {

  private ITool rotateTool;

  @BeforeEach
  void setUp() {
    IModel model = ModelFactory.createModel();
    rotateTool = new RotateTool(model);
  }

  @Test
  void ctor() {
    assertThrows(NullPointerException.class, () -> new RotateTool(null));
  }

  @Test
  void pressed() {
    assertThrows(NullPointerException.class, () -> rotateTool.pressed(null));
  }

  @Test
  void dragged() {
    assertThrows(NullPointerException.class, () -> rotateTool.dragged(null));
  }

  @Test
  void released() {
    assertThrows(NullPointerException.class, () -> rotateTool.released(null));
  }
}