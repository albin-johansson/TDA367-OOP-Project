package chalmers.pimp.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class ModelFactoryTest {

  @Test
  void createModel() {
    assertNotNull(ModelFactory.createModel());
  }
}