package chalmers.pimp.model.canvas.layer;

/**
 * The {@code LayerType} enum provides values that represents the different kinds of layers.
 */
public enum LayerType {
  /**
   * The value that represents raster layers. A raster layer is a "dumb" pixel-based layer.
   */
  RASTER,

  /**
   * The value that represents text layers. A text layer simply consists of text.
   */
  TEXT,

  /**
   * The value that represents shape layers. Shape layers aren't pixel-based, instead they use
   * formulas for their representation. Shape layers are considered "smarter" than raster layers.
   */
  SHAPE
}