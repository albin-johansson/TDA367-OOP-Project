package chalmers.pimp.model.tools;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.MouseStatus;
import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.LayerFactory;
import java.util.Objects;

/**
 * A tool for creating and editing shapes.
 */
public final class ShapeTool implements ITool {

  private int x;
  private int y;
  private IModel model;

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
  }

  @Override
  public void pressed(MouseStatus mouseStatus) {
    x = mouseStatus.getX();
    y = mouseStatus.getY();
  }

  @Override
  public void released(MouseStatus mouseStatus) {
    int mouseX = mouseStatus.getX();
    int mouseY = mouseStatus.getY();

    int originX = Math.min(x, mouseX);
    int originY = Math.min(y, mouseY);
    int maxX = Math.max(x, mouseX);
    int maxY = Math.max(y, mouseY);

    int width = maxX - originX;
    int height = maxY - originY;

    ILayer layer = LayerFactory.createRectangle(originX, originY, width, height);
    model.addLayer(layer);
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
    return new Point(originX, originY);
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
    return new Point(maxX, maxY);
  }
}
