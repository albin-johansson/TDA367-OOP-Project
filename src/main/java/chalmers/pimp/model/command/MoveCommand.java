package chalmers.pimp.model.command;

import chalmers.pimp.model.IMementoTarget;
import chalmers.pimp.model.ModelMemento;
import chalmers.pimp.model.canvas.ICanvas;
import java.util.Objects;

/**
 * The {@code MoveCommand} represents the action of moving a layer.
 *
 * @see ICommand
 * @see CommandFactory
 */
final class MoveCommand implements ICommand {

  private final ICanvas layerModel;
  private final IMementoTarget<ModelMemento> mementoTarget;
  private final int layerDepth;
  private final int x;
  private final int y;
  private ModelMemento modelMemento;

  MoveCommand(ICanvas layerModel, IMementoTarget<ModelMemento> mementoTarget, int layerDepth,
      int x, int y, ModelMemento modelMemento) {
    this.layerModel = Objects.requireNonNull(layerModel);
    this.mementoTarget = mementoTarget;
    this.layerDepth = layerDepth;
    this.x = x;
    this.y = y;
    this.modelMemento = Objects.requireNonNull(modelMemento);
  }

  @Override
  public void execute() {
    modelMemento = mementoTarget.createSnapShot();

    layerModel.selectLayer(layerDepth);
    layerModel.setActiveLayerX(x);
    layerModel.setActiveLayerY(y);
  }

  @Override
  public void revert() {
    if (modelMemento != null) {
      mementoTarget.restore(modelMemento);
    }
  }

  @Override
  public String getName() {
    return "Move Layer";
  }
}