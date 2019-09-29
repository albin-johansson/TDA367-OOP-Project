package chalmers.pimp.model.canvas.layer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chalmers.pimp.model.canvas.Canvas;
import chalmers.pimp.model.canvas.layer.LayerUpdateEvent.EventType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ILayerUpdateListenerTest {

  static ILayer layer;
  static ILayer addedLayer;
  static Canvas canvas;
  static EventType action;

  @BeforeEach
  void init() {
    addedLayer = new Raster(1, 1);
    canvas = new Canvas();

    canvas.addLayerUpdateListener(new MockListener());
    canvas.addLayer(addedLayer);

    action = null;
    layer = null;
  }

  @Test
  void testOnCreate() {
    ILayer layer = new Raster(1, 1);
    canvas.addLayer(layer);
    assertEquals(action, EventType.CREATED);
    assertEquals(ILayerUpdateListenerTest.layer, layer);
  }

  @Test
  void testOnRemove() {

    canvas.removeLayer(addedLayer);
    assertEquals(action, EventType.REMOVED);
    assertEquals(layer, addedLayer);
  }

  @Test
  void testOnRemove2() {

    canvas.removeLayer(0);
    assertEquals(action, EventType.REMOVED);
    assertEquals(layer, addedLayer);
  }

  @Test
  void testSelect() {

    canvas.selectLayer(0);
    assertEquals(action, EventType.SELECTED);
    assertEquals(layer, addedLayer);
  }

  @Test
  void testVisible() {

    canvas.setLayerVisible(0, true);
    assertEquals(action, EventType.VISIBILITY_TOGGLED);
    assertEquals(layer, addedLayer);
  }

  static class MockListener implements ILayerUpdateListener {

    @Override
    public void layersUpdated(LayerUpdateEvent e) {
      action = e.getType();
      layer = (ILayer) e.getLayer();
    }
  }
}