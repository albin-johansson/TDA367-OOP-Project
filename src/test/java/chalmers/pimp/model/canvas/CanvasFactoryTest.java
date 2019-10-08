package chalmers.pimp.model.canvas;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class CanvasFactoryTest {

  @Test
  void createCanvas() {
    assertNotNull(CanvasFactory.createCanvas());
  }
}