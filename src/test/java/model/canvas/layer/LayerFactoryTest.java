package model.canvas.layer;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class LayerFactoryTest {

  @Test
  void createRasterLayer() {
    assertNotNull(LayerFactory.createRasterLayer());
  }
}