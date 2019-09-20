package model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import model.canvas.ICanvasUpdateListener;
import model.canvas.layer.ILayer;
import model.canvas.layer.LayerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModelImplTest {

  private IModel model;

  @BeforeEach
  private void setUp() {
    model = ModelFactory.createModel();
  }

  @Test
  void addLayer() {
    assertThrows(NullPointerException.class, () -> model.addLayer(null));

    ILayer layer = LayerFactory.createRasterLayer(10, 10);
    model.addLayer(layer);

    assertThrows(IllegalArgumentException.class, () -> model.addLayer(layer));
  }

  @Test
  void removeLayerByReference() {
    assertThrows(NullPointerException.class, () -> model.removeLayer(null));

    ILayer nonAddedLayer = LayerFactory.createRasterLayer(10, 10);

    assertDoesNotThrow(() -> model.removeLayer(nonAddedLayer));
  }

  @Test
  void removeLayerByIndex() {
    assertThrows(IllegalArgumentException.class, () -> model.removeLayer(-1));

    ILayer layer = LayerFactory.createRasterLayer(10, 10);
    model.addLayer(layer); // 0

    model.removeLayer(0);

    assertEquals(0, model.getAmountOfLayers());
  }

  @Test
  void selectLayer() {
    assertThrows(IllegalArgumentException.class, () -> model.selectLayer(0));

    ILayer layer = LayerFactory.createRasterLayer(10, 10);
    model.addLayer(layer);

    assertDoesNotThrow(() -> model.selectLayer(0));
  }

  @Test
  void setPixel() {
    // TODO test when PixelData has been improved
  }

  @Test
  void setLayerVisibility() {
    assertThrows(IllegalStateException.class, () -> model.setLayerVisibility(true));

    ILayer layer = LayerFactory.createRasterLayer(10, 10);
    model.addLayer(layer);
    model.selectLayer(0);

    assertDoesNotThrow(() -> model.setLayerVisibility(false));
  }

  @Test
  void addCanvasUpdateListener() {
    assertThrows(NullPointerException.class, () -> model.addCanvasUpdateListener(null));

    ICanvasUpdateListener listener = () -> {
    };

    model.addCanvasUpdateListener(listener);

    assertThrows(IllegalArgumentException.class, () -> model.addCanvasUpdateListener(listener));
  }

  @Test
  void getLayers() {
    assertNotNull(model.getLayers());
  }

  @Test
  void getAmountOfLayers() {
    assertEquals(0, model.getAmountOfLayers());

    ILayer layer = LayerFactory.createRasterLayer(10, 10);
    model.addLayer(layer);

    assertEquals(1, model.getAmountOfLayers());

    model.removeLayer(layer);
    assertEquals(0, model.getAmountOfLayers());
  }
}