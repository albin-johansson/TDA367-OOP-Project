package chalmers.pimp.model.command;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.Stroke;

/**
 * The {@code CommandFactory} class is a factory for creating instances of the {@link ICommand}
 * interface.
 */
public final class CommandFactory {

  private CommandFactory() {
  }

  public static ICommand createStrokeCommand(IModel model, Stroke stroke) {
    return new StrokeCommand(model, stroke);
  }
}