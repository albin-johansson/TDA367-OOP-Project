package model.canvas;

/**
 * The {@code ILayerUpdateListener} interface specifies object that may listen for updates of the
 * layers.
 */
public interface ILayerUpdateListener {

  /**
   * Invoked when the layers has been updated.
   */
  void layersUpdated(); // TODO add LayersUpdateEvent parameter

}
