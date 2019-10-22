package chalmers.pimp.model.pixeldata;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RasterDataImplTest {

  private RasterDataImpl rasterData;

  @BeforeEach
  private void setUp() {
    rasterData = new RasterDataImpl(100, 100);
  }

  @Test
  void getPixelsTest() {
    assertNotNull(rasterData.getPixels(), "Should not return a null object");
  }

  @Test
  void setPixelTest() {
    assertThrows(NullPointerException.class, () -> rasterData.setPixel(null));

    IPixel pixel = new PixelImpl(0, 0);
    rasterData.setPixel(pixel);
    IReadOnlyPixel firstPixel = rasterData.getPixel(0, 0);
    assertEquals(firstPixel, pixel);
  }

  @Test
  void getPixelTest() {
    assertDoesNotThrow(() -> rasterData.getPixel(0, 0));
    assertThrows(IndexOutOfBoundsException.class, () -> rasterData.getPixel(0, 100));
    assertThrows(IndexOutOfBoundsException.class, () -> rasterData.getPixel(100, 0));
    assertThrows(IndexOutOfBoundsException.class, () -> rasterData.getPixel(100, 100));
  }

  @Test
  void constructorTest() {
    assertThrows(IndexOutOfBoundsException.class, () -> new RasterDataImpl(-1, -1));
    assertThrows(IndexOutOfBoundsException.class, () -> new RasterDataImpl(0, 0));
    assertDoesNotThrow(() -> new RasterDataImpl(100, 100));
  }

  @Test
  void equalsTest() {
    assertNotEquals(null, rasterData);

    assertEquals(rasterData, rasterData); // Reflexive

    var copy = new RasterDataImpl(rasterData);

    // Symmetric
    assertEquals(rasterData, copy);
    assertEquals(copy, rasterData);

    var secondCopy = new RasterDataImpl(copy);

    // Transitive
    assertEquals(rasterData, copy);
    assertEquals(copy, secondCopy);
    assertEquals(rasterData, secondCopy);
  }
}
