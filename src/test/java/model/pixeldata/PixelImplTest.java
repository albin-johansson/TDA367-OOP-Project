package model.pixeldata;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PixelImplTest {

  private static final int X = 1205;
  private static final int Y = 295;

  private PixelImpl pixel;

  @BeforeEach
  private void setUp() {
    pixel = new PixelImpl(X, Y);
  }

  @Test
  void getX() {
    assertEquals(X, pixel.getX());
  }

  @Test
  void getY() {
    assertEquals(Y, pixel.getY());
  }

  @Test
  void setRed() {
    final double validRed = 0.8;
    pixel.setRed(validRed);
    assertEquals(validRed, pixel.getRed(), 0.01);

    assertDoesNotThrow(() -> pixel.setRed(-0.1));
    assertEquals(0, pixel.getRed());

    assertDoesNotThrow(() -> pixel.setRed(1.1));
    assertEquals(1, pixel.getRed());
  }

  @Test
  void setGreen() {
    final double validGreen = 0.2;
    pixel.setGreen(validGreen);
    assertEquals(validGreen, pixel.getGreen(), 0.01);

    assertDoesNotThrow(() -> pixel.setGreen(-0.25));
    assertEquals(0, pixel.getGreen());

    assertDoesNotThrow(() -> pixel.setGreen(1.2));
    assertEquals(1, pixel.getGreen());
  }

  @Test
  void setBlue() {
    final double validBlue = 0.38;
    pixel.setBlue(validBlue);
    assertEquals(validBlue, pixel.getBlue(), 0.01);

    assertDoesNotThrow(() -> pixel.setBlue(-0.8));
    assertEquals(0, pixel.getBlue());

    assertDoesNotThrow(() -> pixel.setBlue(1.9));
    assertEquals(1, pixel.getBlue());
  }

  @Test
  void setAlpha() {
    final double validAlpha = 0.63;
    pixel.setAlpha(validAlpha);
    assertEquals(validAlpha, pixel.getAlpha(), 0.01);

    assertDoesNotThrow(() -> pixel.setAlpha(-0.49));
    assertEquals(0, pixel.getAlpha());

    assertDoesNotThrow(() -> pixel.setAlpha(1.25));
    assertEquals(1, pixel.getAlpha());
  }

  @Test
  void getRed() {
    assertEquals(0, pixel.getRed());

    double red = 0.2;
    pixel.setRed(red);

    assertEquals(red, pixel.getRed(), 0.01);
  }

  @Test
  void getGreen() {
    assertEquals(0, pixel.getGreen());

    double green = 0.9;
    pixel.setGreen(green);

    assertEquals(green, pixel.getGreen(), 0.01);
  }

  @Test
  void getBlue() {
    assertEquals(0, pixel.getBlue());

    double blue = 0.5;
    pixel.setBlue(blue);

    assertEquals(blue, pixel.getBlue(), 0.01);
  }

  @Test
  void getAlpha() {
    assertEquals(0, pixel.getAlpha());

    double alpha = 0.8;
    pixel.setAlpha(alpha);

    assertEquals(alpha, pixel.getAlpha(), 0.01);
  }

  @Test
  @SuppressWarnings("all")
  void equalsTest() {
    assertFalse(pixel.equals(null));

    // Should be reflexive
    assertTrue(pixel.equals(pixel));

    // Should be transitive: (a == b) && (b == c) => (a == c)
    IPixel second = new PixelImpl(0, 0);
    IPixel third = new PixelImpl(0, 0);
    assertTrue(pixel.equals(second));
    assertTrue(second.equals(third));
    assertTrue(pixel.equals(third));

    // Should be symmetric: (a == b) <=> (b == a)
    assertTrue(pixel.equals(second));
    assertTrue(second.equals(pixel));

    // Different coordinates but should still be qualified as equal
    IPixel closeEnoughPixel = new PixelImpl(pixel.getX() + 1, pixel.getY() + 1);
    assertTrue(pixel.equals(closeEnoughPixel));
  }

  @Test
  void toStringTest() {
    System.out.println(pixel.toString()); // visual test
  }

  @Test
  void getColor() {
    assertNotNull(pixel.getColor());
  }
}