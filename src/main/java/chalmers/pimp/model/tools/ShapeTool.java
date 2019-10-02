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
}
