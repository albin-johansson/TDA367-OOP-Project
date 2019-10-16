package chalmers.pimp.model;

import chalmers.pimp.model.canvas.CanvasMemento;
import chalmers.pimp.model.viewport.ViewportModelMemento;
import java.util.Objects;

/**
 * The {@code ModelMemento} class represents the state of the model at some point at time.
 *
 * @see IModel
 */
public final class ModelMemento {

  private final CanvasMemento canvasMemento;
  private final ViewportModelMemento viewportModelMemento;

  /**
   * @param canvasMemento        a memento object for a canvas instance.
   * @param viewportModelMemento a memento object for a viewport model instance.
   * @throws NullPointerException if any references are {@code null}.
   */
  ModelMemento(CanvasMemento canvasMemento, ViewportModelMemento viewportModelMemento) {
    this.canvasMemento = Objects.requireNonNull(canvasMemento);
    this.viewportModelMemento = Objects.requireNonNull(viewportModelMemento);
  }

  /**
   * Returns the canvas memento instance contained by this model memento instance.
   *
   * @return the canvas memento instance contained by this model memento instance.
   */
  CanvasMemento getCanvasMemento() {
    return canvasMemento;
  }

  /**
   * Returns the viewport model memento instance contained by this model memento instance.
   *
   * @return the viewport model memento instance contained by this model memento instance.
   */
  ViewportModelMemento getViewportModelMemento() {
    return viewportModelMemento;
  }
}