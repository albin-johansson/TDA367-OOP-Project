package model.canvas.layer;

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
   * @return a layer that holds basic raster data.
   */
  public static ILayer createRasterLayer() {
    return new Raster();
  }
}