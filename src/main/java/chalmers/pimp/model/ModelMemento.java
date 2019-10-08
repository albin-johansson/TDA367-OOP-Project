package chalmers.pimp.model;

import chalmers.pimp.model.canvas.CanvasMemento;
import java.util.Objects;

/**
 * The {@code ModelMemento} class represents the state of the model at some point at time.
 *
 * @see IModel
 */
public final class ModelMemento {

  private final CanvasMemento canvasMemento;

  /**
   * @param canvasMemento a memento object for a canvas instance.
   * @throws NullPointerException if any references are {@code null}.
   */
  ModelMemento(CanvasMemento canvasMemento) {
    this.canvasMemento = Objects.requireNonNull(canvasMemento);
  }

  /**
   * Returns the canvas memento instance contained by this model memento instance.
   *
   * @return the canvas memento instance contained by this model memento instance.
   */
  CanvasMemento getCanvasMemento() {
    return canvasMemento;
  }
}