package chalmers.pimp.model;

/**
 * The {@code IUndoRedoListener} interface specifies objects that may listen for undo/redo updates.
 */
public interface IUndoRedoListener {

  /**
   * Invoked when the undo and redo state changed.
   *
   * @param event the event that contains the undo/redo state.
   */
  void undoRedoStateChanged(UndoRedoEvent event);
}