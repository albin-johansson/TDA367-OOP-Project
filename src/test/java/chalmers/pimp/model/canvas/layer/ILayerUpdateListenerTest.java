package chalmers.pimp.model.canvas.layer;

class ILayerUpdateListenerTest {

//  private static ILayer layer;
//  private static ILayer addedLayer;
//  private static Canvas canvas;
////  static EventType action;
//
//  @BeforeEach
//  void init() {
//    addedLayer = new Raster(1, 1);
//    canvas = new Canvas();
//
//    canvas.addLayerUpdateListener(new MockListener());
//    canvas.addLayer(addedLayer);
//
//    layer = null;
//  }
//
////  @Test
////  void testOnCreate() {
////    ILayer layer = new Raster(1, 1);
////    canvas.addLayer(layer);
////    assertEquals(action, EventType.CREATED);
////    assertEquals(ILayerUpdateListenerTest.layer, layer);
////  }
//
//  @Test
//  void testOnRemove() {
//
//    canvas.removeLayer(addedLayer);
//    assertEquals(action, EventType.REMOVED);
//    assertEquals(layer, addedLayer);
//  }
//
//  @Test
//  void testOnRemove2() {
//
//    canvas.removeLayer(0);
//    assertEquals(action, EventType.REMOVED);
//    assertEquals(layer, addedLayer);
//  }
//
//  @Test
//  void testSelect() {
//
//    canvas.selectLayer(0);
//    assertEquals(action, EventType.SELECTED);
//    assertEquals(layer, addedLayer);
//  }
//
//  @Test
//  void testVisible() {
//
//    canvas.setLayerVisibility(0, true);
//    assertEquals(action, EventType.VISIBILITY_TOGGLED);
//    assertEquals(layer, addedLayer);
//  }
//
//  static class MockListener implements ILayerUpdateListener {
//
//    @Override
//    public void layersUpdated(LayerUpdateEvent e) {
//      action = e.getType();
//      layer = (ILayer) e.getLayer();
//    }
//  }
}