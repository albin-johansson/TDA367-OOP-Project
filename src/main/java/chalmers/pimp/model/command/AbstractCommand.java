package chalmers.pimp.model.command;

import chalmers.pimp.model.IMementoTarget;
import chalmers.pimp.model.ModelMemento;
import chalmers.pimp.model.canvas.ICanvas;
import java.util.Objects;

/**
 * The {@code AbstractCommand} class is a partial implementation of the {@code ICommand} interface.
 * This class assumes the subclasses
 *
 * @see ICommand
 */
abstract class AbstractCommand implements ICommand {

  private final ICanvas canvas;
  private final IMementoTarget<ModelMemento> mementoTarget;
  private ModelMemento modelMemento;

  /**
   * @param canvas        the associated canvas instance.
   * @param mementoTarget the memento target that will be used.
   * @throws NullPointerException if any references are {@code null}.
   */
  AbstractCommand(ICanvas canvas, IMementoTarget<ModelMemento> mementoTarget) {
    this.canvas = Objects.requireNonNull(canvas);
    this.mementoTarget = Objects.requireNonNull(mementoTarget);
  }

  final void updateModelMemento() {
    modelMemento = mementoTarget.createSnapShot();
  }

  /**
   * Sets the value of model memento property.
   *
   * @param modelMemento the new value of the model memento property, may be {@code null}.
   */
  void setModelMemento(ModelMemento modelMemento) {
    this.modelMemento = modelMemento;
  }

  /**
   * Returns the associated canvas instance.
   *
   * @return the associated canvas instance.
   */
  final ICanvas getCanvas() {
    return canvas;
  }

  @Override
  public final void revert() {
    if (modelMemento != null) {
      mementoTarget.restore(modelMemento);
    }
  }
}