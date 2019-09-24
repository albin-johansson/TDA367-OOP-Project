package model.tools;

import java.awt.Color;
import javafx.scene.input.MouseEvent;
import model.canvas.layer.ILayer;

/**
 * A pencil is a tool that paints a layer that is a raster (a grid of pixels).
 */
public final class Pencil implements ITool {

  /**
   * The diameter of the pencil.
   */
  private int width;
  private Color color;
  private ILayer targetLayer;

  /**
   * Creates a pencil with the specified width and color.
   */
  Pencil(int width, Color color) {
    this.width = width;
    this.color = color;
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
  public void dragged(MouseEvent mouseEvent) {
    draw((int) mouseEvent.getX(), (int) mouseEvent.getY());
  }

  @Override
  public void pressed(MouseEvent mouseEvent) {
    draw((int) mouseEvent.getX(), (int) mouseEvent.getY());
  }

  @Override
  public void released(MouseEvent mouseEvent) {
  }

  @Override
  public void setTarget(ILayer iLayer) {
    targetLayer = iLayer;
  }

  /**
   * Draws a square of pixels in the pencils color starting at the first coordinate given and
   * continuing down right. TODO: Implement som kind of "pattern" the pencil will draw.
   *
   * @param x the zero-indexed x coordinate for the square to be drawn.
   * @param y the zero-indexed y coordinate for the square to be drawn.
   */
  private void draw(int x, int y) {
    if (targetLayer == null) {
      return;
    }

    for (int row = 0; row < width; row++) {
      for (int col = 0; col < width; col++) {
        targetLayer.setPixel(col + x, row + y, color);
      }
    }
  }
}
