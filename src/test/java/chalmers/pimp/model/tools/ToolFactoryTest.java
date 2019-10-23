package chalmers.pimp.model.tools;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ToolFactoryTest {

  @Test
  void createRasterPen() {
    assertThrows(NullPointerException.class, () -> ToolFactory.createRasterPen(0, null));
    assertThrows(NullPointerException.class, () -> ToolFactory.createRasterPen(-1, null));
  }

  @Test
  void createRasterEraser() {
    assertThrows(NullPointerException.class, () -> ToolFactory.createRasterEraser(1, null));
    assertThrows(NullPointerException.class, () -> ToolFactory.createRasterEraser(0, null));
  }

  @Test
  void createMoveTool() {
    assertThrows(NullPointerException.class, () -> ToolFactory.createMoveTool(null));
  }

  @Test
  void createRotateTool() {
    assertThrows(NullPointerException.class, () -> ToolFactory.createRotateTool(null));
    assertThrows(NullPointerException.class, () -> ToolFactory.createRotateTool(null));
  }

  @Test
  void createShapeTool() {
    assertThrows(NullPointerException.class, () -> ToolFactory.createShapeTool(null));
    assertThrows(NullPointerException.class, () -> ToolFactory.createShapeTool(null));
  }

  @Test
  void createDoodleTool() {
    assertThrows(NullPointerException.class, () -> ToolFactory.createDoodleTool(1, null));
    assertThrows(IllegalArgumentException.class, () -> ToolFactory.createDoodleTool(0, null));
  }
}