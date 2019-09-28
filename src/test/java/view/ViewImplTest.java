package view;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ViewImplTest {

  @Test
  void ctor() {
    assertThrows(NullPointerException.class, () -> new ViewImpl(null));
  }
}