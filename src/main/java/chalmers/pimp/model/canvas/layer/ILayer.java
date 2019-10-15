package chalmers.pimp.model.canvas.layer;

import chalmers.pimp.model.pixeldata.IPixel;

/**
 * The {@code ILayer} interface specifies objects that represent some sort of layer. This interface
 * is a subinterface to {@code IReadOnlyLayer}.
 *
 * @see IReadOnlyLayer
 */
public interface ILayer extends IReadOnlyLayer {

  /**
   * Sets the color of a pixel specified by a specific coordinate. The coordinates are based on the
   * provided pixels coordinates and the color on the provided pixels color.
   *
   * @param pixel the pixel to be set
   * @throws NullPointerException if any arguments are {@code null}.
   */
  void setPixel(IPixel pixel);

  /**
   * Sets the value of the visible property of this layer.
   *
   * @param isVisible {@code true} if the layer should be visible; {@code false} otherwise.
   */
  void setVisible(boolean isVisible);

  void move(int dx, int dy);

  /**
   * Sets the x-coordinate of the layer.
   *
   * @param x the new x-coordinate of the layer.
   */
  void setX(int x);

  /**
   * Sets the y-coordinate of the layer.
   *
   * @param y the new y-coordinate of the layer.
   */
  void setY(int y);

  /**
   * Sets the name of the layer.
   *
   * @param name the name of the layer.
   */
  void setName(String name);

  /**
   * Sets the depth index for this layer.
   *
   * @param depthIndex the new depth index.
   */
  void setDepthIndex(int depthIndex);

  /**
   * Sets the rotation for this layer.
   *
   * @param rotation the new rotation.
   */
  void setRotation(double rotation);

  /**
   * Sets the alpha value for this layer.
   *
   * @param alpha the new alpha value.
   */
  void setAlpha(double alpha);
}
