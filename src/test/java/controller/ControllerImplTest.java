package controller;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ControllerImplTest {

  @Test
  void ctor() {
    assertThrows(NullPointerException.class, () -> new ControllerImpl(null, null, null));
  }
}