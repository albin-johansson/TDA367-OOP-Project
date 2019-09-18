package model.pixeldata;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Color;
import org.junit.jupiter.api.Test;

class PixelDataTest {

  @Test
  private void getPixelsTest() {
    PixelData pixelData = new PixelData(100, 100);
    Iterable<? extends Iterable<Color>> pixels = pixelData.getPixels();
    assertNotNull(pixels, "Should not return a null object");
  }

  @Test
  private void setPixelTest() {
    PixelData pixelData = new PixelData(100, 100);
    pixelData.setPixel(0, 0, Color.BLACK);
    Color firstPixel = pixelData.getPixel(0, 0);
    assertEquals(firstPixel.getRGB(), Color.BLACK.getRGB());
  }

}
