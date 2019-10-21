package chalmers.pimp.model.command;

import chalmers.pimp.model.IChangeable;
import chalmers.pimp.model.IUndoRedoListener;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

/**
 * The {@code CommandManager} class is responsible for handling instances of the {@link ICommand}
 * interface and tracking them in undo and redo stacks.
 */
public final class CommandManager implements IChangeable {

  /**
   * The maximum amount of commands in either the undo or redo stack.
   */
  private static final int THRESHOLD = 25;

  private final Deque<ICommand> undoDeque;
  private final Deque<ICommand> redoDeque;
  private final UndoRedoListenerComposite undoRedoListeners;

  public CommandManager() {
    undoDeque = new ArrayDeque<>(THRESHOLD);
    redoDeque = new ArrayDeque<>(THRESHOLD);
    undoRedoListeners = new UndoRedoListenerComposite();
  }

  /**
   * Notifies all registered undo/redo listeners.
   */
  private void notifyUndoRedoListeners() {
    var event = new UndoRedoEvent();

    boolean isUndoable = isUndoable();
    boolean isRedoable = isRedoable();

    event.setUndoable(isUndoable);
    event.setRedoable(isRedoable);

    if (isUndoable) {
      event.setUndoCommandName(getUndoCommandName());
    }

    if (isRedoable) {
      event.setRedoCommandName(getRedoCommandName());
    }

    undoRedoListeners.undoRedoStateChanged(event);
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

    notifyUndoRedoListeners();
  }

  /**
   * Adds a undo/redo listener to this command manager.
   *
   * @param listener the undo/redo listener that will be added.
   * @throws NullPointerException if the supplied listener is {@code null}.
   */
  public void addUndoRedoListener(IUndoRedoListener listener) {
    undoRedoListeners.add(listener);
  }

  /**
   * Indicates whether or not there is an undoable command.
   *
   * @return {@code true} if there is an undoable command; {@code false} otherwise.
   */
  private boolean isUndoable() {
    return !undoDeque.isEmpty();
  }

  /**
   * Indicates whether or not there is an redoable command.
   *
   * @return {@code true} if there is an redoable command; {@code false} otherwise.
   */
  private boolean isRedoable() {
    return !redoDeque.isEmpty();
  }

  /**
   * Returns the name of the next undoable command.
   *
   * @return the name of the next undoable command, or {@code null} if there is no undoable command.
   */
  private String getUndoCommandName() {
    return (undoDeque.size() >= 1) ? undoDeque.peek().getName() : null;
  }

  /**
   * Returns the name of the next redoable command.
   *
   * @return the name of the next redoable command, or {@code null} if there is no redoable command.
   */
  private String getRedoCommandName() {
    return (redoDeque.size() >= 1) ? redoDeque.peek().getName() : null;
  }

  @Override
  public void undo() {
    if (isUndoable()) {
      ICommand command = undoDeque.pop();
      command.revert();

      redoDeque.push(command);
      ensureSize(redoDeque);

      notifyUndoRedoListeners();
    }
  }

  @Override
  public void redo() {
    if (isRedoable()) {
      ICommand command = redoDeque.pop();
      command.execute();

      undoDeque.push(command);
      ensureSize(undoDeque);

      notifyUndoRedoListeners();
    }
  }
}