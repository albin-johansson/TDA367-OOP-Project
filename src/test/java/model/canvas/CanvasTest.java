package model.canvas;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import model.canvas.layer.ILayer;
import model.canvas.layer.LayerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CanvasTest {

  private Canvas canvas;
  private ILayer defaultLayer;

  @BeforeEach
  private void setUp() {
    canvas = new Canvas();
    defaultLayer = createLayer();
  }

  /**
   * Creates and returns a new layer instance, to be used when testing.
   *
   * @return a new layer instance, to be used when testing.
   */
  private static ILayer createLayer() {
    return LayerFactory.createRasterLayer(10, 10);
  }

  @Test
  void setPixel() {
    canvas.addLayer(defaultLayer);
    canvas.selectLayer(0);
    assertThrows(NullPointerException.class, () -> canvas.setPixel(0, 0, null));
  }

  @Test
  void setLayerVisible() {
    assertThrows(IllegalStateException.class, () -> canvas.setLayerVisible(0, true));

    canvas.addLayer(defaultLayer);
    canvas.selectLayer(0);

    assertDoesNotThrow(() -> canvas.setLayerVisible(0, true));
  }

  @Test
  void selectLayer() {
    assertThrows(IllegalArgumentException.class, () -> canvas.selectLayer(-1));

    canvas.addLayer(defaultLayer);
    assertDoesNotThrow(() -> canvas.selectLayer(0));
  }

  @Test
  void addLayer() {
    assertThrows(NullPointerException.class, () -> canvas.addLayer(null));

    canvas.addLayer(defaultLayer);

    ILayer obtainedLayer = canvas.getLayers().iterator().next();
    assertEquals(defaultLayer, obtainedLayer);

    // We cannot add the same layer more than once
    assertThrows(IllegalArgumentException.class, () -> canvas.addLayer(defaultLayer));
  }

  @Test
  void removeLayerByReference() {
    assertThrows(NullPointerException.class, () -> canvas.removeLayer(null));

    canvas.addLayer(createLayer());
    canvas.addLayer(createLayer());
    canvas.addLayer(defaultLayer);

    canvas.removeLayer(defaultLayer);

    // Should allow removing non-existent layers
    assertDoesNotThrow(() -> canvas.removeLayer(defaultLayer));

    for (ILayer layer : canvas.getLayers()) {
      assertNotEquals(layer, defaultLayer);
    }
  }

  @Test
  void removeLayerByIndex() {
    assertThrows(IllegalArgumentException.class, () -> canvas.removeLayer(-1));

    canvas.addLayer(createLayer()); // 0
    canvas.addLayer(createLayer()); // 1
    canvas.addLayer(defaultLayer); // 2

    canvas.removeLayer(2); // removes defaultLayer
    for (ILayer layer : canvas.getLayers()) {
      assertNotEquals(layer, defaultLayer);
    }

    // Since the layers are zero-indexed, the maximum index is n-1 where n is the number of layers.
    final int nLayers = canvas.getAmountOfLayers();
    assertThrows(IllegalArgumentException.class, () -> canvas.removeLayer(nLayers));
    assertDoesNotThrow(() -> canvas.removeLayer(nLayers - 1));
  }

  @Test
  void addCanvasUpdateListener() {
    assertThrows(NullPointerException.class, () -> canvas.addCanvasUpdateListener(null));
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
    for (ILayer ignored : canvas.getLayers()) {
      ++actualCount;
    }

    assertEquals(count, actualCount);
  }

}