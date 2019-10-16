package chalmers.pimp.model.viewport;

import chalmers.pimp.model.IMementoTarget;

/**
 * The {@code IViewportModel} interface specifies the "viewport model", which is responsible for
 * dealing with viewports. A viewport is the area that the user can see on their screen. This
 * interface extends the {@code IMementoTarget} interface.
 *
 * @see IMementoTarget
 * @see ViewportModelMemento
 * @see IReadOnlyViewport
 */
public interface IViewportModel extends IMementoTarget<ViewportModelMemento> {

  /**
   * Centers the viewport over the are with the supplied dimensions.
   *
   * @param areaWidth  the width of the area.
   * @param areaHeight the height of the area.
   * @throws IllegalArgumentException if either of the supplied dimensions aren't greater than
   *                                  zero.
   */
  void center(int areaWidth, int areaHeight);

  /**
   * Moves the viewport.
   *
   * @param dx the x-axis offset, may be negative.
   * @param dy the y-axis offset, may be negative.
   */
  void moveViewport(int dx, int dy);

  /**
   * Sets the x-coordinate of the model.
   *
   * @param x the x-coordinate of the model.
   */
  void setX(int x);

  /**
   * Sets the y-coordinate of the model.
   *
   * @param y the y-coordinate of the model.
   */
  void setY(int y);

  /**
   * Sets the width of the viewport.
   *
   * @param width the new width of the viewport.
   * @throws IllegalArgumentException if the supplied width isn't greater than zero.
   */
  void setWidth(int width);

  /**
   * Sets the height of the viewport.
   *
   * @param height the new height of the viewport.
   * @throws IllegalArgumentException if the supplied height isn't greater than zero.
   */
  void setHeight(int height);

  /**
   * Returns the x-coordinate of the viewport.
   *
   * @return the x-coordinate of the viewport.
   */
  int getX();

  /**
   * Returns the y-coordinate of the viewport.
   *
   * @return the y-coordinate of the viewport.
   */
  int getY();

  /**
   * Returns the width of the viewport.
   *
   * @return the width of the viewport.
   */
  int getWidth();

  /**
   * Returns the height of the viewport.
   *
   * @return the height of the viewport.
   */
  int getHeight();

  /**
   * Returns a copy of the current viewport.
   *
   * @return a copy of the current viewport.
   */
  IReadOnlyViewport getViewport();
}