package chalmers.pimp.model.command;

/**
 * The {@code ICommand} interface specifies objects that represent some sort of executable and
 * revertable command.
 */
public interface ICommand {

  /**
   * Executes this command.
   */
  void execute();

  /**
   * Reverts the effect of this command.
   */
  void revert();

  /**
   * Returns the name of this command.
   *
   * @return the name of this command.
   */
  String getName();
}