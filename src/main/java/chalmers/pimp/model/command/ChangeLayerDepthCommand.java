package chalmers.pimp.model.command;

import chalmers.pimp.model.IMementoTarget;
import chalmers.pimp.model.ModelMemento;
import chalmers.pimp.model.canvas.ICanvas;
import java.util.Objects;

/**
 * The {@code ChangeLayerDepthCommand} class is a subclass of {@code AbstractCommand} that
 * represents the action of rearranging the z-order a layer.
 *
 * @see AbstractCommand
 * @see ICommand
 */
final class ChangeLayerDepthCommand extends AbstractCommand {

  private final ICanvas canvas;
  private final int baseDepthIndex;
  private final int dz;

  /**
   * @param canvas         the associated canvas instance.
   * @param mementoTarget  the memento target that will be used.
   * @param baseDepthIndex the layer depth index of the layer that will be "moved".
   * @param dz             the delta z value (the offset), may be either negative or positive.
   * @throws NullPointerException if any references are {@code null}.
   */
  ChangeLayerDepthCommand(ICanvas canvas, IMementoTarget<ModelMemento> mementoTarget,
      int baseDepthIndex, int dz) {
    super(mementoTarget);
    this.canvas = Objects.requireNonNull(canvas);
    this.baseDepthIndex = baseDepthIndex;
    this.dz = dz;
  }

  @Override
  public void execute() {
    updateModelMemento();
    canvas.changeDepthIndex(baseDepthIndex, dz);
  }

  @Override
  public String getName() {
    return "Rearranged Layers";
  }
}