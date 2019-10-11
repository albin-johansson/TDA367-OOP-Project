package chalmers.pimp.model.tools;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.MouseStatus;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.PixelFactory;
import java.util.Objects;

/**
 * A pencil is a tool that updates a layers pixels.
 */
final class Pencil implements ITool {

  private final IModel model;
  private final IColor color;
  private final int diameter;

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

  @Override
  public void dragged(MouseStatus mouseStatus) {
    if (model.getActiveLayer() != null) {
      model.updateStroke(PixelFactory.createPixel(mouseStatus.getX(), mouseStatus.getY(), color));
    }
  }

  @Override
  public void pressed(MouseStatus mouseStatus) {
    if (model.getActiveLayer() != null) {
      IPixel pixel = PixelFactory.createPixel(mouseStatus.getX(), mouseStatus.getY(), color);
      model.startStroke(pixel, diameter);
    }
  }

  @Override
  public void released(MouseStatus mouseStatus) {
    if (model.getActiveLayer() != null) {
      model.endStroke(PixelFactory.createPixel(mouseStatus.getX(), mouseStatus.getY(), color));
    }
  }

  // TODO: Add some kind pattern for the pencil to draw.
}