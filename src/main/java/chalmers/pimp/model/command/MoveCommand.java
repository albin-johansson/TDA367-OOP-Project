package chalmers.pimp.model.command;

import chalmers.pimp.model.IMementoTarget;
import chalmers.pimp.model.LayerMovement;
import chalmers.pimp.model.ModelMemento;
import chalmers.pimp.model.canvas.ICanvas;
import java.util.Objects;

/**
 * The {@code MoveCommand} class is a subclass of {@code AbstractCommand} that represents the action
 * of moving a layer (by changing its x- and y-coordinates).
 *
 * @see AbstractCommand
 * @see ICommand
 */
final class MoveCommand extends AbstractCommand {

  private final ICanvas canvas;
  private final int layerDepthIndex;
  private final int x;
  private final int y;

  /**
   * @param canvas          the associated canvas instance.
   * @param mementoTarget   the memento target that will be used.
   * @param layerDepthIndex the layer depth index of the affected layer.
   * @param movement        the layer movement instance that describes the movement.
   * @throws NullPointerException if any references are {@code null}.
   */
  MoveCommand(ICanvas canvas, IMementoTarget<ModelMemento> mementoTarget, int layerDepthIndex,
      LayerMovement movement) {
    super(mementoTarget);
    this.canvas = Objects.requireNonNull(canvas);
    this.layerDepthIndex = layerDepthIndex;
    Objects.requireNonNull(movement);

    x = movement.getEndX();
    y = movement.getEndY();
    setModelMemento(movement.getModelMemento());
  }

  @Override
  public void execute() {
    updateModelMemento();

    canvas.selectLayer(layerDepthIndex);
    canvas.setActiveLayerX(x);
    canvas.setActiveLayerY(y);
  }

  @Override
  public String getName() {
    return "Move Layer";
  }
}