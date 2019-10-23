package chalmers.pimp.model.canvas;

import chalmers.pimp.model.AbstractComposite;
import chalmers.pimp.model.canvas.layer.ILayerUpdateListener;
import java.util.Objects;

/**
 * The {@code LayerUpdateListenerComposite} class is a composite of instances of the {@code
 * ILayerUpdateListener} interface.
 */
final class LayerUpdateListenerComposite extends AbstractComposite<ILayerUpdateListener>
    implements ILayerUpdateListener {

  LayerUpdateListenerComposite() {
  }

  /**
   * Creates a copy of the supplied layer update listener composite. The created copy will claim all
   * of the listeners in the supplied composite.
   *
   * @param composite the composite that will be copied, may not be {@code null}.
   * @throws NullPointerException if the supplied composite is {@code null}.
   */
  LayerUpdateListenerComposite(LayerUpdateListenerComposite composite) {
    Objects.requireNonNull(composite);
    for (ILayerUpdateListener listener : composite) {
      add(listener);
    }
  }

  @Override
  public void layersUpdated(LayerUpdateEvent event) {
    for (ILayerUpdateListener listener : this) {
      listener.layersUpdated(event);
    }
  }
}
