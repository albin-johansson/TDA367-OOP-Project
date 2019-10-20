package chalmers.pimp.model;

/**
 * The {@code IModelSizeListener} interface specifies objects that may listen for model canvas size
 * updates.
 */
public interface IModelSizeListener {

  /**
   * Invoked when the model canvas size is updated.
   *
   * @param width  the new width of the model width.
   * @param height the new height of the model height.
   */
  void sizeUpdated(int width, int height);
}
