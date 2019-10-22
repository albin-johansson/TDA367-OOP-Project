package chalmers.pimp.model.command;

import chalmers.pimp.model.IMementoTarget;
import chalmers.pimp.model.ModelMemento;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.color.colormodel.IColorModel;
import java.util.Objects;

/**
 * The {@code ChangeColorCommand} class represents the action of changing the canvas color.
 *
 * @see AbstractCommand
 * @see ICommand
 */
final class ChangeColorCommand extends AbstractCommand {

  private final IColorModel colorModel;
  private final IColor color;

  /**
   * @param colorModel    the representation of the color in the model.
   * @param mementoTarget the memento target that will be used.
   * @param color         the mew color.
   * @throws NullPointerException if any references are {@code null}.
   */
  ChangeColorCommand(IColorModel colorModel, IMementoTarget<ModelMemento> mementoTarget,
      IColor color) {
    super(mementoTarget);
    this.colorModel = Objects.requireNonNull(colorModel);
    this.color = Objects.requireNonNull(color);
  }

  @Override
  public void execute() {
    updateModelMemento();
    colorModel.setColor(color);
  }

  @Override
  public String getName() {
    return "Change color";
  }
}
