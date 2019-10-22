package chalmers.pimp.model.viewport;

/**
 * The {@code ViewportFactory} class is a factory for creating instances of the {@code
 * IReadOnlyViewport} interface.
 *
 * @see IReadOnlyViewport
 */
public final class ViewportFactory {

  private ViewportFactory() {
  }

  /**
   * Creates and returns a read-only viewport with the supplied dimensions.
   *
   * @param x      the x-coordinate of the viewport.
   * @param y      the y-coordinate of the viewport.
   * @param width  the width of the viewport, must be greater than zero.
   * @param height the height of the viewport, must be greater than zero.
   * @return a read-only viewport.
   * @throws IllegalArgumentException if the supplied width or height aren't greater than zero.
   */
  public static IReadOnlyViewport createViewport(int x, int y, int width, int height) {
    var viewport = new Viewport();
    viewport.setX(x);
    viewport.setY(y);
    viewport.setWidth(width);
    viewport.setHeight(height);
    return viewport;
  }
}