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
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.PixelData;
import chalmers.pimp.model.pixeldata.PixelFactory;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CanvasImplTest {

  private ICanvas canvas;
  private ILayer defaultLayer;

  /**
   * Creates and returns a new layer instance, to be used when testing.
   *
   * @return a new layer instance, to be used when testing.
   */
  private static ILayer createLayer() {
    return LayerFactory.createRasterLayer(10, 10);
  }

  @BeforeEach
  private void setUp() {
    canvas = new CanvasImpl();
    defaultLayer = createLayer();
  }

  @Test
  void setActiveLayerPixel() {
    assertDoesNotThrow(() -> canvas.setActiveLayerPixel(PixelFactory.createPixel(0, 0)));

    canvas.addLayer(defaultLayer);
    canvas.selectLayer(0);
    assertThrows(NullPointerException.class, () -> canvas.setActiveLayerPixel(null));
  }

  @Test
  void setLayerVisible() {
    assertDoesNotThrow(() -> canvas.setLayerVisibility(0, true));
    assertThrows(NullPointerException.class, () -> canvas.setLayerVisibility(null, true));

    canvas.addLayer(defaultLayer);
    canvas.addLayer(LayerFactory.createRasterLayer(10, 10));

    assertDoesNotThrow(() -> canvas.setLayerVisibility(0, true));
    assertDoesNotThrow(() -> canvas.setLayerVisibility(defaultLayer, false));
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
  void removeLayerByReference() {
    assertDoesNotThrow(() -> canvas.removeLayer(defaultLayer));
    assertThrows(NullPointerException.class, () -> canvas.removeLayer(null));

    canvas.addLayer(defaultLayer);
    canvas.addLayer(createLayer());
    canvas.addLayer(createLayer());

    //Since default layer is the first layer created, will become activeLayer
    assertEquals(defaultLayer, canvas.getActiveLayer());
    canvas.removeLayer(defaultLayer);

    //The active layer should change to no longer point to removed Layer
    assertNotSame(defaultLayer, canvas.getActiveLayer());

    assertDoesNotThrow(() -> canvas.removeLayer(defaultLayer));

    for (IReadOnlyLayer layer : canvas.getLayers()) {
      assertNotEquals(layer, defaultLayer);
    }
  }

  @Test
  void removeLayerByIndex() {
    assertDoesNotThrow(() -> canvas.removeLayer(-1));

    canvas.addLayer(defaultLayer); // 0
    canvas.addLayer(createLayer()); // 1
    canvas.addLayer(createLayer()); // 2

    //Since default layer is the first layer created, will become activeLayer
    assertEquals(defaultLayer, canvas.getActiveLayer());
    canvas.removeLayer(0);

    //The active layer should change to no longer point to removed Layer
    assertNotSame(defaultLayer, canvas.getActiveLayer());
    canvas.removeLayer(0); // removes defaultLayer
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

    canvas.removeLayer(defaultLayer);
    assertEquals(0, canvas.getAmountOfLayers());
  }

  @Test
  void getLayers() {
    assertNotNull(canvas.getLayers()); // should be empty, not null

    assertFalse(canvas.getLayers().iterator().hasNext());

    // Ensures the correct amount of layers in the iterable:
    final int count = 5;
    for (int i = 0; i < count; i++) {
      canvas.addLayer(createLayer());
    }

    int actualCount = 0;
    for (IReadOnlyLayer ignored : canvas.getLayers()) {
      ++actualCount;
    }

    assertEquals(count, actualCount);
  }

  @Test
  void setActiveLayerPixels() {
    assertDoesNotThrow(() -> canvas.setActiveLayerPixels(0, 0, new PixelData(1, 1)));
    assertThrows(NullPointerException.class, () -> canvas.setActiveLayerPixels(0, 0, null));

    canvas.addLayer(defaultLayer);
    canvas.selectLayer(0);
    PixelData pixelData = new PixelData(5, 5);
    IPixel pixel = PixelFactory.createPixel(2, 3, 0, 1, 0, 1);

    pixelData.setPixel(pixel);
    //pixelData shifted to (2,2)
    canvas.setActiveLayerPixels(2, 2, pixelData);

    assertEquals(1, defaultLayer.getPixelData().getPixel(4, 5).getColor().getGreenPercentage());
  }

  @Test
  void removeLayer() {
    assertDoesNotThrow(() -> canvas.removeLayer(0));
  }

  @Test
  void removeLayer1() {
    assertDoesNotThrow(() -> canvas.removeLayer(defaultLayer));
  }

  @Test
  void changeDepthIndex() {
    assertDoesNotThrow(() -> canvas.changeDepthIndex(defaultLayer, 0));
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
  void setLayerVisibility1() {
    assertDoesNotThrow(() -> canvas.setLayerVisibility(defaultLayer, true));
    assertThrows(NullPointerException.class, () -> canvas.setLayerVisibility(null, false));

    canvas.addLayer(defaultLayer);

    boolean isVisible = new Random().nextBoolean();
    canvas.setLayerVisibility(defaultLayer, isVisible);

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
  void setLayerName1() {
    assertDoesNotThrow(() -> canvas.setLayerName(defaultLayer, ""));

    canvas.addLayer(defaultLayer);

    String name = "foo";
    canvas.setLayerName(defaultLayer, name);

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
  void copy() {
    canvas.addLayer(defaultLayer);
    canvas.selectLayer(0);

    ICanvas copy = canvas.copy();

    assertEquals(canvas.getAmountOfLayers(), copy.getAmountOfLayers());
    assertEquals(canvas.getActiveLayer().getDepthIndex(), copy.getActiveLayer().getDepthIndex());
  }
}