package chalmers.pimp.model;

import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;

/**
 * The {@code IRenderer} interface specifies the render methods the view should be able to render.
 */
public interface IRenderer {

  /**
   * Draws a rectangle outline.
   *
   * @param x      the zero-indexed x coordinate of the rectangle.
   * @param y      the zero-indexed y coordinate of the rectangle.
   * @param width  the width of the rectangle.
   * @param height the height of the rectangle.
   */
  void drawRect(int x, int y, int width, int height);

  /**
   * Draws a filled rectangle.
   *
   * @param x      the zero-indexed x coordinate of the rectangle.
   * @param y      the zero-indexed y coordinate of the rectangle.
   * @param width  the width of the rectangle.
   * @param height the height of the rectangle.
   */
  void fillRect(int x, int y, int width, int height);

  /**
   * Draws a ellipse.
   *
   * @param x       the zero-indexed x coordinate of the ellipse.
   * @param y       the zero-indexed y coordinate of the ellipse.
   * @param radiusX the x radius of the ellipse.
   * @param radiusY the y radius of the ellipse.
   */
  void drawEllipse(int x, int y, int radiusX, int radiusY);

  /**
   * Draws a filled ellipse.
   *
   * @param x       the zero-indexed x coordinate of the ellipse.
   * @param y       the zero-indexed y coordinate of the ellipse.
   * @param radiusX the x radius of the ellipse.
   * @param radiusY the y radius of the ellipse.
   */
  void fillEllipse(int x, int y, int radiusX, int radiusY);

  /**
   * Draws an image.
   *
   * @param readOnlyPixelData the image to draw.
   * @param x                 the zero-indexed x coordinate of the image.
   * @param y                 the zero-indexed y coordinate of the image.
   * @param width             the width of the image.
   * @param height            the height of the image.
   * @param rotation          the rotation of the image in degrees.
   */
  void drawImage(IReadOnlyPixelData readOnlyPixelData, int x, int y, int width, int height,
      double rotation);

  /**
   * Draws a text.
   *
   * @param content  the
   * @param fontSize the size of the font in pixels.
   * @param rotation the rotation of the text.
   * @param color    the color of the text.
   */
  void drawText(String content, int fontSize, double rotation, IColor color);

  /**
   * Sets the rotation.
   *
   * @param rotation the rotation in degrees.
   */
  void setRotation(int rotation);

  /**
   * Sets the fill color.
   *
   * @param color the color to be set.
   */
  void setFillColor(IColor color);

  /**
   * Sets the color of the border.
   *
   * @param color the color to be set.
   */
  void setBorderColor(IColor color);

  /**
   * Sets the border width.
   *
   * @param width the width of borders.
   */
  void setBorderWidth(int width);
}