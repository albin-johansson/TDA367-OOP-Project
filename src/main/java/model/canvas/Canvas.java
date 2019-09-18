package model.canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.canvas.layer.ILayer;

/**
 * The {@code Canvas} class is responsible for handling layers.
 */
public final class Canvas {

  private final CanvasUpdateListenerComposite canvasUpdateListeners;
  private final List<ILayer> layers;
  private ILayer activeLayer; // TODO use when the ILayer interface has been further specified

  public Canvas() {
    canvasUpdateListeners = new CanvasUpdateListenerComposite();
    layers = new ArrayList<>(20);
    activeLayer = null;
  }

  /**
   * Adds a layer to the canvas. Duplicates are not allowed.
   *
   * @param layer the layer that will be added, may not be {@code null}.
   * @throws NullPointerException if any arguments are {@code null}.
   * @throws IllegalArgumentException if the supplied layer is already present in the canvas.
   */
  public void addLayer(ILayer layer) {
    Objects.requireNonNull(layer);

    if (layers.contains(layer)) {
      throw new IllegalArgumentException("Attempted to add the same layer multiple times!");
    }

    layers.add(layer);
  }

  /**
   * Removes the supplied layer from the canvas. This method has no effect if the specified layer
   * isn't contained in the canvas.
   *
   * @param layer the layer that will be removed, may not be {@code null}.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  public void removeLayer(ILayer layer) {
    Objects.requireNonNull(layer);
    layers.remove(layer);
  }

  /**
   * Adds a canvas update listener to the canvas.
   *
   * @param listener the listener that will be added, may not be {@code null}.
   * @throws NullPointerException if any arguments are {@code null}.
   * @throws IllegalArgumentException if the supplied listener has been added previously.
   */
  public void addCanvasUpdateListener(ICanvasUpdateListener listener) {
    canvasUpdateListeners.add(listener);
  }

  // TODO create methods that delegate to the active layer, if no layer is active: throw exceptions.

  /**
   * Returns all of the layers present in the canvas.
   *
   * @return all of the layers present in the canvas.
   */
  public Iterable<ILayer> getLayers() {
    return layers;
  }
}