package chalmers.pimp.model.color;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ColorImplTest {

  private IColor colorImpl;
  private int val;
  private double percentage;

  @BeforeEach
  void setUp() {
    colorImpl = new ColorImpl(0, 0, 0, 200);
    val = 10;
    percentage = val / 255.0;
  }

  @Test
  void setColor() {
  }

  @Test
  void setRed() {
    colorImpl = colorImpl.setRed(val);
    assertEquals(colorImpl.getRed(), val);
    assertEquals(colorImpl.getRedPercentage(), percentage);
    colorImpl = colorImpl.setRed(-val);
    assertEquals(colorImpl.getRed(), 0);
    colorImpl = colorImpl.setRed(256);
    assertEquals(colorImpl.getRed(), 255);
  }

  @Test
  void setGreen() {
    colorImpl = colorImpl.setGreen(val);
    assertEquals(colorImpl.getGreen(), val);
    assertEquals(colorImpl.getGreenPercentage(), percentage);
    colorImpl = colorImpl.setGreen(-val);
    assertEquals(colorImpl.getGreen(), 0);
    colorImpl = colorImpl.setGreen(256);
    assertEquals(colorImpl.getGreen(), 255);
  }

  @Test
  void setBlue() {
    colorImpl = colorImpl.setBlue(val);
    assertEquals(colorImpl.getBlue(), val);
    assertEquals(colorImpl.getBluePercentage(), percentage);
    colorImpl = colorImpl.setBlue(-val);
    assertEquals(colorImpl.getBlue(), 0);
    colorImpl = colorImpl.setBlue(256);
    assertEquals(colorImpl.getBlue(), 255);
  }

  @Test
  void setAlpha() {
    colorImpl = colorImpl.setAlpha(val);
    assertEquals(colorImpl.getAlpha(), val);
    assertEquals(colorImpl.getAlphaPercentage(), percentage);
    colorImpl = colorImpl.setAlpha(-val);
    assertEquals(colorImpl.getAlpha(), 0);
    colorImpl = colorImpl.setAlpha(256);
    assertEquals(colorImpl.getAlpha(), 255);
  }
}