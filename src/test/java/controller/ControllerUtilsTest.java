package controller;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ControllerUtilsTest {

  @Test
  void makeController() {
    assertThrows(NullPointerException.class, () -> ControllerUtils.makeController(null, null));
  }
}