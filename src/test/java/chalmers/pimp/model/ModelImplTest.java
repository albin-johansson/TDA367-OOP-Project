package chalmers.pimp.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chalmers.pimp.model.canvas.ICanvasUpdateListener;
import chalmers.pimp.model.canvas.layer.ILayerUpdateListener;
import chalmers.pimp.model.canvas.layer.ILayer;
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
  void selectLayerByIndex() {
    assertDoesNotThrow(() -> model.selectLayer(0));

    ILayer layer = LayerFactory.createRasterLayer(10, 10);
    model.addLayer(layer);

    assertDoesNotThrow(() -> model.selectLayer(0));
  }

  @Test
  void moveLayer() {
    model.addLayer(LayerFactory.createRasterLayer(1, 1));
    model.addLayer(LayerFactory.createRasterLayer(2, 2));
    model.addLayer(LayerFactory.createRasterLayer(3, 3));

    // TODO rewrite
//    int index = 0;
//    for (IReadOnlyLayer layer : model.getLayers()) {
//      switch (index) {
//        case 0:
//          assertEquals(layer, layer0);
//          break;
//        case 1:
//          assertEquals(layer, layer1);
//          break;
//        case 2:
//          assertEquals(layer, layer2);
//          break;
//      }
//      index++;
//    }
//
//    //Try to move back layer backwards and front layer forwards, should not change anything
//    model.changeLayerDepthIndex(layer0, -1);
//    model.changeLayerDepthIndex(layer2, 1);
//
//    //Move middle layer "zero" steps
//    model.changeLayerDepthIndex(layer1, 0);
//
//    //Assert order 1,2,3
//    index = 0;
//    for (IReadOnlyLayer layer : model.getLayers()) {
//      switch (index) {
//        case 0:
//          assertEquals(layer, layer0);
//          break;
//        case 1:
//          assertEquals(layer, layer1);
//          break;
//        case 2:
//          assertEquals(layer, layer2);
//          break;
//      }
//      index++;
//    }
//
//    //Move back layer forwards, then front layer backwards. Each one step.
//    model.changeLayerDepthIndex(layer0, 1);
//    model.changeLayerDepthIndex(layer2, -1);
//
//    //Assert order 2,3,1
//    for (IReadOnlyLayer l : model.getLayers()) {
//      switch (index) {
//        case 0:
//          assertEquals(l, layer1);
//          break;
//        case 1:
//          assertEquals(l, layer2);
//          break;
//        case 2:
//          assertEquals(l, layer0);
//          break;
//      }
//      index++;
//    }
//
//    //Supply invalid layer
//    IReadOnlyLayer temp = LayerFactory.createRasterLayer(5, 5);
//    assertDoesNotThrow(() -> model.changeLayerDepthIndex(temp, 1));
  }

  @Test
  void setPixel() {
    ILayer layer = LayerFactory.createRasterLayer(10, 10);
    model.addLayer(layer);
    model.selectLayer(0);

    assertThrows(NullPointerException.class, () -> model.setActiveLayerPixel(null));
  }

//  @Test
//  void setLayerNameByReference() {
//    ILayer layer = LayerFactory.createRasterLayer(10, 10);
//    model.addLayer(layer);
//    model.selectLayer(0);
//
//    String temp = layer.getName();
//    String fooName = "foo";
//    assertNotEquals(temp, fooName);
//
//    model.setLayerName(layer, fooName);
//
//    assertEquals(fooName, model.getActiveLayer().getName());
//
//    final String temp2 = null;
//    assertThrows(NullPointerException.class, () -> model.setLayerName(layer, temp2));
//    assertThrows(NullPointerException.class, () -> model.setLayerName(null, temp));
//  }

  @Test
  void setLayerNameByIndex() {
    ILayer layer = LayerFactory.createRasterLayer(10, 10);
    model.addLayer(layer);

    String temp = layer.getName();
    String nameToBe = "name";
    assertNotSame(temp, nameToBe);

    model.setLayerName(0, nameToBe);
    model.selectLayer(0);

    assertEquals(nameToBe, model.getActiveLayer().getName());

    final String temp2 = null;
    assertThrows(NullPointerException.class, () -> model.setLayerName(0, temp2));
    assertDoesNotThrow(() -> model.setLayerName(-1, temp));
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

  @Test
  void getActiveLayer() {
    assertNull(model.getActiveLayer());
    ILayer layer = LayerFactory.createRasterLayer(10, 10);
    model.addLayer(layer);
    model.selectLayer(0);

    assertEquals(layer.getLayerType(), model.getActiveLayer().getLayerType());
    assertEquals(layer.getName(), model.getActiveLayer().getName());
    assertEquals(layer.getX(), model.getActiveLayer().getX());
    assertEquals(layer.getY(), model.getActiveLayer().getY());
    assertEquals(layer.getDepthIndex(), model.getActiveLayer().getDepthIndex());
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

    assertEquals(20, model.getActiveLayer().getX());
    assertEquals(15, model.getActiveLayer().getY());

    model.moveActiveLayer(15, 10);

    assertEquals(35, model.getActiveLayer().getX());
    assertEquals(25, model.getActiveLayer().getY());
  }
}