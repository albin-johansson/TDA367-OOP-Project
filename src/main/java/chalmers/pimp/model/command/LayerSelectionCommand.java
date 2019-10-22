package chalmers.pimp.model.command;

import chalmers.pimp.model.IMementoTarget;
import chalmers.pimp.model.ModelMemento;
import chalmers.pimp.model.canvas.ICanvas;
import java.util.Objects;

/**
 * The {@code LayerSelectionCommand} class is a subclass of {@code AbstractCommand} that represents
 * the action of selecting a layer.
 *
 * @see AbstractCommand
 * @see ICommand
 */
final class LayerSelectionCommand extends AbstractCommand {

  private final ICanvas canvas;
  private final int targetLayerIndex;

  /**
   * @param canvas           the associated canvas instance.
   * @param mementoTarget    the memento target that will be used.
   * @param targetLayerIndex the layer depth index of the layer that will be selected.
   * @throws NullPointerException if any references are {@code null}.
   */
  LayerSelectionCommand(ICanvas canvas, IMementoTarget<ModelMemento> mementoTarget,
      int targetLayerIndex) {
    super(mementoTarget);
    this.canvas = Objects.requireNonNull(canvas);
    this.targetLayerIndex = targetLayerIndex;
  }

  @Override
  public void execute() {
    updateModelMemento();
    canvas.selectLayer(targetLayerIndex);
  }

  @Override
  public String getName() {
    return "Layer Selection";
  }
}