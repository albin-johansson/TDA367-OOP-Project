package chalmers.pimp.model.canvas.layer;

/**
 * The {@code LayerDelegate} class is designed to be used by implementations of the {@code ILayer}
 * interface. The implementations should use instances of this class to delegate requests related to
 * position and visibility.
 *
 * @see ILayer
 */
final class LayerDelegate {

  /**
   * The default value for the visibility property.
   */
  static final boolean DEFAULT_VISIBILITY_VALUE = true;
  static int layerIndex = 1;
  private boolean isVisible;
  private int x;
  private int y;
  private String name;

  LayerDelegate() {
    isVisible = DEFAULT_VISIBILITY_VALUE;
    x = 0;
    y = 0;
    name = "Layer: " + layerIndex++;
  }

  /**
   * Sets the x-coordinate of the layer.
   *
   * @param x the new x-coordinate of the layer.
   */
  void setX(int x) {
    this.x = x;
  }

  /**
   * Sets the y-coordinate of the layer.
   *
   * @param y the new y-coordinate of the layer.
   */
  void setY(int y) {
    this.y = y;
  }

  /**
   * Sets the name of the layer.
   *
   * @param name the new name of the layer.
   */
  void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the value of the visibility property.
   *
   * @param isVisible {@code true} if the layer should be marked as visible; {@code false}
   *                  otherwise.
   */
  void setVisible(boolean isVisible) {
    this.isVisible = isVisible;
  }

  /**
   * Indicates whether or not the layer is visible. By default, this property is set to {@value
   * LayerDelegate#DEFAULT_VISIBILITY_VALUE}.
   *
   * @return {@code true} if the layer is visible; {@code false} otherwise.
   */
  boolean isVisible() {
    return isVisible;
  }

  /**
   * Returns the x-coordinate of the layer. By default, this property is set to {@code 0}.
   *
   * @return the x-coordinate of the layer.
   */
  int getX() {
    return x;
  }

  /**
   * Returns the y-coordinate of the layer. By default, this property is set to {@code 0}.
   *
   * @return the y-coordinate of the layer.
   */
  int getY() {
    return y;
  }

  /**
   * Returns the name of the layer.
   *
   * @return the name of the layer as a {@code String}
   */
  String getName() {
    return name;
  }
}