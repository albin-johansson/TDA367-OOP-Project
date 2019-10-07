package chalmers.pimp.model.canvas;

import chalmers.pimp.model.AbstractComposite;

/**
 * The {@code CanvasUpdateListenerComposite} class is a composite of instances of the {@code
 * ICanvasUpdateListener} interface.
 */
final class CanvasUpdateListenerComposite extends AbstractComposite<ICanvasUpdateListener>
    implements ICanvasUpdateListener {

  CanvasUpdateListenerComposite() {
  }

  @Override
  public void canvasUpdated() {
    for (ICanvasUpdateListener listener : this) {
      listener.canvasUpdated();
    }
  }
}