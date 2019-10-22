package chalmers.pimp.model.canvas.layer;

import chalmers.pimp.model.color.Colors;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.pixeldata.IRasterData;

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
   * the supplied raster data.
   *
   * @param rasterData the raster data which holds the data that will be copied.
   * @return a layer that holds raster data, copied from the supplied raster data instance.
   * @throws NullPointerException if the supplied raster data is {@code null}.
   */
  public static ILayer createRasterLayer(IRasterData rasterData) {
    return new Raster(rasterData);
  }

  /**
   * Creates and returns a raster layer that holds basic raster data. The created layer is based on
   * the supplied pixel data and a supplied name.
   *
   * @param rasterData the raster data which holds the data that will be copied.
   * @param name       the name of the raster data (usually a file name) that will be copied.
   * @return a layer that holds raster data, copied from the supplied raster data instance.
   * @throws NullPointerException if any references are {@code null}.
   */
  public static ILayer createRasterLayer(IRasterData rasterData, String name) {
    return new Raster(rasterData, name);
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
    return new Rectangle(x, y, width, height, Colors.WHITE);
  }

  /**
   * Creates and returns a layer that is a rectangle.
   *
   * @param x      the zero-indexed x coordinate of the rectangle.
   * @param y      the zero-indexed y coordinate of the rectangle.
   * @param width  the width of the rectangle.
   * @param height the height of the rectangle.
   * @param color  the color of the rectangle.
   * @return a rectangle layer.
   */
  public static ILayer createRectangle(int x, int y, int width, int height, IColor color) {
    return new Rectangle(x, y, width, height, color);
  }

  /**
   * Creates and returns a layer that is a doodle.
   *
   * @param lineWidth the width of the line strokes.
   * @param color     the color of the line strokes.
   * @return a doodle layer.
   */
  public static IDoodleLayer createDoodle(int lineWidth, IColor color) {
    return new Doodle(lineWidth, color);
  }
}