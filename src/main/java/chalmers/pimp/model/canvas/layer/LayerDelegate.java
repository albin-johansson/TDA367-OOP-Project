package chalmers.pimp.model.canvas.layer;

import java.util.Objects;

/**
 * The {@code LayerDelegate} class is designed to be used by implementations of the {@code ILayer}
 * interface. The implementations should use instances of this class to delegate common layer
 * requests.
 *
 * @see ILayer
 */
final class LayerDelegate {

  /**
   * The default value for the visibility property.
   */
  static final boolean DEFAULT_VISIBILITY_VALUE = true;

  private final LayerType layerType;
  private String name;
  private boolean isVisible;
  private int x;
  private int y;
  private int depthIndex;
  private double rotation;
  private double alpha;

  /**
   * @param layerType the layer type that will be used by the layer delegate.
   * @throws NullPointerException if any references are {@code null}.
   */
  LayerDelegate(LayerType layerType) {
    this.layerType = Objects.requireNonNull(layerType);

    name = "";
    isVisible = DEFAULT_VISIBILITY_VALUE;
    x = 0;
    y = 0;
    depthIndex = 0;
  }

  /**
   * Creates a copy of the supplied layer delegate.
   *
   * @throws NullPointerException if the supplied layer delegate is {@code null}.
   */
  LayerDelegate(LayerDelegate layerDelegate) {
    Objects.requireNonNull(layerDelegate);

    layerType = layerDelegate.layerType;
    x = layerDelegate.x;
    y = layerDelegate.y;
    depthIndex = layerDelegate.depthIndex;
    name = layerDelegate.name;
    isVisible = layerDelegate.isVisible;
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
   * Offsets the x- and y-coordinates.
   *
   * @param dx the x-axis offset, may be negative.
   * @param dy the y-axis offset, may be negative.
   */
  void move(int dx, int dy) {
    x += dx;
    y += dy;
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

  /**
   * Returns the layer type associated with this layer delegate.
   *
   * @return the layer type associated with this layer delegate.
   */
  LayerType getLayerType() {
    return layerType;
  }

  /**
   * Returns the depth index for this layer.
   *
   * @return the depth index.
   */
  int getDepthIndex() {
    return depthIndex;
  }

  /**
   * Sets the depth index for this layer.
   *
   * @param depthIndex the new depth index.
   */
  void setDepthIndex(int depthIndex) {
    this.depthIndex = depthIndex;
  }

  /**
   * Sets the rotation for this layer.
   *
   * @param rotation the new rotation.
   */
  void setRotation(double rotation) {
    this.rotation = rotation;
  }

  /**
   * Returns the rotation value for this layer.
   *
   * @return the rotation value.
   */
  double getRotation() {
    return rotation;
  }

  /**
   * Returns the alpha value for this layer.
   *
   * @return the alpha value.
   */

  public double getAlpha() {
    return alpha;
  }

  /**
   * Sets the alpha value for this layer.
   *
   * @param alpha the new alpha.
   */
  public void setAlpha(double alpha) {
    this.alpha = alpha;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, name, depthIndex, isVisible);
  }

  /**
   * Indicates whether the supplied object is "equal" to this layer delegate.
   *
   * The supplied object is considered equal to this layer delegate if:
   * <ul>
   *   <li>the supplied object is an instance of the {@code LayerDelegate} class</li>
   *   <li>the layer delegates have the same layer type value.</li>
   *   <li>the layer delegates have the same x-coordinates.</li>
   *   <li>the layer delegates have the same y-coordinates.</li>
   *   <li>the layer delegates have the same depth index.</li>
   * </ul>
   *
   * @param object the object that will be compared to this object.
   * @return {@code true} if the supplied object is considered equal to this layer delegate; {@code
   * false} otherwise.
   */
  @Override
  public boolean equals(Object object) {
    if (!(object instanceof LayerDelegate)) {
      return false;
    }
    if (object == this) {
      return true;
    }

    var layerDelegate = (LayerDelegate) object;

    return (layerType == layerDelegate.layerType)
        && (x == layerDelegate.x)
        && (y == layerDelegate.y)
        && (depthIndex == layerDelegate.depthIndex);
  }
}