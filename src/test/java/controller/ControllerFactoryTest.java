package controller;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ControllerFactoryTest {

  @Test
  void createController() {
    assertThrows(NullPointerException.class, () -> ControllerFactory.createController(null));
  }
}