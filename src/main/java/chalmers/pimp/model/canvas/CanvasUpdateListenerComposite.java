package chalmers.pimp.model.canvas;

import chalmers.pimp.model.AbstractComposite;
import java.util.Objects;

/**
 * The {@code CanvasUpdateListenerComposite} class is a composite of instances of the {@code
 * ICanvasUpdateListener} interface.
 */
final class CanvasUpdateListenerComposite extends AbstractComposite<ICanvasUpdateListener>
    implements ICanvasUpdateListener {

  CanvasUpdateListenerComposite() {
  }

  /**
   * Creates a copy of the supplied canvas update listener composite. The created copy will claim
   * all of the listeners in the supplied composite.
   *
   * @param composite the composite that will be copied, may not be {@code null}.
   * @throws NullPointerException if the supplied composite is {@code null}.
   */
  CanvasUpdateListenerComposite(CanvasUpdateListenerComposite composite) {
    Objects.requireNonNull(composite);
    for (ICanvasUpdateListener listener : composite) {
      add(listener);
    }
  }

  @Override
  public void canvasUpdated() {
    for (ICanvasUpdateListener listener : this) {
      listener.canvasUpdated();
    }
  }
}