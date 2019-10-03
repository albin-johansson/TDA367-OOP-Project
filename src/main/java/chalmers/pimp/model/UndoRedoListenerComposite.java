package chalmers.pimp.model;

/**
 * The {@code UndoRedoListenerComposite} class is a subclass of {@link AbstractComposite} that
 * implements the {@link IUndoRedoListener} interface.
 */
final class UndoRedoListenerComposite extends AbstractComposite<IUndoRedoListener>
    implements IUndoRedoListener {

  UndoRedoListenerComposite() {
  }

  @Override
  public void undoRedoStateChanged(UndoRedoEvent event) {
    for (IUndoRedoListener listener : this) {
      listener.undoRedoStateChanged(event);
    }
  }
}