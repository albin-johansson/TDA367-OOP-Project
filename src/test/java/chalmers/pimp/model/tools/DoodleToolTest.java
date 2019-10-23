package chalmers.pimp.model.tools;

import static org.junit.jupiter.api.Assertions.assertThrows;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.ModelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DoodleToolTest {

  private int diameter = 12;
  private IModel model;
  private ITool doodle;

  @BeforeEach
  void setUp() {
    model = ModelFactory.createModel();
    doodle = new DoodleTool(diameter, model);
  }

  @Test
  void ctor() {
    assertThrows(NullPointerException.class, () -> new DoodleTool(1, null));
    assertThrows(IllegalArgumentException.class, () -> new DoodleTool(0, null));
  }

  @Test
  void pressed() {
    assertThrows(NullPointerException.class, () -> doodle.pressed(null));
  }

  @Test
  void dragged() {
    assertThrows(NullPointerException.class, () -> doodle.dragged(null));
  }

  @Test
  void released() {
    assertThrows(NullPointerException.class, () -> doodle.released(null));
  }
}