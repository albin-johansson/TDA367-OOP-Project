package chalmers.pimp.model.pixeldata;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chalmers.pimp.model.color.ColorFactory;
import chalmers.pimp.model.color.IReadOnlyColor;
import org.junit.jupiter.api.Test;

class PixelFactoryTest {

  @Test
  void createPixel() {
    assertNotNull(PixelFactory.createPixel(0, 0));

    final int x = 124;
    final int y = 941;
    IPixel pixel = PixelFactory.createPixel(x, y);

    assertEquals(x, pixel.getX());
    assertEquals(y, pixel.getY());
  }

  @Test
  @SuppressWarnings("MagicNumber")
  void createPixel1() {
    assertDoesNotThrow(() -> PixelFactory.createPixel(0, 0, -0.1, 0.0, 0.0));
    assertDoesNotThrow(() -> PixelFactory.createPixel(0, 0, 0.0, -0.8, 0.0));
    assertDoesNotThrow(() -> PixelFactory.createPixel(0, 0, 0.0, 0.0, 1.1));

    final int x = 819;
    final int y = 423;
    final double red = 0.8;
    final double green = 0.1;
    final double blue = 0.3;

    IPixel pixel = PixelFactory.createPixel(x, y, red, green, blue);

    assertEquals(x, pixel.getX());
    assertEquals(y, pixel.getY());
    assertEquals(red, pixel.getColor().getRedPercentage(), 0.01);
    assertEquals(green, pixel.getColor().getGreenPercentage(), 0.01);
    assertEquals(blue, pixel.getColor().getBluePercentage(), 0.01);
    assertEquals(1, pixel.getColor().getAlphaPercentage(), 0.01); // should be opaque
  }

  @Test
  void createPixel2() {
    assertThrows(NullPointerException.class, () -> PixelFactory.createPixel(0, 0, null));

    IReadOnlyColor color = ColorFactory.createColor();

    final int x = 812;
    final int y = 192;
    IPixel pixel = PixelFactory.createPixel(x, y, color);

    assertEquals(x, pixel.getX());
    assertEquals(y, pixel.getY());
    assertEquals(color, pixel.getColor());
  }

  @Test
  void createPixelWithOffset() {
    assertThrows(NullPointerException.class, () -> PixelFactory.createPixelWithOffset(null, 0, 0));

    final int originalX = 9125;
    final int originalY = -1245;
    IPixel originalPixel = PixelFactory.createPixel(originalX, originalY);

    final int dx = 1124;
    final int dy = 996;
    IPixel offsetPixel = PixelFactory.createPixelWithOffset(originalPixel, dx, dy);

    assertEquals(originalX + dx, offsetPixel.getX());
    assertEquals(originalY + dy, offsetPixel.getY());
    assertEquals(originalPixel.getColor(), offsetPixel.getColor());
  }
}