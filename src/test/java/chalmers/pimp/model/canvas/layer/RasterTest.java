package chalmers.pimp.model.canvas.layer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

  @Test
  void copy() {
    var copy = raster.copy();
    assertEquals(raster, copy);
  }

  @Test
  void equalsTest() {
    var copy = raster.copy();

    assertNotEquals(null, raster);

    assertEquals(raster, raster); // Reflexive

    // Symmetric
    assertEquals(copy, raster);
    assertEquals(raster, copy);

    var secondCopy = copy.copy();

    // Transitive
    assertEquals(raster, copy);
    assertEquals(copy, secondCopy);
    assertEquals(raster, secondCopy);
  }

  @Test
  void move() {
    final int dx = 29;
    final int dy = -134;

    final int x = raster.getX();
    final int y = raster.getY();

    raster.move(dx, dy);

    assertEquals(x + dx, raster.getX());
    assertEquals(y + dy, raster.getY());
  }

  @Test
  void setX() {
    int x = -255;
    raster.setX(x);

    assertEquals(x, raster.getX());
  }

  @Test
  void setY() {
    int y = 8124;
    raster.setY(y);

    assertEquals(y, raster.getY());
  }

  @Test
  void setDepthIndex() {
    int depthIndex = 8923;
    raster.setDepthIndex(depthIndex);

    assertEquals(depthIndex, raster.getDepthIndex());
  }

  @Test
  void getDepthIndex() {
    int depthIndex = 1294;
    raster.setDepthIndex(depthIndex);

    assertEquals(depthIndex, raster.getDepthIndex());
  }

  @Test
  void getLayerType() {
    assertEquals(raster.getLayerType(), LayerType.RASTER);
  }
}