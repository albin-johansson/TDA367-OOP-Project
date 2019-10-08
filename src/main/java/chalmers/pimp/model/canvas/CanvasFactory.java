package chalmers.pimp.model.canvas;

/**
 * The {@code CanvasFactory} class is a factory for creating instances of the {@code ICanvas}
 * interface.
 *
 * @see ICanvas
 */
public final class CanvasFactory {

  private CanvasFactory() {
  }

  /**
   * Creates and returns a canvas instance.
   *
   * @return a canvas instance.
   */
  public static ICanvas createCanvas() {
    return new CanvasImpl();
  }
}