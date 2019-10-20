package chalmers.pimp.model.canvas;

import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import java.util.Objects;

/**
 * The {@code LayerUpdateEvent} class represents the state of a layer update event.
 */
public final class LayerUpdateEvent {

  private final Iterable<? extends IReadOnlyLayer> layers;
  private final int nLayers;
  private IReadOnlyLayer selectedLayer;
  private IReadOnlyLayer removedLayer;
  private IReadOnlyLayer addedLayer;
  private boolean visibilityUpdated;
  private boolean selectionUpdated;

  /**
   * @param layers  all of the layers contained in the model.
   * @param nLayers the number of supplied layers.
   * @throws NullPointerException if any references are {@code null}.
   */
  LayerUpdateEvent(Iterable<? extends IReadOnlyLayer> layers, int nLayers) {
    this.layers = Objects.requireNonNull(layers);
    this.nLayers = nLayers;

    selectedLayer = null;
    removedLayer = null;
    addedLayer = null;
    visibilityUpdated = false;
    selectionUpdated = false;
  }

  /**
   * Sets the value for the property that indicates whether or not the visibility of a layer has
   * changed. By default, this property is set to {@code false}.
   *
   * @param visibilityUpdated {@code true} if the visibility of a layer has changed; {@code false}
   *                          otherwise.
   */
  void setVisibilityUpdated(boolean visibilityUpdated) {
    this.visibilityUpdated = visibilityUpdated;
  }

  /**
   * Sets the value for the property that indicates whether or not the layer selection has changed.
   * By default, this property is set to {@code false}.
   *
   * @param selectionUpdated {@code true} if the layer selection has changed; {@code false}
   *                         otherwise.
   */
  void setSelectionUpdated(boolean selectionUpdated) {
    this.selectionUpdated = selectionUpdated;
  }

  /**
   * Sets the value of the property that holds a reference to a layer that was added. By default,
   * this property is set to {@code null}.
   *
   * @param addedLayer the layer that was recently added, may be {@code null}.
   */
  void setAddedLayer(IReadOnlyLayer addedLayer) {
    this.addedLayer = addedLayer;
  }

  /**
   * Sets value of the property that holds a reference to a layer that was removed. By default, this
   * property is set to {@code null}.
   *
   * @param removedLayer the layer that was recently removed, may be {@code null}.
   */
  void setRemovedLayer(IReadOnlyLayer removedLayer) {
    this.removedLayer = removedLayer;
  }

  /**
   * Indicates whether or not the visibility of a layer was changed.
   *
   * @return {@code true} if the visibility of a layer was changed; {@code false} otherwise.
   */
  public boolean wasVisibilityUpdated() {
    return visibilityUpdated;
  }

  /**
   * Indicates whether or not the selected layer was changed.
   *
   * @return {@code true} if the selected layer was changed; {@code false} otherwise.
   */
  public boolean wasSelectionUpdated() {
    return selectionUpdated;
  }

  /**
   * Indicates whether or not a layer was added.
   *
   * @return {@code true} if a layer was added; {@code false} otherwise.
   */
  public boolean wasLayerAdded() {
    return addedLayer != null;
  }

  /**
   * Indicates whether or not a layer was removed.
   *
   * @return {@code true} if a layer was removed; {@code false} otherwise.
   */
  public boolean wasLayerRemoved() {
    return removedLayer != null;
  }

  /**
   * Returns the amount of layers associated with this layer update event. This corresponds to the
   * amount of layers in the model.
   *
   * @return the amount of layers associated with this layer update event.
   */
  public int getAmountOfLayers() {
    return nLayers;
  }

  /**
   * Returns the selected layer. Returns {@code null} if there is no selected layer.
   *
   * @return the selected layer.
   */
  public IReadOnlyLayer getSelectedLayer() {
    return selectedLayer;
  }

  /**
   * Sets the the selected layer by its index. Does nothing if the index is outside the range: [0,
   * amount of layers).
   *
   * @param index the index of the selected layer.
   */
  void setSelectedLayer(int index) {
    int i = 0;
    for (IReadOnlyLayer layer : layers) {
      if (i++ == index) {
        selectedLayer = layer;
      }
    }
  }

  /**
   * Returns all of the layers associated with this layer update event. The layers correspond to the
   * layers contained in the model.
   *
   * @return all of the layers associated with this layer update event.
   */
  public Iterable<? extends IReadOnlyLayer> getLayers() {
    return layers;
  }
}
