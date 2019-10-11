package chalmers.pimp.model.canvas;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chalmers.pimp.model.canvas.layer.LayerFactory;
import chalmers.pimp.model.canvas.layer.LayerType;
import com.sun.javafx.scene.control.CustomColorDialog;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LayerManagerTest {

  private LayerManager manager;

  @BeforeEach
  void setUp() {
    manager = new LayerManager();
  }

  @Test
  void notifyListeners() {

  }

  @Test
  void addLayerUpdateListener() {
    assertThrows(NullPointerException.class, () -> manager.addLayerUpdateListener(null));

    ILayerUpdateListener listener = event -> {
    };

    manager.addLayerUpdateListener(listener);
    assertDoesNotThrow(() -> manager.addLayerUpdateListener(listener));
  }

  @Test
  void changeDepthIndex() {
    manager.addLayer(LayerFactory.createRectangle(0, 0, 10, 10)); // SHAPE
    manager.addLayer(LayerFactory.createRasterLayer(5, 5)); // RASTER

    manager.changeDepthIndex(0, -4); // should have no effect

    manager.selectLayer(0);
    assertEquals(LayerType.SHAPE, manager.getActiveLayer().getLayerType());
    manager.selectLayer(1);
    assertEquals(LayerType.RASTER, manager.getActiveLayer().getLayerType());

    manager.changeDepthIndex(1, 5); // should have no effect

    manager.selectLayer(0);
    assertEquals(LayerType.SHAPE, manager.getActiveLayer().getLayerType());
    manager.selectLayer(1);
    assertEquals(LayerType.RASTER, manager.getActiveLayer().getLayerType());

    manager.changeDepthIndex(0, 1); // moves first layer forwards (to higher z-value)

    manager.selectLayer(0);
    assertEquals(LayerType.RASTER, manager.getActiveLayer().getLayerType());
    manager.selectLayer(1);
    assertEquals(LayerType.SHAPE, manager.getActiveLayer().getLayerType());

    manager.changeDepthIndex(1, -1); // moves last layer backwards (to lower z-value)

    manager.selectLayer(0);
    assertEquals(LayerType.SHAPE, manager.getActiveLayer().getLayerType());
    manager.selectLayer(1);
    assertEquals(LayerType.RASTER, manager.getActiveLayer().getLayerType());
  }

  @Test
  void selectLayer() {
    manager.addLayer(LayerFactory.createRectangle(0, 0, 10, 10)); // SHAPE
    manager.addLayer(LayerFactory.createRasterLayer(5, 5)); // RASTER

    assertEquals(LayerType.RASTER, manager.getActiveLayer().getLayerType());

    manager.selectLayer(-1); // should have no effect
    assertEquals(LayerType.RASTER, manager.getActiveLayer().getLayerType());

    manager.selectLayer(2); // should have no effect
    assertEquals(LayerType.RASTER, manager.getActiveLayer().getLayerType());

    manager.selectLayer(0);
    assertEquals(LayerType.SHAPE, manager.getActiveLayer().getLayerType());

    manager.selectLayer(1);
    assertEquals(LayerType.RASTER, manager.getActiveLayer().getLayerType());
  }

  @Test
  void addLayer() {
    assertThrows(NullPointerException.class, () -> manager.addLayer(null));

    manager.addLayer(LayerFactory.createRectangle(0, 0, 5, 5));

    assertNotNull(manager.getActiveLayer());
    assertEquals(LayerType.SHAPE, manager.getActiveLayer().getLayerType());

    assertEquals(1, manager.getAmountOfLayers());
  }

  @Test
  void removeLayer() {
    assertDoesNotThrow(() -> manager.removeLayer(0));

    manager.addLayer(LayerFactory.createRectangle(0, 0, 5, 5));
    manager.removeLayer(0);

    assertEquals(0, manager.getAmountOfLayers());
  }

  @Test
  void setActiveLayerPixel() {
  }

  @Test
  void setActiveLayerPixels() {
  }

  @Test
  void moveActiveLayer() {
  }

  @Test
  void setActiveLayerX() {
  }

  @Test
  void setActiveLayerY() {
  }

  @Test
  void setLayerVisibility() {
    assertDoesNotThrow(() -> manager.setLayerVisibility(0, true));

    manager.addLayer(LayerFactory.createRectangle(0, 0, 5, 5));
    manager.selectLayer(0);

    boolean isVisible = new Random().nextBoolean();
    manager.setLayerVisibility(0, isVisible);

    assertEquals(isVisible, manager.getActiveLayer().isVisible());
  }

  @Test
  void setLayerName() {
    assertThrows(NullPointerException.class, () -> manager.setLayerName(0, null));
    assertDoesNotThrow(() -> manager.setLayerName(0, ""));

    String name = "foo";

    manager.addLayer(LayerFactory.createRectangle(0, 0, 5, 5));
    manager.setLayerName(0, name);

    assertEquals(name, manager.getActiveLayer().getName());
  }

  @Test
  void getLayerName() {
    assertNotNull(manager.getLayerName(0));
    assertEquals("", manager.getLayerName(0));

    String name = "abc";

    manager.addLayer(LayerFactory.createRectangle(0, 0, 5, 5));
    manager.setLayerName(0, name);

    assertEquals(name, manager.getLayerName(0));
  }

  @Test
  void getAmountOfLayers() {
    assertEquals(0, manager.getAmountOfLayers());

    int nLayers = 10;
    for (int i = 0; i < nLayers; i++) {
      manager.addLayer(LayerFactory.createRectangle(0, 0, 5, 5));
    }

    assertEquals(nLayers, manager.getAmountOfLayers());
  }

  @Test
  void isLayerVisible() {
  }

  @Test
  void getActiveLayer() {
  }

  @Test
  void getLayers() {
  }

  @Test
  void testToString() {
  }
}