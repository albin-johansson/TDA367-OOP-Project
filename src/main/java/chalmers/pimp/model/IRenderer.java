package chalmers.pimp.model;

import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;

/**
 * The {@code IRenderer} interface specifies the render methods the view should be able to render.
 */
public interface IRenderer {

  /**
   * Clears the rendering target with the currently selected fill color.
   */
  void clear();

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
   * Draws an image. This method has no effect if startPoint is {@code Null}.
   *
   * @param readOnlyPixelData the image to draw.
   * @param x                 the zero-indexed x coordinate of the image.
   * @param y                 the zero-indexed y coordinate of the image.
   */
  void drawImage(IReadOnlyPixelData readOnlyPixelData, int x, int y);

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
   * Draws a line from point p1 to point p2. This method has no effect if startPoint is {@code
   * Null}.
   *
   * @param p1 first point
   * @param p2 second point
   */
  void drawLine(Point p1, Point p2);

  /**
   * Creates and starts a new rotation transform. Will also save the previous transform. This method
   * has no effect if the supplied point is {@code Null}.
   *
   * @param rotation    the rotation to use (degrees).
   * @param centerPoint a point that represents the center of the content that will be rotated.
   */
  void startTransform(double rotation, Point centerPoint);

  /**
   * Restores the transform to the previous saved state, thus ending the transform from affecting
   * more objects.
   */
  void endTransform();

  /**
   * Sets the transparency alpha value. 1 is no transparency, 0 is completely transparent.
   *
   * @param alpha the new alpha value [0, 1].
   */
  void setGlobalAlpha(double alpha);

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
   * Sets the width of the line which will be drawn
   *
   * @param width the width of the line
   */
  void setLineWidth(int width);

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
