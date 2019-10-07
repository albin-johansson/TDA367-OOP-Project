package chalmers.pimp.model.pixeldata;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PixelDataTest {

  private PixelData pixelData;

  @BeforeEach
  private void setUp() {
    pixelData = new PixelData(100, 100);
  }

  @Test
  void getPixelsTest() {
    assertNotNull(pixelData.getPixels(), "Should not return a null object");
  }

  @Test
  void setPixelTest() {
    assertThrows(NullPointerException.class, () -> pixelData.setPixel(null));

    IPixel pixel = new PixelImpl(0, 0);
    pixelData.setPixel(pixel);
    IReadOnlyPixel firstPixel = pixelData.getPixel(0, 0);
    assertEquals(firstPixel, pixel);
  }

  @Test
  void getPixelTest() {
    assertDoesNotThrow(() -> pixelData.getPixel(0, 0));
    assertThrows(IndexOutOfBoundsException.class, () -> pixelData.getPixel(0, 100));
    assertThrows(IndexOutOfBoundsException.class, () -> pixelData.getPixel(100, 0));
    assertThrows(IndexOutOfBoundsException.class, () -> pixelData.getPixel(100, 100));
  }

  @Test
  void constructorTest() {
    assertThrows(IndexOutOfBoundsException.class, () -> new PixelData(-1, -1));
    assertThrows(IndexOutOfBoundsException.class, () -> new PixelData(0, 0));
    assertDoesNotThrow(() -> new PixelData(100, 100));
  }
}
