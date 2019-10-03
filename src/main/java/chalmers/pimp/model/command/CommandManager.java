package chalmers.pimp.model.command;

import chalmers.pimp.model.IChangeable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

/**
 * The {@code CommandManager} class is responsible for handling instances of the {@link IChangeable}
 * interface and tracking them in undo and redo stacks.
 */
public final class CommandManager implements IChangeable {

  /**
   * The maximum amount of commands in either the undo or redo stack.
   */
  private static final int THRESHOLD = 45;

  private final Deque<ICommand> undoDeque;
  private final Deque<ICommand> redoDeque;

  public CommandManager() {
    undoDeque = new ArrayDeque<>(THRESHOLD);
    redoDeque = new ArrayDeque<>(THRESHOLD);
  }

  /**
   * Ensures that the supplied deque doesn't supercede the value of {@link
   * CommandManager#THRESHOLD}.
   *
   * @param deque the deque that will be checked.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  private void ensureSize(Deque<?> deque) {
    Objects.requireNonNull(deque);
    while (deque.size() > THRESHOLD) {
      deque.removeLast();
    }
  }

  /**
   * Adds the supplied command to the command manager. Invoking this method clears the redo stack.
   *
   * @param command the command that will be added.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  public void insertCommand(ICommand command) {
    Objects.requireNonNull(command);
    ensureSize(undoDeque);
    undoDeque.push(command);
    redoDeque.clear();
  }

  /**
   * Indicates whether or not there is an undoable command.
   *
   * @return {@code true} if there is an undoable command; {@code false} otherwise.
   */
  public boolean isUndoable() {
    return !undoDeque.isEmpty();
  }

  /**
   * Indicates whether or not there is an redoable command.
   *
   * @return {@code true} if there is an redoable command; {@code false} otherwise.
   */
  public boolean isRedoable() {
    return !redoDeque.isEmpty();
  }

  /**
   * Returns the name of the next undoable command.
   *
   * @return the name of the next undoable command, or {@code null} if there is no undoable command.
   */
  public String getUndoCommandName() {
    return (undoDeque.size() >= 1) ? undoDeque.peek().getName() : null;
  }

  /**
   * Returns the name of the next redoable command.
   *
   * @return the name of the next redoable command, or {@code null} if there is no redoable command.
   */
  public String getRedoCommandName() {
    return (redoDeque.size() >= 1) ? redoDeque.peek().getName() : null;
  }

  @Override
  public void undo() {
    if (isUndoable()) {
      ICommand command = undoDeque.pop();
      command.revert();

      redoDeque.push(command);
      ensureSize(redoDeque);
    }
  }

  @Override
  public void redo() {
    if (isRedoable()) {
      ICommand command = redoDeque.pop();
      command.execute();

      undoDeque.push(command);
      ensureSize(undoDeque);
    }
  }
}