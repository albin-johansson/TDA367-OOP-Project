package chalmers.pimp.model.canvas.layer;

import chalmers.pimp.model.canvas.LayerUpdateEvent;

/**
 * The {@code ILayerUpdateListener} interface specifies object that may listen for updates of the
 * layers.
 */
public interface ILayerUpdateListener {

  /**
   * Invoked when the layers has been updated.
   *
   * @param event the layer update event that contains the layer state.
   */
  void layersUpdated(LayerUpdateEvent event);
}
