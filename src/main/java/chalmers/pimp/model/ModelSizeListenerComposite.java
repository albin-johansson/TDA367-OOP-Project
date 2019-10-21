package chalmers.pimp.model;

/**
 * The {@code ModelSizeListenerComposite} class represents a composite of instances of the {@code
 * IModelSizeListener} interface.
 *
 * @see IModelSizeListener
 */
final class ModelSizeListenerComposite extends AbstractComposite<IModelSizeListener>
    implements IModelSizeListener {

  ModelSizeListenerComposite() {
  }

  @Override
  public void sizeUpdated(int width, int height) {
    for (IModelSizeListener listener : this) {
      listener.sizeUpdated(width, height);
    }
  }
}