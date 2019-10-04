package chalmers.pimp.model.command;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.ModelMemento;
import java.util.Objects;

/**
 * The {@code MoveCommand} represents the action of moving a layer.
 *
 * @see ICommand
 * @see CommandFactory
 */
final class MoveCommand implements ICommand {

  private final IModel model;
  private final int layerDepth;
  private final int x;
  private final int y;
  private ModelMemento modelMemento;

  /**
   * @param model        the associated model instance.
   * @param layerDepth   the depth index of the layer that will be moved.
   * @param x            the x-coordinate that the layer will be moved to.
   * @param y            the y-coordinate that the layer will be moved to.
   * @param modelMemento the model memento that holds the state of the model at the time the move
   *                     command is created.
   * @throws NullPointerException if any references are {@code null}.
   */
  MoveCommand(IModel model, int layerDepth, int x, int y, ModelMemento modelMemento) {
    this.model = Objects.requireNonNull(model);
    this.layerDepth = layerDepth;
    this.x = x;
    this.y = y;
    this.modelMemento = Objects.requireNonNull(modelMemento);
  }

  @Override
  public void execute() {
    modelMemento = model.createSnapShot();

    model.selectLayer(layerDepth);
    model.setLayerX(x);
    model.setLayerY(y);
  }

  @Override
  public void revert() {
    if (modelMemento != null) {
      model.restore(modelMemento);
    }
  }

  @Override
  public String getName() {
    return "Move Layer";
  }
}