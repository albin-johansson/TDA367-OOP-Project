package chalmers.pimp.model.canvas;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.LayerFactory;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LayerUpdateEventTest {

  private LayerUpdateEvent event;
  private LayerManager layerManager;
  private Random random;

  @BeforeEach
  private void setUp() {
    random = new Random(System.currentTimeMillis());

    layerManager = new LayerManager();
    layerManager.addLayer(LayerFactory.createRectangle(0, 0, 10, 10));

    event = new LayerUpdateEvent(layerManager.getLayers(), layerManager.getAmountOfLayers());
  }

  @Test
  void setVisibilityUpdated() {
    boolean visibilityUpdated = random.nextBoolean();
    event.setVisibilityUpdated(visibilityUpdated);
    assertEquals(visibilityUpdated, event.wasVisibilityUpdated());
  }

  @Test
  void setSelectionUpdated() {
    boolean selectionUpdated = random.nextBoolean();
    event.setSelectionUpdated(selectionUpdated);
    assertEquals(selectionUpdated, event.wasSelectionUpdated());
  }

  @Test
  void setAddedLayer() {
    assertDoesNotThrow(() -> event.setAddedLayer(null));

    ILayer addedLayer = LayerFactory.createRectangle(0, 0, 20, 20);
    event.setAddedLayer(addedLayer);

    assertTrue(event.wasLayerAdded());
  }

  @Test
  void setRemovedLayer() {
    assertDoesNotThrow(() -> event.setRemovedLayer(null));

    ILayer removedLayer = LayerFactory.createRectangle(0, 0, 20, 20);
    event.setRemovedLayer(removedLayer);

    assertTrue(event.wasLayerRemoved());
  }

  @Test
  void wasVisibilityUpdated() {
    assertFalse(event.wasVisibilityUpdated());

    event.setVisibilityUpdated(true);
    assertTrue(event.wasVisibilityUpdated());
  }

  @Test
  void wasSelectionUpdated() {
    assertFalse(event.wasSelectionUpdated());

    event.setSelectionUpdated(true);
    assertTrue(event.wasSelectionUpdated());
  }

  @Test
  void wasLayerAdded() {
    assertFalse(event.wasLayerAdded());

    event.setAddedLayer(LayerFactory.createRectangle(0, 0, 2, 2));
    assertTrue(event.wasLayerAdded());

    event.setAddedLayer(null);
    assertFalse(event.wasLayerAdded());
  }

  @Test
  void wasLayerRemoved() {
    assertFalse(event.wasLayerRemoved());

    event.setRemovedLayer(LayerFactory.createRectangle(0, 0, 2, 2));
    assertTrue(event.wasLayerRemoved());

    event.setRemovedLayer(null);
    assertFalse(event.wasLayerRemoved());
  }

  @Test
  void getAmountOfLayers() {
    assertEquals(layerManager.getAmountOfLayers(), event.getAmountOfLayers());
  }

  @Test
  void getLayers() {
    assertEquals(layerManager.getLayers(), event.getLayers());
  }
}