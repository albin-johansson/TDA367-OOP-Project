package chalmers.pimp.model.canvas;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CanvasUpdateListenerCompositeTest {

  private CanvasUpdateListenerComposite composite;
  private int canvasUpdatedTestCount; // used when testing the canvasUpdated-method

  @BeforeEach
  private void setUp() {
    composite = new CanvasUpdateListenerComposite();
    canvasUpdatedTestCount = 0;
  }

  /**
   * Creates and returns a generic canvas update listener instance, to be used for testing.
   *
   * @return a generic canvas update listener instance, to be used for testing.
   */
  private ICanvasUpdateListener createListener() {
    return () -> {
      // do nothing when triggered
    };
  }

  @Test
  void add() {
    assertThrows(NullPointerException.class, () -> composite.add(null));

    // The composite should not allow adding the same listener more than once
    ICanvasUpdateListener listener = createListener();
    composite.add(listener);
    assertDoesNotThrow(() -> composite.add(listener));

    int nHits = 0;
    for (ICanvasUpdateListener child : composite) {
      if (listener == child) {
        nHits++;
      }
    }

    assertEquals(1, nHits); // The listener should've only been added once
  }

  @Test
  void canvasUpdated() {
    canvasUpdatedTestCount = 0;

    final int firstTerm = 2;
    final int secondTerm = 5;
    ICanvasUpdateListener firstListener = () -> canvasUpdatedTestCount += firstTerm;
    ICanvasUpdateListener secondListener = () -> canvasUpdatedTestCount += secondTerm;

    final int expectedSum = firstTerm + secondTerm;

    composite.add(firstListener);
    composite.add(secondListener);

    composite.canvasUpdated();

    assertEquals(expectedSum, canvasUpdatedTestCount);
  }
}