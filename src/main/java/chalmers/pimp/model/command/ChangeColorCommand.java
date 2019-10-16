package chalmers.pimp.model.command;

import chalmers.pimp.model.IMementoTarget;
import chalmers.pimp.model.ModelMemento;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.color.colormodel.IColorModel;

/**
 * The {@code ChangeColorCommand} class is a command that represents a change of color.
 *
 * @see ICommand
 */
public class ChangeColorCommand implements ICommand {

  private final IMementoTarget<ModelMemento> mementoTarget;
  private final IColorModel colorModel;
  private final IColor color;
  private ModelMemento modelMemento;

  /**
   * @param mementoTarget the memento target that will be used.
   * @param colorModel    the representation of the color in the model.
   * @param color         the mew color.
   * @throws NullPointerException if any references are {@code null}.
   */
  ChangeColorCommand(
      IMementoTarget<ModelMemento> mementoTarget,
      IColorModel colorModel,
      IColor color
  ) {
    this.mementoTarget = mementoTarget;
    this.colorModel = colorModel;
    this.color = color;
  }

  @Override
  public void execute() {
    modelMemento = mementoTarget.createSnapShot();
    colorModel.setColor(color);
  }

  @Override
  public void revert() {
    if (modelMemento != null) {
      mementoTarget.restore(modelMemento);
    }
  }

  @Override
  public String getName() {
    return "Change color";
  }
}
