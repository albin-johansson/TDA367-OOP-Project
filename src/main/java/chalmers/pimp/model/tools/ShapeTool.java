package chalmers.pimp.model.tools;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.MouseStatus;
import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.LayerFactory;
import chalmers.pimp.model.point.IReadOnlyPoint;
import chalmers.pimp.model.point.Point;
import chalmers.pimp.model.point.PointFactory;
import java.util.Objects;
import javafx.scene.input.MouseEvent;

/**
 * A tool for creating and editing shapes.
 */
final class ShapeTool implements ITool {

  private int x;
  private int y;
  private IModel model;
  private ILayer currentShape;

  /**
   * Creates and returns a shape tool.
   *
   * @param model a reference to the model.
   * @throws NullPointerException if the provided layer is null.
   */
  ShapeTool(IModel model) {
    this.model = Objects.requireNonNull(model);
  }

  @Override
  public void dragged(MouseStatus mouseStatus) {
    model.notifyAllCanvasUpdateListeners();
    ILayer newShape = createRect(mouseStatus);
    newShape.draw(model.getRenderer());
  }

  @Override
  public void pressed(MouseStatus mouseStatus) {
    x = mouseStatus.getX();
    y = mouseStatus.getY();
  }

  @Override
  public void released(MouseStatus mouseStatus) {
    ILayer newShape = createRect(mouseStatus);
    model.addLayer(newShape);
    model.selectLayer(newShape.getDepthIndex());
  }

  /**
   * Returns the origin point of the rectangle based on the first point the rectangle was created at
   * and the current position of the mouse. The closest one to the origin will be classified as the
   * origin point.
   *
   * @param mouseStatus information about the mouse.
   * @return the new origin point of the rectangle.
   */
  private IReadOnlyPoint getOriginPoint(MouseStatus mouseStatus) {
    int originX = Math.min(x, mouseStatus.getX());
    int originY = Math.min(y, mouseStatus.getY());
    return PointFactory.createPoint(originX, originY);
  }

  /**
   * Returns the max point of the rectangle based on the first point the rectangle was created at
   * and the current position of the mouse. The point furthest away from the origin will be
   * classified as the max point.
   *
   * @param mouseStatus information about the mouse.
   * @return the new origin point of the rectangle.
   */
  private IReadOnlyPoint getMaxPoint(MouseStatus mouseStatus) {
    int maxX = Math.max(x, mouseStatus.getX());
    int maxY = Math.max(y, mouseStatus.getY());
    return PointFactory.createPoint(maxX, maxY);
  }

  /**
   * Creates and returns a rectangle based on the origin point of the rectangle and the current
   * position of the mouse.
   *
   * @param mouseStatus the current mouse status.
   * @return a rectangle.
   */
  private ILayer createRect(MouseStatus mouseStatus) {
    IReadOnlyPoint originPoint = getOriginPoint(mouseStatus);
    IReadOnlyPoint maxPoint = getMaxPoint(mouseStatus);

    int width = maxPoint.getX() - originPoint.getX();
    int height = maxPoint.getY() - originPoint.getY();

    return LayerFactory.createRectangle(originPoint.getX(), originPoint.getY(), width, height);
  }
}
