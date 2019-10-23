package chalmers.pimp.model.pixeldata;

import chalmers.pimp.model.color.IColor;

/**
 * The {@code RasterDataFactory} class is a factory for creating instances of the {@code
 * IRasterData} interface.
 *
 * @see IRasterData
 */
public final class RasterDataFactory {

  private RasterDataFactory() {
  }

  /**
   * Creates and returns a raster data instance.
   *
   * @param width  the width of the raster data instance.
   * @param height the height of the raster data instance.
   * @return a raster data instance.
   * @throws IndexOutOfBoundsException if width or height isn't greater than 1.
   */
  public static IRasterData createRasterData(int width, int height) {
    return new RasterDataImpl(width, height);
  }

  /**
   * Creates and returns a raster data instance.
   *
   * @param width  the width of the raster data instance.
   * @param height the height of the raster data instance.
   * @return a raster data instance.
   * @throws NullPointerException      if the provided color is {@code null}.
   * @throws IndexOutOfBoundsException if width or height isn't greater than 1.
   */
  public static IRasterData createRasterData(int width, int height, IColor color) {
    return new RasterDataImpl(width, height, color);
  }

  /**
   * Creates and returns a raster data instance that is a copy of the supplied raster data.
   *
   * @param rasterData the raster data that will be copied.
   * @return a raster data instance that is a copy of the supplied raster data.
   * @throws NullPointerException if the supplied raster data is {@code null}.
   */
  public static IRasterData createRasterData(IRasterData rasterData) {
    return new RasterDataImpl(rasterData);
  }
}
