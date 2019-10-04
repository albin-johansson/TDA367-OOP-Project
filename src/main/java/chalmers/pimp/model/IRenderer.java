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
   * Draws an ellipse.
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
   */
  void drawImage(IReadOnlyPixelData readOnlyPixelData, int x, int y, int width, int height);

  /**
   * Draws a text.
   *
   * @param content  the string to write on the screen.
   * @param x        the zero-indexed x coordinate.
   * @param y        the zero-indexed y coordinate.
   * @param fontSize the size of the font in pixels.
   */
  void drawText(String content, int x, int y, int fontSize);

  /**
   * Draws a line from point (x1, y1) to point (x2, y2)
   *
   * @param x1 first point x-coordinate
   * @param y1 first point y-coordinate
   * @param x2 second point x-coordinate
   * @param y2 second point y-coordinate
   */
  void drawLine(int x1, int y1, int x2, int y2);

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

  /**
   * Returns the width of the rendering target.
   *
   * @return the width of the rendering target.
   */
  int getCanvasWidth();

  /**
   * Returns the height of the rendering target.
   *
   * @return the height of the rendering target.
   */
  int getCanvasHeight();
}
