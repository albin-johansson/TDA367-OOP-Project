package model.canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.canvas.layer.ILayerUpdateListener;
import model.canvas.layer.IReadOnlyLayer;
import model.canvas.layer.LayerUpdateEvent;
import model.canvas.layer.LayerUpdateEvent.EventType;

/**
 * The {@code LayerUpdateListenerComposite} class is a composite of instances of the {@code
 * ILayerUpdateListener} interface.
 */
final class LayerUpdateListenerComposite implements ILayerUpdateListener {

  private final List<ILayerUpdateListener> listeners;

  LayerUpdateListenerComposite() {
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
  void add(ILayerUpdateListener listener) {
    Objects.requireNonNull(listener);

    if (listeners.contains(listener)) {
      throw new IllegalArgumentException("Attempted to add same listener more than once!");
    }

    listeners.add(listener);
  }

  @Override
  public void layersUpdated(LayerUpdateEvent e) {
    for (ILayerUpdateListener listener : listeners) {
      listener.layersUpdated(e);
    }
  }
}
