package chalmers.pimp.model.command;

import chalmers.pimp.model.IMementoTarget;
import chalmers.pimp.model.LayerMovement;
import chalmers.pimp.model.LayerRotation;
import chalmers.pimp.model.ModelMemento;
import chalmers.pimp.model.canvas.ICanvas;
import java.util.Objects;

/**
 * The {@code RotateCommand} class is a subclass of {@code AbstractCommand} that represents the
 * action of rotating a layer (by some degree).
 *
 * @see AbstractCommand
 * @see ICommand
 */
final class RotateCommand extends AbstractCommand {

  private final int layerDepthIndex;
  private final double alpha;

  /**
   * @param canvas          the associated canvas instance.
   * @param mementoTarget   the memento target that will be used.
   * @param layerDepthIndex the layer depth index of the affected layer.
   * @param movement        the layer movement instance that describes the movement.
   * @throws NullPointerException if any references are {@code null}.
   */
  RotateCommand(ICanvas canvas, IMementoTarget<ModelMemento> mementoTarget, int layerDepthIndex,
      LayerRotation movement) {
    super(canvas, mementoTarget);
    this.layerDepthIndex = layerDepthIndex;
    Objects.requireNonNull(movement);

    alpha = movement.getCurrentDegree();
    setModelMemento(movement.getModelMemento());
  }

  @Override
  public void execute() {
    updateModelMemento();

    getCanvas().selectLayer(layerDepthIndex);
    getCanvas().setActiveLayerRotation(alpha);
  }

  @Override
  public String getName() {
    return "Rotate Layer";
  }
}
