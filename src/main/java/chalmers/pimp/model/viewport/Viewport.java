package chalmers.pimp.model.viewport;

import java.util.Objects;

/**
 * The {@code Viewport} class is an implementation of the {@code IReadOnlyViewport} interface that
 * also provides package-private setters.
 *
 * @see IReadOnlyViewport
 */
final class Viewport implements IReadOnlyViewport {

  private static final int DEFAULT_WIDTH = 100;
  private static final int DEFAULT_HEIGHT = 100;

  private int x;
  private int y;
  private int width;
  private int height;

  /**
   * Creates a viewport with the dimensions {@value Viewport#DEFAULT_WIDTH}x{@value
   * Viewport#DEFAULT_HEIGHT}.
   */
  Viewport() {
    x = 0;
    y = 0;
    width = DEFAULT_WIDTH;
    height = DEFAULT_HEIGHT;
  }

  /**
   * Creates a copy of the supplied viewport.
   *
   * @param viewport the viewport that will be copied.
   * @throws NullPointerException if the supplied viewport is {@code null}.
   */
  Viewport(Viewport viewport) {
    Objects.requireNonNull(viewport);
    x = viewport.x;
    y = viewport.y;
    width = viewport.width;
    height = viewport.height;
  }

  /**
   * Centers the viewport over the area with the supplied dimensions.
   *
   * @param areaWidth  the width of the area.
   * @param areaHeight the height of the area.
   * @throws IllegalArgumentException if either of the supplied dimensions aren't greater than
   *                                  zero.
   */
  void center(int areaWidth, int areaHeight) {
    if ((areaWidth < 1) || (areaHeight < 1)) {
      String msg = "Bad area dimensions: (" + areaWidth + "x" + areaHeight + ")";
      throw new IllegalArgumentException(msg);
    }

    int x = -(areaWidth - width) / 2;
    int y = -(areaHeight - height) / 2;
    this.x = x;
    this.y = y;
  }

  /**
   * Moves the viewport with the supplied offsets.
   *
   * @param dx the x-axis movement, may be negative.
   * @param dy the y-axis movement, may be negative.
   */
  void move(int dx, int dy) {
    x += dx;
    y += dy;
  }

  /**
   * Sets the x-coordinate of the viewport.
   *
   * @param x the new x-coordinate of the viewport.
   */
  void setX(int x) {
    this.x = x;
  }

  /**
   * Sets the y-coordinate of the viewport.
   *
   * @param y the new y-coordinate of the viewport.
   */
  void setY(int y) {
    this.y = y;
  }

  /**
   * Sets the width of the viewport.
   *
   * @param width the new width of the viewport, must be greater than zero.
   * @throws IllegalArgumentException if the supplied width isn't greater than zero.
   */
  void setWidth(int width) {
    if (width < 1) {
      throw new IllegalArgumentException("Invalid viewport width: " + width);
    }
    this.width = width;
  }

  /**
   * Sets the height of the viewport.
   *
   * @param height the new height of the viewport, must be greater than zero.
   * @throws IllegalArgumentException if the supplied height isn't greater than zero.
   */
  void setHeight(int height) {
    if (height < 1) {
      throw new IllegalArgumentException("Invalid viewport height: " + height);
    }
    this.height = height;
  }

  @Override
  public int getX() {
    return x;
  }

  @Override
  public int getY() {
    return y;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getTranslatedX(int x) {
    return this.x + x;
  }

  @Override
  public int getTranslatedY(int y) {
    return this.y + y;
  }
}