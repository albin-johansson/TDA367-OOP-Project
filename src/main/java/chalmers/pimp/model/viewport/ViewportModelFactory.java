package chalmers.pimp.model.viewport;

/**
 * The {@code ViewportModelFactory} class is a factory for creating instances of the {@code
 * IViewportModel} interface.
 *
 * @see IViewportModel
 * @see IReadOnlyViewport
 */
public final class ViewportModelFactory {

  private ViewportModelFactory() {
  }

  /**
   * Creates and returns a viewport model instance.
   *
   * @return a viewport model instance.
   */
  public static IViewportModel createViewportModel() {
    return new ViewportModelImpl();
  }
}