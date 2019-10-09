package chalmers.pimp.model.command;

/**
 * The {@code MockCommand} is an implementation of the {@link ICommand} interface, intended for
 * testing only.
 */
final class MockCommand implements ICommand {

  /**
   * The name used by all instances of the {@code MockCommand} class.
   */
  static final String NAME = "MockCommand";

  private int nExecuted;
  private int nReverted;

  MockCommand() {
    nExecuted = 0;
  }

  /**
   * Returns the current amount of times the {@link MockCommand#execute()} method has been invoked.
   *
   * @return the current amount of times the {@link MockCommand#execute()} method has been invoked.
   */
  int getExecutedAmount() {
    return nExecuted;
  }

  /**
   * Returns the current amount of times the {@link MockCommand#revert()} method has been invoked.
   *
   * @return the current amount of times the {@link MockCommand#revert()} method has been invoked.
   */
  int getRevertedAmount() {
    return nReverted;
  }

  @Override
  public void execute() {
    ++nExecuted;
  }

  @Override
  public void revert() {
    ++nReverted;
  }

  @Override
  public String getName() {
    return NAME;
  }
}
