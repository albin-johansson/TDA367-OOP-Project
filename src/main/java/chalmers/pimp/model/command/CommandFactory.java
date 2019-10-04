package chalmers.pimp.model.command;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.ModelMemento;
import chalmers.pimp.model.Stroke;
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

  /**
   * Creates and returns a command that represents the action of performing a stroke on the canvas.
   *
   * @param model  the associated model instance.
   * @param stroke the associated stroke instance.
   * @return a command that represents the action of performing a stroke on the canvas.
   * @throws NullPointerException if any references are {@code null}.
   */
  public static ICommand createStrokeCommand(IModel model, Stroke stroke) {
    return new StrokeCommand(model, stroke);
  }

  /**
   * Creates and returns a command that represents the action of moving a layer.
   *
   * @param model      the associated model instance.
   * @param layerDepth the depth index of the layer that will be moved.
   * @param x          the x-coordinate that the layer will be moved to.
   * @param y          the y-coordinate that the layer will be moved to.
   * @param memento    the model memento that holds the state of the model at the time the move
   *                   command is created.
   * @throws NullPointerException if any references are {@code null}.
   */
  public static ICommand createMoveCommand(IModel model, int layerDepth, int x, int y,
      ModelMemento memento) {
    return new MoveCommand(model, layerDepth, x, y, memento);
  }

  /**
   * Creates and returns a command that represents the action of adding a layer.
   *
   * @param model the associated model instance.
   * @param layer the layer that will be added.
   * @return a command that represents the action of adding a layer.
   * @throws NullPointerException if any references are {@code null}.
   */
  public static ICommand createAddLayerCommand(IModel model, ILayer layer) {
    return new AddLayerCommand(model, layer);
  }
}