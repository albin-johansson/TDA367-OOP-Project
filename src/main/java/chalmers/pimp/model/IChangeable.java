package chalmers.pimp.model;

/**
 * The {@code IChangeable} interface specifies objects that allow for their state to be cycled
 * through undo and redo actions.
 */
public interface IChangeable {

  /**
   * Attempts to undo an action.
   */
  void undo();

  /**
   * Attempts to redo an undone action.
   */
  void redo();
}
