package chalmers.pimp.model.command;

import chalmers.pimp.model.IMementoTarget;
import chalmers.pimp.model.ModelMemento;
import chalmers.pimp.model.Stroke;
import chalmers.pimp.model.canvas.ICanvas;
import chalmers.pimp.model.canvas.layer.ILayer;

/**
 * The {@code CommandFactory} class is a factory for creating instances of the {@link ICommand}
 * interface.
 *
 * @see ICommand
 */
public final class CommandFactory {

  private CommandFactory() {
  }

  public static ICommand createStrokeCommand(ICanvas canvas,
      IMementoTarget<ModelMemento> mementoTarget, Stroke stroke) {
    return new StrokeCommand(canvas, mementoTarget, stroke);
  }

  public static ICommand createMoveCommand(ICanvas canvas,
      IMementoTarget<ModelMemento> mementoTarget, int layerDepth, int x, int y,
      ModelMemento memento) {
    return new MoveCommand(canvas, mementoTarget, layerDepth, x, y, memento);
  }

  public static ICommand createAddLayerCommand(ICanvas canvas,
      IMementoTarget<ModelMemento> mementoTarget, ILayer layer) {
    return new AddLayerCommand(canvas, mementoTarget, layer);
  }
}