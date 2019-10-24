package chalmers.pimp.model.canvas;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.model.canvas.layer.LayerFactory;
import chalmers.pimp.model.pixeldata.PixelFactory;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CanvasImplTest {

  private ICanvas canvas;
  private ILayer defaultLayer;

  private static ILayer createLayer(int x, int y, int width, int height) {
    ILayer layer = LayerFactory.createRasterLayer(width, height);
    layer.setX(x);
    layer.setY(y);
    return layer;
  }

  @BeforeEach
  private void setUp() {
    canvas = new CanvasImpl();
    defaultLayer = createLayer(12, 84, 54, 37);
  }

  @Test
  void setActiveLayerPixel() {
    assertDoesNotThrow(() -> canvas.setActiveLayerPixel(PixelFactory.createPixel(0, 0)));

    canvas.addLayer(defaultLayer);
    canvas.selectLayer(0);
    assertThrows(NullPointerException.class, () -> canvas.setActiveLayerPixel(null));
  }

  @Test
  void selectLayer() {
    assertDoesNotThrow(() -> canvas.selectLayer(-1));

    canvas.addLayer(defaultLayer);
    assertDoesNotThrow(() -> canvas.selectLayer(0));
  }

  @Test
  void addLayer() {
    assertThrows(NullPointerException.class, () -> canvas.addLayer(null));

    canvas.addLayer(defaultLayer);
    assertDoesNotThrow(() -> canvas.addLayer(defaultLayer));
    assertEquals(1, canvas.getAmountOfLayers());

    IReadOnlyLayer obtainedLayer = canvas.getLayers().iterator().next();
    assertEquals(defaultLayer, obtainedLayer);

    assertDoesNotThrow(() -> canvas.addLayer(defaultLayer));
  }

  @Test
  void removeLayer() {
    assertDoesNotThrow(() -> canvas.removeLayer(-1));

    canvas.addLayer(defaultLayer); // 0
    canvas.addLayer(createLayer(4, 18, 82, 14)); // 1

    ILayer lastLayer = createLayer(7, -12, 33, 41);
    canvas.addLayer(lastLayer); // 2

    assertEquals(lastLayer, canvas.getActiveLayer());
    canvas.removeLayer(0);

    assertNotSame(defaultLayer, canvas.getActiveLayer());

    for (IReadOnlyLayer layer : canvas.getLayers()) {
      assertNotEquals(layer, defaultLayer);
    }

    assertDoesNotThrow(() -> canvas.removeLayer(canvas.getAmountOfLayers()));
    assertDoesNotThrow(() -> canvas.removeLayer(canvas.getAmountOfLayers() + 100));
  }

  @Test
  void addCanvasUpdateListener() {
    assertThrows(NullPointerException.class, () -> canvas.addCanvasUpdateListener(null));
  }

  @Test
  void addLayerUpdateListener() {
    assertThrows(NullPointerException.class, () -> canvas.addLayerUpdateListener(null));
  }

  @Test
  void getAmountOfLayers() {
    assertEquals(0, canvas.getAmountOfLayers());

    canvas.addLayer(defaultLayer);
    assertEquals(1, canvas.getAmountOfLayers());

    canvas.removeLayer(0);
    assertEquals(0, canvas.getAmountOfLayers());
  }

  @Test
  void getLayers() {
    assertNotNull(canvas.getLayers()); // should be empty, not null

    assertFalse(canvas.getLayers().iterator().hasNext());

    // Ensures the correct amount of layers in the iterable:
    final int count = 5;
    for (int i = 0; i < count; i++) {
      canvas.addLayer(createLayer(i * 2, i + 5, 12, 4 + i));
    }

    int actualCount = 0;
    for (IReadOnlyLayer ignored : canvas.getLayers()) {
      ++actualCount;
    }

    assertEquals(count, actualCount);
  }

  @Test
  void moveActiveLayer() {
    assertDoesNotThrow(() -> canvas.moveActiveLayer(0, 0));
  }

  @Test
  void setLayerVisibility() {
    assertDoesNotThrow(() -> canvas.setLayerVisibility(0, true));

    canvas.addLayer(defaultLayer);

    boolean isVisible = new Random().nextBoolean();
    canvas.setLayerVisibility(defaultLayer.getDepthIndex(), isVisible);

    assertEquals(isVisible, defaultLayer.isVisible());
  }

  @Test
  void setLayerName() {
    assertDoesNotThrow(() -> canvas.setLayerName(0, ""));

    canvas.addLayer(defaultLayer);

    String name = "foo";
    canvas.setLayerName(defaultLayer.getDepthIndex(), name);

    assertEquals(name, defaultLayer.getName());
  }

  @Test
  void setActiveLayerX() {
    assertDoesNotThrow(() -> canvas.setActiveLayerX(0));

    canvas.addLayer(defaultLayer);
    canvas.selectLayer(0);

    int x = 1295;

    canvas.setActiveLayerX(x);
    assertEquals(x, defaultLayer.getX());
  }

  @Test
  void setActiveLayerY() {
    assertDoesNotThrow(() -> canvas.setActiveLayerY(0));

    canvas.addLayer(defaultLayer);
    canvas.selectLayer(0);

    int y = 9124;

    canvas.setActiveLayerY(y);
    assertEquals(y, defaultLayer.getY());
  }

  @Test
  void isLayerVisible() {
    assertDoesNotThrow(() -> canvas.isLayerVisible(-1));

    canvas.addLayer(defaultLayer);
    canvas.selectLayer(0);

    defaultLayer.setVisible(new Random().nextBoolean());

    assertEquals(defaultLayer.isVisible(), canvas.isLayerVisible(defaultLayer.getDepthIndex()));
  }

  @Test
  void getActiveLayer() {
    assertNull(canvas.getActiveLayer());

    canvas.addLayer(defaultLayer);
    canvas.selectLayer(0);

    assertEquals(defaultLayer, canvas.getActiveLayer());
  }

  @Test
  void restore() {
    assertThrows(NullPointerException.class, () -> canvas.restore(null));
  }

  @Test
  void createSnapShot() {
    assertNotNull(canvas.createSnapShot());
  }

  @Test
  void testToString() {
    System.out.println(canvas.toString()); // see the test output to see if it's correct
  }

  @Test
  void cloneTest() {
    canvas.addLayer(defaultLayer);
    canvas.selectLayer(0);

    ICanvas copy = canvas.clone();

    assertEquals(canvas.getAmountOfLayers(), copy.getAmountOfLayers());
    assertEquals(canvas.getActiveLayer().getDepthIndex(), copy.getActiveLayer().getDepthIndex());
  }
}