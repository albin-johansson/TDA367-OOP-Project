package chalmers.pimp.model.command;

import java.util.Objects;

/**
 * The {@code UndoRedoEvent} class represents the state of an undo/redo event update.
 */
public final class UndoRedoEvent {

  /**
   * The default command name for either the undo and redo name properties.
   */
  static final String DEFAULT_COMMAND_NAME = "N/A";

  private boolean isUndoable;
  private boolean isRedoable;
  private String undoCommandName;
  private String redoCommandName;

  UndoRedoEvent() {
    undoCommandName = DEFAULT_COMMAND_NAME;
    redoCommandName = DEFAULT_COMMAND_NAME;
    isUndoable = false;
    isRedoable = false;
  }

  /**
   * Sets whether there is an available undo command.
   *
   * @param isUndoable {@code true} if there is at least one undoable command; {@code false}
   *                   otherwise.
   */
  void setUndoable(boolean isUndoable) {
    this.isUndoable = isUndoable;
  }

  /**
   * Sets whether there is an available redo command.
   *
   * @param isRedoable {@code true} if there is at least one redoable command; {@code false}
   *                   otherwise.
   */
  void setRedoable(boolean isRedoable) {
    this.isRedoable = isRedoable;
  }

  /**
   * Sets the name of the available undo command. By default, this property is set to {@value
   * UndoRedoEvent#DEFAULT_COMMAND_NAME}.
   *
   * @param undoCommandName the name of the available undo command.
   * @throws NullPointerException if the supplied string is {@code null}.
   */
  void setUndoCommandName(String undoCommandName) {
    this.undoCommandName = Objects.requireNonNull(undoCommandName);
  }

  /**
   * Sets the name of the available redo command. By default, this property is set to {@value
   * DEFAULT_COMMAND_NAME}.
   *
   * @param redoCommandName the name of the available redo command.
   * @throws NullPointerException if the supplied string is {@code null}.
   */
  void setRedoCommandName(String redoCommandName) {
    this.redoCommandName = Objects.requireNonNull(redoCommandName);
  }

  /**
   * Returns the name of the available undo command. By default, {@value DEFAULT_COMMAND_NAME} is
   * returned.
   *
   * @return the name of the available undo command.
   */
  public String getUndoCommandName() {
    return undoCommandName;
  }

  /**
   * Returns the name of the available redo command. By default, {@value DEFAULT_COMMAND_NAME} is
   * returned.
   *
   * @return the name of the available redo command.
   */
  public String getRedoCommandName() {
    return redoCommandName;
  }

  /**
   * Indicates whether or not there is an undoable command.
   *
   * @return {@code true} if there is at least one undoable command; {@code false} otherwise.
   */
  public boolean isUndoable() {
    return isUndoable;
  }

  /**
   * Indicates whether or not there is an redoable command.
   *
   * @return {@code true} if there is at least one redoable command; {@code false} otherwise.
   */
  public boolean isRedoable() {
    return isRedoable;
  }
}