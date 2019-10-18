package chalmers.pimp.model.tools;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.MouseStatus;
import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.LayerFactory;
import chalmers.pimp.model.color.ColorFactory;
import chalmers.pimp.model.pixeldata.PixelFactory;
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
  private ILayer doodle;

  /**
   * @param lineWidth the line width of the doodle.
   * @param model     the associated model instance.
   * @throws NullPointerException if any references are {@code null}.
   */
  DoodleTool(int lineWidth, IModel model) {
    this.lineWidth = lineWidth;
    this.model = Objects.requireNonNull(model);
  }

  @Override
  public void pressed(MouseStatus mouseStatus) {
    doodle = LayerFactory.createDoodle(lineWidth, model.getSelectedColor());
    dragged(mouseStatus);
  }

  @Override
  public void dragged(MouseStatus mouseStatus) {
    model.notifyCanvasUpdateListeners();

    int x = model.getViewport().getRelativeX(mouseStatus.getX());
    int y = model.getViewport().getRelativeY(mouseStatus.getY());

    doodle.setPixel(PixelFactory.createPixel(x, y));
    doodle.draw(model.getRenderer(), model.getViewport());
  }

  @Override
  public void released(MouseStatus mouseStatus) {
    model.addLayer(doodle);
  }
}
