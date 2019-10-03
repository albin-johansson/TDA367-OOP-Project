package chalmers.pimp.model.canvas.layer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import chalmers.pimp.model.pixeldata.PixelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RectangleTest {

  private ILayer rectangle;

  @BeforeEach
  private void setUp() {
    rectangle = new Rectangle(0, 0, 10, 10);
  }

  @Test
  void setPixel() {
    // Should not actually manipulate anything.
    assertDoesNotThrow(() -> rectangle.setPixel(PixelFactory.createPixel(0, 0)));
  }

  @Test
  void setVisible() {
    final boolean isVisible = false;
    rectangle.setVisible(isVisible);
    assertEquals(isVisible, rectangle.isVisible());
  }

  @Test
  void isVisible() {
    assertEquals(LayerDelegate.DEFAULT_VISIBILITY_VALUE, rectangle.isVisible());

    final boolean isVisible = true;
    rectangle.setVisible(isVisible);
    assertEquals(isVisible, rectangle.isVisible());
  }

  @Test
  void getX() {
    assertEquals(0, rectangle.getX());

    final int x = 312;
    rectangle.setX(x);
    assertEquals(x, rectangle.getX());
  }

  @Test
  void getY() {
    assertEquals(0, rectangle.getY());

    final int y = 916;
    rectangle.setY(y);
    assertEquals(y, rectangle.getY());
  }

  @Test
  void getPixelData() {
    assertNotNull(rectangle.getPixelData());
  }

  @Test
  void setName() {
    final String setName = "name";
    rectangle.setName(setName);
    assertEquals(setName, rectangle.getName());
  }

  @Test
  void getName() {
    assertNotNull(rectangle.getName());

    final String setName = "name";
    rectangle.setName(setName);
    assertEquals(setName, rectangle.getName());
  }
}