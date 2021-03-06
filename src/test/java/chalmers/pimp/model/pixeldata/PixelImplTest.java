package chalmers.pimp.model.pixeldata;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
  @SuppressWarnings("all")
  void copyCtor() {
    assertThrows(NullPointerException.class, () -> new PixelImpl(null));

    var copy = new PixelImpl(pixel);

    assertTrue(pixel.equals(copy));

    assertEquals(X, copy.getX());
    assertEquals(Y, copy.getY());

    assertEquals(pixel.getColor().getRed(), copy.getColor().getRed());
    assertEquals(pixel.getColor().getGreen(), copy.getColor().getGreen());
    assertEquals(pixel.getColor().getBlue(), copy.getColor().getBlue());
    assertEquals(pixel.getColor().getAlpha(), copy.getColor().getAlpha());
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
    assertEquals(validRed, pixel.getColor().getRedPercentage(), 0.01);

    assertDoesNotThrow(() -> pixel.setRed(-0.1));
    assertEquals(0, pixel.getColor().getRedPercentage());

    assertDoesNotThrow(() -> pixel.setRed(1.1));
    assertEquals(1, pixel.getColor().getRedPercentage());
  }

  @Test
  void setGreen() {
    final double validGreen = 0.2;
    pixel.setGreen(validGreen);
    assertEquals(validGreen, pixel.getColor().getGreenPercentage(), 0.01);

    assertDoesNotThrow(() -> pixel.setGreen(-0.25));
    assertEquals(0, pixel.getColor().getGreenPercentage());

    assertDoesNotThrow(() -> pixel.setGreen(1.2));
    assertEquals(1, pixel.getColor().getGreenPercentage());
  }

  @Test
  void setBlue() {
    final double validBlue = 0.38;
    pixel.setBlue(validBlue);
    assertEquals(validBlue, pixel.getColor().getBluePercentage(), 0.01);

    assertDoesNotThrow(() -> pixel.setBlue(-0.8));
    assertEquals(0, pixel.getColor().getBluePercentage());

    assertDoesNotThrow(() -> pixel.setBlue(1.9));
    assertEquals(1, pixel.getColor().getBluePercentage());
  }

  @Test
  void setAlpha() {
    final double validAlpha = 0.63;
    pixel.setAlpha(validAlpha);

    assertEquals(validAlpha, pixel.getColor().getAlphaPercentage(), 0.01);

    assertDoesNotThrow(() -> pixel.setAlpha(-0.49));
    assertEquals(0, pixel.getColor().getAlphaPercentage());

    assertDoesNotThrow(() -> pixel.setAlpha(1.25));
    assertEquals(1, pixel.getColor().getAlphaPercentage());
  }

  @Test
  void getRed() {
    assertEquals(0, pixel.getColor().getRedPercentage());

    double red = 0.2;
    pixel.setRed(red);

    assertEquals(red, pixel.getColor().getRedPercentage(), 0.01);
  }

  @Test
  void getGreen() {
    assertEquals(0, pixel.getColor().getGreenPercentage());

    double green = 0.9;
    pixel.setGreen(green);

    assertEquals(green, pixel.getColor().getGreenPercentage(), 0.01);
  }

  @Test
  void getBlue() {
    assertEquals(0, pixel.getColor().getBluePercentage());

    double blue = 0.5;
    pixel.setBlue(blue);

    assertEquals(blue, pixel.getColor().getBluePercentage(), 0.01);
  }

  @Test
  void getAlpha() {
    assertEquals(0, pixel.getColor().getAlphaPercentage());

    double alpha = 0.8;
    pixel.setAlpha(alpha);

    assertEquals(alpha, pixel.getColor().getAlphaPercentage(), 0.01);
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