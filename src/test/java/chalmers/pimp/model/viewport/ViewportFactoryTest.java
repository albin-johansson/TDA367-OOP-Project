package chalmers.pimp.model.viewport;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ViewportFactoryTest {

  @Test
  void createViewport() {
    assertThrows(IllegalArgumentException.class, () -> ViewportFactory.createViewport(0, 0, 0, 0));
    assertDoesNotThrow(() -> ViewportFactory.createViewport(0, 0, 1, 1));
  }
}