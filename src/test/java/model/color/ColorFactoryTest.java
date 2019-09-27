package model.color;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class ColorFactoryTest {

  @Test
  void createColor0() {
    IColor color = ColorFactory.createColor(10, 10, 10, 1);
    assertNotNull(color);
  }
}