package model.color;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ColorImpTest {

  private ColorImp colorImp;
  private int val;
  private double percentage;

  @BeforeEach
  void setUp() {
    colorImp = new ColorImp(0, 0, 0, 1);
    val = 10;
    percentage = val / 255.0;
  }

  @Test
  void setColor() {
  }

  @Test
  void setRed() {
    colorImp.setRed(val);
    assertEquals(colorImp.getRed(), val);
    assertEquals(colorImp.getRedPercentage(), percentage);
    colorImp.setRed(-val);
    assertEquals(colorImp.getRed(), 0);
    colorImp.setRed(256);
    assertEquals(colorImp.getRed(), 255);
  }

  @Test
  void setGreen() {
    colorImp.setGreen(val);
    assertEquals(colorImp.getGreen(), val);
    assertEquals(colorImp.getGreenPercentage(), percentage);
    colorImp.setGreen(-val);
    assertEquals(colorImp.getGreen(), 0);
    colorImp.setGreen(256);
    assertEquals(colorImp.getGreen(), 255);
  }

  @Test
  void setBlue() {
    colorImp.setBlue(val);
    assertEquals(colorImp.getBlue(), val);
    assertEquals(colorImp.getBluePercentage(), percentage);
    colorImp.setBlue(-val);
    assertEquals(colorImp.getBlue(), 0);
    colorImp.setBlue(256);
    assertEquals(colorImp.getBlue(), 255);
  }

  @Test
  void setAlpha() {
    colorImp.setAlpha(val);
    assertEquals(colorImp.getAlpha(), val);
    assertEquals(colorImp.getAlphaPercentage(), percentage);
    colorImp.setAlpha(-val);
    assertEquals(colorImp.getAlpha(), 0);
    colorImp.setAlpha(256);
    assertEquals(colorImp.getAlpha(), 255);
  }
}