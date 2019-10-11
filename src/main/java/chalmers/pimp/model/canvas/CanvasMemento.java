package chalmers.pimp.model.canvas;

import java.util.Objects;

/**
 * The {@code CanvasMemento} class represents the state of a {@link ICanvas} instance at some point
 * in time.
 *
 * @see ICanvas
 */
public final class CanvasMemento {

  private final LayerManager layerManager;

  /**
   * @param layerManager the layer manager that will be saved, should be a copy of the actual layer
   *                     manager used by the canvas.
   * @throws NullPointerException if any references are {@code null}.
   */
  CanvasMemento(LayerManager layerManager) {
    this.layerManager = Objects.requireNonNull(layerManager);
  }

  /**
   * Returns the layer manager instance associated with the canvas memento.
   *
   * @return the layer manager instance associated with the canvas memento.
   */
  LayerManager getLayerManager() {
    return layerManager;
  }
}