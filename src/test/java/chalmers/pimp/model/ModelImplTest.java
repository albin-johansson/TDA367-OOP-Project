package chalmers.pimp.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chalmers.pimp.model.canvas.ICanvasUpdateListener;
import chalmers.pimp.model.canvas.ILayerUpdateListener;
import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.model.canvas.layer.LayerFactory;
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

    assertDoesNotThrow(() -> model.addLayer(layer));
  }

  @Test
  void removeLayerByReference() {
    assertThrows(NullPointerException.class, () -> model.removeLayer(null));

    ILayer nonAddedLayer = LayerFactory.createRasterLayer(10, 10);

    assertDoesNotThrow(() -> model.removeLayer(nonAddedLayer));
  }

//  @Test
//  void removeLayerByIndex() {
//    assertThrows(IllegalStateException.class, () -> model.removeLayer(-1));
//
//    ILayer layer = LayerFactory.createRasterLayer(10, 10);
//    model.addLayer(layer); // 0
//
//    model.removeLayer(0);
//
//    assertEquals(0, model.getAmountOfLayers());
//  }

  @Test
  void selectLayerByIndex() {
    assertDoesNotThrow(() -> model.selectLayer(0));

    ILayer layer = LayerFactory.createRasterLayer(10, 10);
    model.addLayer(layer);

    assertDoesNotThrow(() -> model.selectLayer(0));
  }

  @Test
  void selectLayerByReference() {
    assertDoesNotThrow(() -> model.selectLayer(0));

    ILayer layer = LayerFactory.createRasterLayer(10, 10);
    model.addLayer(layer);

    assertDoesNotThrow(() -> model.selectLayer(layer));
  }

  @Test
  void moveLayer() {

    //Creates three layers to work with
    ILayer layer1 = LayerFactory.createRasterLayer(1, 1);
    model.addLayer(layer1);
    ILayer layer2 = LayerFactory.createRasterLayer(2, 2);
    model.addLayer(layer2);
    ILayer layer3 = LayerFactory.createRasterLayer(3, 3);
    model.addLayer(layer3);

    //Start order should me 1, 2, 3
    int index = 0;
    for (IReadOnlyLayer l : model.getLayers()) {
      switch (index) {
        case 0:
          assertEquals(l, layer1);
          break;
        case 1:
          assertEquals(l, layer2);
          break;
        case 2:
          assertEquals(l, layer3);
          break;
      }
      index++;
    }

    //Try to move back layer backwards and front layer forwards, should not change anything
    model.moveLayer(layer1, -1);
    model.moveLayer(layer3, 1);
    //Move middle layer "zero" steps
    model.moveLayer(layer2, 0);

    //Assert order 1,2,3
    index = 0;
    for (IReadOnlyLayer l : model.getLayers()) {
      switch (index) {
        case 0:
          assertEquals(l, layer1);
          break;
        case 1:
          assertEquals(l, layer2);
          break;
        case 2:
          assertEquals(l, layer3);
          break;
      }
      index++;
    }

    //Move back layer forwards, then front layer backwards. Each one step.
    model.moveLayer(layer1, 1);
    model.moveLayer(layer3, -1);
    //Assert order 2,3,1
    for (IReadOnlyLayer l : model.getLayers()) {
      switch (index) {
        case 0:
          assertEquals(l, layer2);
          break;
        case 1:
          assertEquals(l, layer3);
          break;
        case 2:
          assertEquals(l, layer1);
          break;
      }
      index++;
    }

    //Supply invalid layer
    IReadOnlyLayer temp = LayerFactory.createRasterLayer(5, 5);
    assertDoesNotThrow(() -> model.moveLayer(temp, 1));
  }

  @Test
  void setPixel() {
    ILayer layer = LayerFactory.createRasterLayer(10, 10);
    model.addLayer(layer);
    model.selectLayer(0);

    assertThrows(NullPointerException.class, () -> model.setPixel(null));
  }

  @Test
  void setLayerNameByReference() {
    ILayer layer = LayerFactory.createRasterLayer(10, 10);
    model.addLayer(layer);
    String temp = layer.getName();
    String nameToBe = "name";
    assertTrue(temp != nameToBe);
    model.setLayerName(layer, nameToBe);
    assertEquals(nameToBe, layer.getName());
    final String temp2 = null;
    assertThrows(NullPointerException.class, () -> model.setLayerName(layer, temp2));
    assertThrows(NullPointerException.class, () -> model.setLayerName(null, temp));
  }

  @Test
  void setLayerNameByIndex() {
    ILayer layer = LayerFactory.createRasterLayer(10, 10);
    model.addLayer(layer);
    String temp = layer.getName();
    String nameToBe = "name";
    assertNotSame(temp, nameToBe);
    model.setLayerName(0, nameToBe);
    assertEquals(nameToBe, layer.getName());
    final String temp2 = null;
    assertThrows(NullPointerException.class, () -> model.setLayerName(0, temp2));
    assertDoesNotThrow(() -> model.setLayerName(-1, temp));
  }

  @Test
  void setLayerVisibility() {
    assertDoesNotThrow(() -> model.setLayerVisibility(0, true));
    assertThrows(NullPointerException.class, () -> model.setLayerVisibility(null, false));
    ILayer layer = LayerFactory.createRasterLayer(10, 10);
    model.addLayer(layer);

    assertDoesNotThrow(() -> model.setLayerVisibility(0, false));
    assertDoesNotThrow(() -> model.setLayerVisibility(layer, true));
  }

  @Test
  void addCanvasUpdateListener() {
    assertThrows(NullPointerException.class, () -> model.addCanvasUpdateListener(null));
    ICanvasUpdateListener listener = () -> {
    };
    model.addCanvasUpdateListener(listener);
    assertDoesNotThrow(() -> model.addCanvasUpdateListener(listener));
  }

  @Test
  void addLayerUpdateListener() {
    assertThrows(NullPointerException.class, () -> model.addLayerUpdateListener(null));
    ILayerUpdateListener listener = (e) -> {
    };
    model.addLayerUpdateListener(listener);
    assertDoesNotThrow(() -> model.addLayerUpdateListener(listener));
  }

  @Test
  void getLayers() {
    assertNotNull(model.getLayers());
  }

//  @Test
//  void getAmountOfLayers() {
//    assertEquals(0, model.getAmountOfLayers());
//
//    ILayer layer = LayerFactory.createRasterLayer(10, 10);
//    model.addLayer(layer);
//
//    assertEquals(1, model.getAmountOfLayers());
//
//    model.removeLayer(layer);
//    assertEquals(0, model.getAmountOfLayers());
//  }

  @Test
  void getActiveLayer() {
    assertEquals(null, model.getActiveLayer());
    ILayer layer = LayerFactory.createRasterLayer(10, 10);
    model.addLayer(layer);
    assertEquals(layer, model.getActiveLayer());
  }

  @Test
  void setSelectedTool() {
    assertDoesNotThrow(() -> model.setSelectedTool(null));
  }

  @Test
  void moveSelectedLayer() {
    ILayer layer = LayerFactory.createRectangle(20, 15, 500, 70);
    model.addLayer(layer);

    model.selectLayer(0);

    assertEquals(20, layer.getX());
    assertEquals(15, layer.getY());

    model.moveSelectedLayer(15, 10);

    assertEquals(35, layer.getX());
    assertEquals(25, layer.getY());
  }
}