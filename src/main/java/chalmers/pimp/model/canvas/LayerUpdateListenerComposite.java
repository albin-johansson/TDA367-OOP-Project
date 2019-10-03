package chalmers.pimp.model.canvas;

import chalmers.pimp.model.AbstractComposite;
import chalmers.pimp.model.canvas.layer.ILayerUpdateListener;
import chalmers.pimp.model.canvas.layer.LayerUpdateEvent;

/**
 * The {@code LayerUpdateListenerComposite} class is a composite of instances of the {@code
 * ILayerUpdateListener} interface.
 */
final class LayerUpdateListenerComposite extends AbstractComposite<ILayerUpdateListener>
    implements ILayerUpdateListener {

  LayerUpdateListenerComposite() {
  }

  @Override
  public void layersUpdated(LayerUpdateEvent e) {
    for (ILayerUpdateListener listener : this) {
      listener.layersUpdated(e);
    }
  }
}
