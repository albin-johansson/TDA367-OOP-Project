package chalmers.pimp.model.command;

import chalmers.pimp.model.IMementoTarget;
import chalmers.pimp.model.ModelMemento;
import chalmers.pimp.model.canvas.ICanvas;

/**
 * The {@code RemoveLayerCommand} class is a subclass of {@code AbstractCommand} that represents the
 * action of removing a layer.
 *
 * @see AbstractCommand
 * @see ICommand
 */
final class RemoveLayerCommand extends AbstractCommand {

  private final int layerDepthIndex;

  /**
   * @param canvas          the associated canvas instance.
   * @param mementoTarget   the memento target that will be used.
   * @param layerDepthIndex the layer depth index of the layer that will be removed.
   * @throws NullPointerException if any references are {@code null}.
   */
  RemoveLayerCommand(ICanvas canvas, IMementoTarget<ModelMemento> mementoTarget,
      int layerDepthIndex) {
    super(canvas, mementoTarget);
    this.layerDepthIndex = layerDepthIndex;
  }

  @Override
  public void execute() {
    updateModelMemento();
    getCanvas().removeLayer(layerDepthIndex);
  }

  @Override
  public String getName() {
    return "Remove Layer";
  }
}