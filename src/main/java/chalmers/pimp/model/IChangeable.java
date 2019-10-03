package chalmers.pimp.model;

/**
 * The {@code IChangeable} interface specifies objects that allow for their state to be cycled
 * through undo and redo actions.
 */
public interface IChangeable { // TODO is this interface necessary?

  /**
   * Undoes the last performed action.
   */
  void undo();

  /**
   * Redoes the last undone action.
   */
  void redo(); // TODO improve doc
}
