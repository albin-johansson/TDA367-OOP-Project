package chalmers.pimp.model.command;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.ModelMemento;
import chalmers.pimp.model.Stroke;
import chalmers.pimp.model.canvas.layer.ILayer;

/**
 * The {@code CommandFactory} class is a factory for creating instances of the {@link ICommand}
 * interface.
 */
public final class CommandFactory {

  private CommandFactory() {
  }

  /**
   * Creates and returns a command that represents the action of performing a stroke on the canvas.
   *
   * @param model  the associated model instance.
   * @param stroke the associated stroke instance.
   * @return a command that represents the action of performing a stroke on the canvas.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  public static ICommand createStrokeCommand(IModel model, Stroke stroke) {
    return new StrokeCommand(model, stroke);
  }

  public static ICommand createMoveCommand(IModel model, int associatedLayerDepth, int dx, int dy, ModelMemento modelMemento) {
    return new MoveCommand(model, associatedLayerDepth, dx, dy, modelMemento);
  }

  public static ICommand createAddLayerCommand(IModel model, ILayer layer) {
    return new AddLayerCommand(model, layer);
  }
}