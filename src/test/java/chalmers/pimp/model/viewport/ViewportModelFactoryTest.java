package chalmers.pimp.model.viewport;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class ViewportModelFactoryTest {

  @Test
  void createViewportModel() {
    assertNotNull(ViewportModelFactory.createViewportModel());
  }
}