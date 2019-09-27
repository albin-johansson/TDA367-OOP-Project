package model.canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The {@code CanvasUpdateListenerComposite} class is a composite of instances of the {@code
 * ICanvasUpdateListener} interface.
 */
final class CanvasUpdateListenerComposite implements ICanvasUpdateListener {

  private final List<ICanvasUpdateListener> listeners;

  CanvasUpdateListenerComposite() {
    listeners = new ArrayList<>(4);
  }

  /**
   * Adds a canvas update listener to the composite.
   *
   * @param listener the listener that will be added, may not be {@code null}.
   * @throws NullPointerException if any arguments are {@code null}.
   * @throws IllegalArgumentException if the supplied listener has been added to the composite
   * previously.
   */
  void add(ICanvasUpdateListener listener) {
    Objects.requireNonNull(listener);

    if (listeners.contains(listener)) {
      throw new IllegalArgumentException("Attempted to add same listener more than once!");
    }

    listeners.add(listener);
  }

  @Override
  public void canvasUpdated() {
    for (ICanvasUpdateListener listener : listeners) {
      listener.canvasUpdated();
    }
  }
}