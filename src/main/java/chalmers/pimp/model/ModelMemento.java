package chalmers.pimp.model;

import chalmers.pimp.model.canvas.CanvasMemento;
import chalmers.pimp.model.color.colormodel.ColorModelMemento;
import java.util.Objects;

/**
 * The {@code ModelMemento} class represents the state of the model at some point at time.
 *
 * @see IModel
 */
public final class ModelMemento {

  private final CanvasMemento canvasMemento;
  private final ColorModelMemento colorModelMemento;

  /**
   * @param canvasMemento     a memento object for a canvas instance.
   * @param colorModelMemento         the selected color.
   * @throws NullPointerException if any references are {@code null}.
   */
  ModelMemento(CanvasMemento canvasMemento, ColorModelMemento colorModelMemento) {
    this.canvasMemento = Objects.requireNonNull(canvasMemento);
    this.colorModelMemento = Objects.requireNonNull(colorModelMemento);
  }

  /**
   * Returns the canvas memento instance contained by this model memento instance.
   *
   * @return the canvas memento instance contained by this model memento instance.
   */
  CanvasMemento getCanvasMemento() {
    return canvasMemento;
  }

  ColorModelMemento getColorModelMemento() {
    return colorModelMemento;
  }
}