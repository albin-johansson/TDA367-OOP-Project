package chalmers.pimp.model.tools;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.MouseStatus;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.PixelFactory;
import java.util.Objects;

/**
 * The {@code RasterPen} class represents a "pen" that modifies a raster layer.
 *
 * @see ITool
 */
final class RasterPen implements ITool {

  private final int diameter;
  private final IModel model;
  private IColor customColor;

  /**
   * @param diameter the diameter of the pencil.
   * @param model    the associated model instance.
   * @throws NullPointerException if any references are {@code null}.
   */
  RasterPen(int diameter, IModel model) {
    this.diameter = diameter;
    this.model = Objects.requireNonNull(model);
  }

  /**
   * @param diameter    the diameter of the pencil.
   * @param model       the associated model instance.
   * @param customColor the custom color that will be used.
   * @throws NullPointerException if any references are {@code null}.
   */
  RasterPen(int diameter, IModel model, IColor customColor) {
    this(diameter, model);
    this.customColor = Objects.requireNonNull(customColor);
  }

  /**
   * Returns the color that should be used.
   *
   * @return the color that should be used.
   */
  private IColor getColor() {
    return (customColor == null) ? model.getSelectedColor() : customColor;
  }

  /**
   * Creates a pixel based on the supplied mouse status.
   *
   * @param mouseStatus the mouse status that will be used.
   * @return a pixel based on the supplied mouse status.
   * @throws NullPointerException if the supplied mouse status is {@code null}.
   */
  private IPixel createPixel(MouseStatus mouseStatus) {
    Objects.requireNonNull(mouseStatus);
    int x = model.getViewport().getTranslatedX(mouseStatus.getX());
    int y = model.getViewport().getTranslatedY(mouseStatus.getY());
    return PixelFactory.createPixel(x, y, getColor());
  }

  @Override
  public void dragged(MouseStatus mouseStatus) {
    if (model.getActiveLayer() != null) {
      model.updateStroke(createPixel(mouseStatus));
    }
  }

  @Override
  public void pressed(MouseStatus mouseStatus) {
    if (model.getActiveLayer() != null) {
      model.startStroke(createPixel(mouseStatus), diameter, getColor());
    }
  }

  @Override
  public void released(MouseStatus mouseStatus) {
    if (model.getActiveLayer() != null) {
      model.endStroke(createPixel(mouseStatus));
    }
  }
}