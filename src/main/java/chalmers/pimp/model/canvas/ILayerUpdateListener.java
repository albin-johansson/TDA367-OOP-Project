package chalmers.pimp.model.canvas;

import chalmers.pimp.model.canvas.LayerUpdateEvent;

/**
 * The {@code ILayerUpdateListener} interface specifies object that may listen for updates of the
 * layers.
 */
public interface ILayerUpdateListener {

  /**
   * Invoked when the layers has been updated.
   */
  void layersUpdated(LayerUpdateEvent e);
}
