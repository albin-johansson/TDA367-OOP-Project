package chalmers.pimp.model.command;

import chalmers.pimp.model.IMementoTarget;
import chalmers.pimp.model.ModelMemento;
import java.util.Objects;

/**
 * The {@code AbstractCommand} class is a partial implementation of the {@code ICommand} interface.
 * This class assumes the subclasses
 *
 * @see ICommand
 */
abstract class AbstractCommand implements ICommand {

  private final IMementoTarget<ModelMemento> mementoTarget;
  private ModelMemento modelMemento;

  /**
   * @param mementoTarget the memento target that will be used.
   * @throws NullPointerException if any references are {@code null}.
   */
  AbstractCommand(IMementoTarget<ModelMemento> mementoTarget) {
    this.mementoTarget = Objects.requireNonNull(mementoTarget);
  }

  /**
   * Updates the internal model memento reference, this is done by creating a snap shot of the
   * associated memento target.
   */
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

  @Override
  public final void revert() {
    if (modelMemento != null) {
      mementoTarget.restore(modelMemento);
    }
  }
}