package chalmers.pimp.model.tools;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.MouseStatus;
import chalmers.pimp.model.Point;
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
   * @throws IllegalArgumentException if the supplied diameter is less than 1.
   */
  RasterPen(int diameter, IModel model) {
    if(diameter < 1){
      throw new IllegalArgumentException("Diameter can't be less than 1!");
    }
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
   * Rotates the given point opposite to the current active layer's rotation. This since the layer
   * is never actually rotated in the model and thus the input mouse point will have to rotate to
   * compensate. First the point is translated to origin, rotated, and then translated back. Note:
   * Slight discrepancy after rounding and the limited amount of pixels used.
   *
   * @param point the point to be drawn in relation to the current active layer's center point and
   *              rotation.
   */
  private Point getRotatedPoint(Point point) {

    //Rotate the point around center point with the layers negative angle
    double rotation = Math.toRadians(-model.getActiveLayer().getRotation());
    double s = Math.sin(rotation);
    double c = Math.cos(rotation);

    //Translate point to origin.
    Point layersCenter = model.getActiveLayer().getCenterPoint();
    int xToRotate = point.getX() - layersCenter.getX();
    int yToRotate = point.getY() - layersCenter.getY();

    //Rotate point.
    int xNew = (int) (xToRotate * c - yToRotate * s);
    int yNew = (int) (xToRotate * s + yToRotate * c);

    //Translate point back.
    int translatedX = xNew + layersCenter.getX();
    int translatedY = yNew + layersCenter.getY();

    return new Point(translatedX, translatedY);
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
    Point tempPoint = new Point(x, y);
    tempPoint = getRotatedPoint(tempPoint);
    return PixelFactory.createPixel(tempPoint.getX(), tempPoint.getY(), getColor());
  }

  @Override
  public void dragged(MouseStatus mouseStatus) {
    if (model.hasActiveLayer()) {
      model.updateStroke(createPixel(mouseStatus));
    }
  }

  @Override
  public void pressed(MouseStatus mouseStatus) {
    if (model.hasActiveLayer()) {
      model.startStroke(createPixel(mouseStatus), diameter, getColor());
    }
  }

  @Override
  public void released(MouseStatus mouseStatus) {
    if (model.hasActiveLayer()) {
      model.endStroke(createPixel(mouseStatus));
    }
  }
}