package chalmers.pimp.model.canvas;

/**
 * The {@code ILayerUpdateListener} interface specifies object that may listen for updates of the
 * layers.
 */
public interface ILayerUpdateListener { // TODO move to layer package

  /**
   * Invoked when the layers has been updated.
   */
  void layersUpdated(LayerUpdateEvent e);
}
