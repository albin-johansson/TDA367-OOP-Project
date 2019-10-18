package chalmers.pimp.model.tools;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.MouseStatus;
import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.LayerFactory;
import chalmers.pimp.model.color.ColorFactory;
import chalmers.pimp.model.pixeldata.PixelFactory;
import java.util.Objects;

/**
 * A tool which the user can deaw vectorised lines with
 */
final class DoodleTool implements ITool {

  private final IModel model;
  private final int lineWidth;
  private ILayer doodle;

  /**
   * Creates a doodle tool
   *
   * @param lineWidth the width of the doodle.
   * @param model     reference back to the model.
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
    doodle.setPixel(
        PixelFactory.createPixel(mouseStatus.getX(), mouseStatus.getY(), model.getSelectedColor()));
    doodle.draw(model.getRenderer());
  }

  @Override
  public void released(MouseStatus mouseStatus) {
    model.addLayer(doodle);
  }
}
