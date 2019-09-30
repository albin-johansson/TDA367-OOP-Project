package chalmers.pimp.model.tools;

import chalmers.pimp.model.pixeldata.PixelFactory;
import chalmers.pimp.model.IModel;
import chalmers.pimp.model.pixeldata.PixelData;
import java.awt.Color;
import chalmers.pimp.model.MouseStatus;

/**
 * A pencil is a tool that updates a layers pixels.
 */
public final class Pencil implements ITool {

  /**
   * The diameter of the pencil.
   */
  private int width;
  private Color color;
  private final IModel model;

  /**
   * Creates a pencil with the specified width and color.
   *
   * @param width the diameter of the pencil.
   * @param color the color of the pencils strokes.
   * @param model a reference to the model.
   */
  Pencil(int width, Color color, IModel model) {
    this.width = width;
    this.color = color;
    this.model = model;
  }

  /**
   * Returns the width of the pencil.
   *
   * @return the width of the pencil.
   */
  public float getWidth() {
    return width;
  }

  /**
   * Sets the width of the pencil.
   *
   * @param width the width of the pencil to be set.
   */
  public void setWidth(int width) {
    this.width = width;
  }

  /**
   * Returns the color of the pencil.
   *
   * @return the color of the pencil.
   */
  public Color getColor() {
    return color;
  }

  /**
   * Sets the color of the pencil.
   *
   * @param color the color of the pencil to be set.
   */
  public void setColor(Color color) {
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

  /**
   * Updates the {@code targetLayer}'s pixels in a square of the pencils width and color. TODO: Add
   * some kind pattern for the pencil to draw.
   *
   * @param x the zero-indexed x coordinate for the squares center.
   * @param y the zero-indexed y coordinate for the squares center.
   */
  private void updateTargetsPixels(int x, int y) {

    PixelData pixels = new PixelData(width, width);

    int radius = (int) (width / 2.0);
    for (int row = 0; row < width; row++) {
      for (int col = 0; col < width; col++) {
        pixels.setPixel(PixelFactory
            .createPixel(col - radius + x, row - radius + y, color.getRed() / 255.0,
                color.getGreen() / 255.0, color.getBlue() / 255.0));
      }
    }

    model.setPixels(x, y, pixels);
  }
}
