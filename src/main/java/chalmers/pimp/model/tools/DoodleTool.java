package chalmers.pimp.model.tools;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.MouseStatus;
import chalmers.pimp.model.Point;
import chalmers.pimp.model.canvas.layer.IDoodleLayer;
import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.LayerFactory;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.pixeldata.PixelFactory;
import java.util.Objects;

/**
 * A tool which the user can deaw vectorised lines with
 */
final class DoodleTool implements ITool {

  private final IModel model;
  private final IColor color;
  private final int lineWidth;
  private int x, y;
  private IDoodleLayer doodle;

  /**
   * Creates a doodle tool
   *
   * @param lineWidth the width of the doodle.
   * @param color    the color of the doodle.
   * @param model    reference back to the model.
   */
  DoodleTool(int lineWidth, IColor color, IModel model) {
    this.lineWidth = lineWidth;
    this.color = Objects.requireNonNull(color);
    this.model = Objects.requireNonNull(model);
  }

  @Override
  public void pressed(MouseStatus mouseStatus) {
    doodle = LayerFactory.createDoodle(lineWidth, color);
    dragged(mouseStatus);
  }

  @Override
  public void dragged(MouseStatus mouseStatus) {
    model.notifyCanvasUpdateListeners();
    doodle.addPoint(new Point(mouseStatus.getX(), mouseStatus.getY()));
    doodle.draw(model.getRenderer());
  }

  @Override
  public void released(MouseStatus mouseStatus) {
    model.addLayer(doodle);
    model.selectLayer(doodle.getDepthIndex());
  }
}
