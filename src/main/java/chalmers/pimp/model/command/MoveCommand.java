package chalmers.pimp.model.command;

import chalmers.pimp.model.IMementoTarget;
import chalmers.pimp.model.LayerMovement;
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

  private final ICanvas canvas;
  private final IMementoTarget<ModelMemento> mementoTarget;
  private final int layerDepthIndex;
  private final int x;
  private final int y;
  private ModelMemento modelMemento;

  /**
   * @param canvas          the associated canvas instance.
   * @param mementoTarget   the memento target that will be used.
   * @param layerDepthIndex the layer depth index of the affected layer.
   * @param movement        the layer movement instance that describes the movement.
   * @throws NullPointerException if any references are {@code null}.
   */
  MoveCommand(ICanvas canvas, IMementoTarget<ModelMemento> mementoTarget, int layerDepthIndex,
      LayerMovement movement) {
    this.canvas = Objects.requireNonNull(canvas);
    this.mementoTarget = Objects.requireNonNull(mementoTarget);
    this.layerDepthIndex = layerDepthIndex;
    Objects.requireNonNull(movement);

    x = movement.getEndX();
    y = movement.getEndY();
    modelMemento = Objects.requireNonNull(movement.getModelMemento());
  }

  @Override
  public void execute() {
    modelMemento = mementoTarget.createSnapShot();

    canvas.selectLayer(layerDepthIndex);
    canvas.setActiveLayerX(x);
    canvas.setActiveLayerY(y);
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