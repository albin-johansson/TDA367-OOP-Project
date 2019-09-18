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
    return LayerFactory.createRasterLayer();
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
  void removeLayer() {
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
  void addCanvasUpdateListener() {
    assertThrows(NullPointerException.class, () -> canvas.addCanvasUpdateListener(null));
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