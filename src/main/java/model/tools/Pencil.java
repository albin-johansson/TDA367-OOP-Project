package model.tools;

import java.awt.Color;
import javafx.scene.input.MouseEvent;
import model.canvas.layer.ILayer;

/**
 * A pencil is a tool that paints a layer that is a raster (a grid of pixels).
 */
public class Pencil implements ITool {

  /**
   * The diameter of pencil.
   */
  private int width;
  private Color color;

  public Pencil() {
    new Pencil(10, Color.BLACK);
  }

  /**
   * Creates a pencil with the specified width and color.
   */
  public Pencil(int width, Color color) {
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
  }

  @Override
  public void pressed(MouseEvent mouseEvent) {
  }

  @Override
  public void released(MouseEvent mouseEvent) {
  }

  @Override
  public void setTarget(ILayer iLayer) {

  }
}
