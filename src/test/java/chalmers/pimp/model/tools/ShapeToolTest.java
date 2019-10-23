package chalmers.pimp.model.tools;

import static org.junit.jupiter.api.Assertions.assertThrows;

import chalmers.pimp.model.ModelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShapeToolTest {

  private ITool shapeTool;

  @BeforeEach
  void setUp() {
    shapeTool = new ShapeTool(ModelFactory.createModel());
  }

  @Test
  void ctor() {
    assertThrows(NullPointerException.class, () -> new ShapeTool(null));
  }

  @Test
  void dragged() {
    assertThrows(NullPointerException.class, () -> shapeTool.dragged(null));
  }

  @Test
  void pressed() {
    assertThrows(NullPointerException.class, () -> shapeTool.pressed(null));
  }

  @Test
  void released() {
    assertThrows(NullPointerException.class, () -> shapeTool.released(null));
  }
}