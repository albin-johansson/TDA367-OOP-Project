package model.canvas.layer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class LayerFactoryTest {

  @Test
  void createRasterLayer() {
    assertThrows(IndexOutOfBoundsException.class, () -> LayerFactory.createRasterLayer(-1, -1));
    assertNotNull(LayerFactory.createRasterLayer(10, 10));
  }

  @Test
  void createRectangle() {
    assertNotNull(LayerFactory.createRectangle(0, 0, 10, 10));
  }
}