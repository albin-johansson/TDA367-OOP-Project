package chalmers.pimp.model.command;

import chalmers.pimp.model.IMementoTarget;
import chalmers.pimp.model.ModelMemento;
import chalmers.pimp.model.canvas.ICanvas;
import java.util.Objects;

/**
 * The {@code RemoveLayerCommand} class is an implementation of {@code ICommand} that represents the
 * action of removing a layer from the canvas.
 */
final class RemoveLayerCommand implements ICommand {

  private final IMementoTarget<ModelMemento> mementoTarget;
  private final ICanvas canvas;
  private final int layerDepthIndex;
  private ModelMemento modelMemento;

  /**
   * @param canvas          the associated canvas instance.
   * @param mementoTarget   the memento target that will be used.
   * @param layerDepthIndex the layer depth index of the layer that will be removed.
   * @throws NullPointerException if any references are {@code null}.
   */
  RemoveLayerCommand(ICanvas canvas, IMementoTarget<ModelMemento> mementoTarget,
      int layerDepthIndex) {
    this.canvas = Objects.requireNonNull(canvas);
    this.mementoTarget = Objects.requireNonNull(mementoTarget);
    this.layerDepthIndex = layerDepthIndex;
    modelMemento = null;
  }

  @Override
  public void execute() {
    modelMemento = mementoTarget.createSnapShot();

    canvas.removeLayer(layerDepthIndex);
  }

  @Override
  public void revert() {
    if (modelMemento != null) {
      mementoTarget.restore(modelMemento);
    }
  }

  @Override
  public String getName() {
    return "Remove Layer";
  }
}