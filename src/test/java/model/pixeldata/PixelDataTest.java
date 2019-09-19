package model.pixeldata;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Color;
import org.junit.jupiter.api.Test;

public class PixelDataTest {

  @Test
  void getPixelsTest() {
    PixelData pixelData = new PixelData(100, 100);
    Iterable<? extends Iterable<Color>> pixels = pixelData.getPixels();
    assertNotNull(pixels, "Should not return a null object");
  }

  @Test
  void setPixelTest() {
    PixelData pixelData = new PixelData(100, 100);
    pixelData.setPixel(0, 0, Color.BLACK);
    Color firstPixel = pixelData.getPixel(0, 0);
    assertEquals(firstPixel.getRGB(), Color.BLACK.getRGB());
  }

  @Test
  void getPixelTest() {
    PixelData pixelData = new PixelData(100, 100);

    assertDoesNotThrow(() -> {
      pixelData.getPixel(0, 0);
    });

    assertThrows(IndexOutOfBoundsException.class, () -> {
      pixelData.getPixel(0, 100);
    });

    assertThrows(IndexOutOfBoundsException.class, () -> {
      pixelData.getPixel(100, 0);
    });

    assertThrows(IndexOutOfBoundsException.class, () -> {
      pixelData.getPixel(100, 100);
    });
  }

  @Test
  void constructorTest() {
    assertThrows(IndexOutOfBoundsException.class, () -> new PixelData(-1, -1));
    assertThrows(IndexOutOfBoundsException.class, () -> new PixelData(0, 0));
    assertDoesNotThrow(() -> new PixelData(100, 100));
  }
}
