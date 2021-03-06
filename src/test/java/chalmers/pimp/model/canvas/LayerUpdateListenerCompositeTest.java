package chalmers.pimp.model.canvas;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chalmers.pimp.model.canvas.layer.ILayerUpdateListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LayerUpdateListenerCompositeTest {

  private LayerUpdateListenerComposite composite;
  private int LayerUpdatedTestCount; // used when testing the canvasUpdated-method

  @BeforeEach
  private void setUp() {
    composite = new LayerUpdateListenerComposite();
    LayerUpdatedTestCount = 0;
  }

  /**
   * Creates and returns a generic canvas update listener instance, to be used for testing.
   *
   * @return a generic canvas update listener instance, to be used for testing.
   */
  private ILayerUpdateListener createListener() {
    return (e) -> {
      // do nothing when triggered
    };
  }

  @Test
  void add() {
    assertThrows(NullPointerException.class, () -> composite.add(null));

    // The composite should not allow adding the same listener more than once
    ILayerUpdateListener listener = createListener();
    composite.add(listener);

    assertDoesNotThrow(() -> composite.add(listener));

    int nHits = 0;
    for (ILayerUpdateListener child : composite) {
      if (listener == child) {
        nHits++;
      }
    }

    assertEquals(1, nHits); // The listener should've only been added once
  }

  @Test
  void canvasUpdated() {
    LayerUpdatedTestCount = 0;

    final int firstTerm = 2;
    final int secondTerm = 5;
    ILayerUpdateListener firstListener = (e) -> LayerUpdatedTestCount += firstTerm;
    ILayerUpdateListener secondListener = (e) -> LayerUpdatedTestCount += secondTerm;

    final int expectedSum = firstTerm + secondTerm;

    composite.add(firstListener);
    composite.add(secondListener);

    composite.layersUpdated(null);

    assertEquals(expectedSum, LayerUpdatedTestCount);
  }
}