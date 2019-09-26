package model.color;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ColorFactoryTest {

  @Test
  void createColor0() {
    IColor color = ColorFactory.createColor(10, 10, 10, 1);
    assertNotNull(color);

    color = ColorFactory.createColor("#FF0000");
    assertEquals(color.getRed(), 255);
    assertEquals(color.getGreen(), 0);
    assertEquals(color.getBlue(), 0);

    color.setColor("f977a1");
    assertEquals(color.getRed(), 249);
    assertEquals(color.getGreen(), 119);
    assertEquals(color.getBlue(), 161);
  }

  /**
   * "Bad params" tests.
   */
  @Test
  @SuppressWarnings("ResultOfMethodCallIgnored")
  void createColor1() {
    assertThrows(NullPointerException.class, () -> ColorFactory.createColor(-1, -1, -1, -1));
    assertThrows(NullPointerException.class, () -> ColorFactory.createColor("FF00GG"));
    assertThrows(NullPointerException.class, () -> ColorFactory.createColor("hello !"));
    assertThrows(NullPointerException.class, () -> ColorFactory.createColor("ffffffffffff"));
  }
}