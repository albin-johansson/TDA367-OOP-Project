package chalmers.pimp.model.tools;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.MouseStatus;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.pixeldata.PixelData;
import java.util.Objects;

/**
 * A pencil is a tool that updates a layers pixels.
 */
public final class Pencil implements ITool {

  private final IModel model;
  private IColor color;
  private int diameter;

  /**
   * Creates a pencil with the specified width and color.
   *
   * @param diameter the diameter of the pencil.
   * @param color    the color of the pencils strokes.
   * @param model    a reference to the model.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  Pencil(int diameter, IColor color, IModel model) {
    this.diameter = diameter;
    this.color = Objects.requireNonNull(color);
    this.model = Objects.requireNonNull(model);
  }

  /**
   * Returns the width of the pencil.
   *
   * @return the width of the pencil.
   */
  public float getDiameter() {
    return diameter;
  }

  /**
   * Sets the width of the pencil.
   *
   * @param diameter the width of the pencil to be set.
   */
  public void setDiameter(int diameter) {
    this.diameter = diameter;
  }

  /**
   * Returns the color of the pencil.
   *
   * @return the color of the pencil.
   */
  public IColor getColor() {
    return color;
  }

  /**
   * Sets the color of the pencil.
   *
   * @param color the color of the pencil to be set.
   */
  public void setColor(IColor color) {
    this.color = color;
  }

  @Override
  public void dragged(MouseStatus mouseStatus) {
    updateTargetsPixels(mouseStatus.getX(), mouseStatus.getY());
  }

  @Override
  public void pressed(MouseStatus mouseStatus) {
    updateTargetsPixels(mouseStatus.getX(), mouseStatus.getY());
  }

  @Override
  public void released(MouseStatus mouseStatus) {
  }

  // TODO: Add some kind pattern for the pencil to draw.

  /**
   * Updates the {@code targetLayer}'s pixels in a square of the pencils width and color.
   *
   * @param x the zero-indexed x coordinate for the squares center.
   * @param y the zero-indexed y coordinate for the squares center.
   */
  private void updateTargetsPixels(int x, int y) {

    PixelData pixels = new PixelData(diameter, diameter);

    int radius = (int) (diameter / 2.0);
    for (int row = 0; row < diameter; row++) {
      for (int col = 0; col < diameter; col++) {
        pixels.setPixel(col, row, color);
      }
    }

    model.setPixels(x - radius, y - radius, pixels);
  }
}
  