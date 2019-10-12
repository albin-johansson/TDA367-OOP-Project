package chalmers.pimp.model.canvas.layer;

import chalmers.pimp.model.color.IReadOnlyColor;
import chalmers.pimp.model.pixeldata.PixelData;

/**
 * The {@code LayerFactory} class is a factory for creating instances of the {@code ILayer}
 * interface.
 */
public final class LayerFactory {

  private LayerFactory() {
  }

  /**
   * Creates and returns a layer that holds basic raster data.
   *
   * @param width  the width of the raster.
   * @param height the height of the raster.
   * @return a layer that holds basic raster data.
   * @throws IndexOutOfBoundsException if the supplied dimensions aren't greater than zero.
   */
  public static ILayer createRasterLayer(int width, int height) {
    return new Raster(width, height);
  }

  /**
   * Creates and returns a raster layer that holds basic raster data. The created layer is based on
   * the supplied pixel data.
   *
   * @param pixelData the pixel data which holds the data that will be copied.
   * @return a layer that holds raster data, copied from the supplied pixel data instance.
   * @throws NullPointerException if the supplied pixel data is {@code null}.
   */
  public static ILayer createRasterLayer(PixelData pixelData) {
    return new Raster(pixelData);
  }

  /**
   * Creates and returns a layer that is a rectangle.
   *
   * @param x      the zero-indexed x coordinate of the rectangle.
   * @param y      the zero-indexed y coordinate of the rectangle.
   * @param width  the width of the rectangle.
   * @param height the height of the rectangle.
   * @return a rectangle layer.
   */
  public static ILayer createRectangle(int x, int y, int width, int height) {
    return new Rectangle(x, y, width, height);
  }

  /**
   * Creates and returns a layer that is a doodle.
   *
   * @param width the width of the line strokes.
   * @param color the color of the line strokes.
   * @return a doodle layer.
   */
  public static ILayer createDoodle(int width, IReadOnlyColor color) {
    return new Doodle(width, color);
  }
}