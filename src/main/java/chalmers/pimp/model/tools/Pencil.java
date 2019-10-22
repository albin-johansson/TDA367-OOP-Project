package chalmers.pimp.model.tools;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.MouseStatus;
import chalmers.pimp.model.Point;
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

  private Point getRotatedPoint(MouseStatus mouseStatus) {

    //Rotate the point around center point with the layers negative angle
    double rotation = model.getActiveLayer().getRotation();
    double s = Math.sin(rotation);
    double c = Math.cos(rotation);

    //Translate point to origin.
    Point layersCenter = model.getActiveLayer().getRotationAnchor();
    int xToRotate = mouseStatus.getX() - layersCenter.getX();
    int yToRotate = mouseStatus.getY() - layersCenter.getY();

    //Rotate point.
    int xNew = (int) (xToRotate * c - yToRotate * s);
    int yNew = (int) (xToRotate * s + yToRotate * c);

    //Translate point back.
    int translatedX = xNew + layersCenter.getX();
    int translatedY = yNew + layersCenter.getY();

    return new Point(translatedX, translatedY);
  }

  @Override
  public void dragged(MouseStatus mouseStatus) {
    if (model.getActiveLayer() != null) {
      Point tempPoint = getRotatedPoint(mouseStatus);
      model.updateStroke(PixelFactory.createPixel(tempPoint.getX(), tempPoint.getY(), color));
    }
  }

  @Override
  public void pressed(MouseStatus mouseStatus) {
    if (model.getActiveLayer() != null) {
      Point tempPoint = getRotatedPoint(mouseStatus);
      IPixel pixel = PixelFactory.createPixel(tempPoint.getX(), tempPoint.getY(), color);
      model.startStroke(pixel, diameter);
    }
  }

  @Override
  public void released(MouseStatus mouseStatus) {
    if (model.getActiveLayer() != null) {
      Point tempPoint = getRotatedPoint(mouseStatus);
      IPixel pixel = PixelFactory.createPixel(tempPoint.getX(), tempPoint.getY(), color);
      model.endStroke(pixel);
    }
  }

  // TODO: Add some kind pattern for the pencil to draw.
}