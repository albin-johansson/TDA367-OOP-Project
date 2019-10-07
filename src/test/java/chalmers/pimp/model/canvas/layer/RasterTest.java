package chalmers.pimp.model.canvas.layer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RasterTest {

  private ILayer raster;

  @BeforeEach
  private void setUp() {
    raster = new Raster(10, 10);
  }

  @Test
  void setPixel() {
    assertThrows(NullPointerException.class, () -> raster.setPixel(null));
  }

  @Test
  void setVisible() {
    final boolean isVisible = false;
    raster.setVisible(isVisible);
    assertEquals(isVisible, raster.isVisible());
  }

  @Test
  void isVisible() {
    // raster should delegate the isVisible-call
    assertEquals(LayerDelegate.DEFAULT_VISIBILITY_VALUE, raster.isVisible());

    final boolean isVisible = true;
    raster.setVisible(isVisible);
    assertEquals(isVisible, raster.isVisible());
  }

  @Test
  void getX() {
    assertEquals(0, raster.getX());

    final int x = 312;
    raster.setX(x);
    assertEquals(x, raster.getX());
  }

  @Test
  void getY() {
    assertEquals(0, raster.getY());

    final int y = 916;
    raster.setY(y);
    assertEquals(y, raster.getY());
  }

  @Test
  void getPixelData() {
    assertNotNull(raster.getPixelData());
  }

  @Test
  void getLayerType() {
    assertEquals(LayerType.RASTER, raster.getLayerType());
  }

  @Test
  void setName() {
    final String setName = "name";
    raster.setName(setName);
    assertEquals(setName, raster.getName());
  }

  @Test
  void getName() {
    assertNotNull(raster.getName());

    final String setName = "name";
    raster.setName(setName);
    assertEquals(setName, raster.getName());
  }
}