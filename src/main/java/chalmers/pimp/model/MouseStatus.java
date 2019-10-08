package chalmers.pimp.model;

/**
 * The {@code MouseStatus} class is an immutable class used to store relevant information about a
 * mouse event.
 */
public final class MouseStatus {

  private final int x;
  private final int y;
  private final MouseButton button;

  /**
   * @param x      the x coordinate of the mouse
   * @param y      the y coordinate of the mouse
   * @param button the button pressed
   */
  public MouseStatus(int x, int y, MouseButton button) {
    this.x = x;
    this.y = y;
    this.button = button;
  }

  /**
   * returns the x-coordinate of the mouse
   *
   * @return the x-coordinate of the mouse
   */
  public int getX() {
    return x;
  }

  /**
   * returns the y-coordinate of the mouse
   *
   * @return the y-coordinate of the mouse
   */
  public int getY() {
    return y;
  }

  /**
   * returns the button that was pressed, if any.
   *
   * @return the button that was pressed, if any.
   */
  public MouseButton getButton() {
    return button;
  }

  public enum MouseButton{
    NONE,
    PRIMARY,
    MIDDLE,
    SECONDARY
  }
}
