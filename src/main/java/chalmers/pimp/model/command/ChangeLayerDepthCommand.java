package chalmers.pimp.model.command;

import chalmers.pimp.model.IMementoTarget;
import chalmers.pimp.model.ModelMemento;
import chalmers.pimp.model.canvas.ICanvas;

/**
 * The {@code ChangeLayerDepthCommand} class is a subclass of {@code AbstractCommand} that
 * represents the action of rearranging the z-order a layer.
 *
 * @see AbstractCommand
 * @see ICommand
 */
final class ChangeLayerDepthCommand extends AbstractCommand {

  private final int baseDepthIndex;
  private final int dz;

  ChangeLayerDepthCommand(ICanvas canvas, IMementoTarget<ModelMemento> mementoTarget,
      int baseDepthIndex, int dz) {
    super(canvas, mementoTarget);
    this.baseDepthIndex = baseDepthIndex;
    this.dz = dz;
  }

  @Override
  public void execute() {
    updateModelMemento();
    getCanvas().changeDepthIndex(baseDepthIndex, dz);
  }

  @Override
  public String getName() {
    return "Rearranged Layers";
  }
}