package chalmers.pimp.model.tools;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.MouseStatus;
import chalmers.pimp.model.Point;
import chalmers.pimp.model.canvas.layer.IDoodleLayer;
import chalmers.pimp.model.canvas.layer.LayerFactory;
import java.util.Objects;

/**
 * The {@code DoodleTool} class is an implementation of the {@code ITool} interface that represents
 * that is used to create "doodles". A doodle is basically a collection of interconnected points.
 *
 * @see ITool
 */
final class DoodleTool implements ITool {

  private final IModel model;
  private final int lineWidth;
  private IDoodleLayer doodle;

  /**
   * @param lineWidth the line width of the doodle.
   * @param model     the associated model instance.
   * @throws IllegalArgumentException if the supplied line width isn't greater than zero.
   * @throws NullPointerException     if any references are {@code null}.
   */
  DoodleTool(int lineWidth, IModel model) {
    if (lineWidth < 1) {
      throw new IllegalArgumentException("Invalid line width: " + lineWidth);
    }
    this.lineWidth = lineWidth;
    this.model = Objects.requireNonNull(model);
  }

  @Override
  public void pressed(MouseStatus mouseStatus) {
    Objects.requireNonNull(mouseStatus);
    doodle = LayerFactory.createDoodle(lineWidth, model.getSelectedColor());
    dragged(mouseStatus);
  }

  @Override
  public void dragged(MouseStatus mouseStatus) {
    Objects.requireNonNull(mouseStatus);
    model.notifyCanvasUpdateListeners();

    int x = model.getViewport().getTranslatedX(mouseStatus.getX());
    int y = model.getViewport().getTranslatedY(mouseStatus.getY());

    doodle.addPoint(new Point(x, y));
    doodle.draw(model.getRenderer(), model.getViewport());
  }

  @Override
  public void released(MouseStatus mouseStatus) {
    Objects.requireNonNull(mouseStatus);

    model.addLayer(doodle);
  }
}
