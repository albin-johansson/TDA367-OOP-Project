package chalmers.pimp.model.tools;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.MouseStatus;
import chalmers.pimp.model.Point;
import chalmers.pimp.model.canvas.layer.IDoodleLayer;
import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.LayerFactory;
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
<<<<<<< HEAD
  private int x, y;
  private IDoodleLayer doodle;
=======
  private ILayer doodle;
>>>>>>> dev

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
<<<<<<< HEAD
    doodle.addPoint(new Point(mouseStatus.getX(), mouseStatus.getY()));
    doodle.draw(model.getRenderer());
=======

    int x = model.getViewport().getTranslatedX(mouseStatus.getX());
    int y = model.getViewport().getTranslatedY(mouseStatus.getY());

    doodle.setPixel(PixelFactory.createPixel(x, y));
    doodle.draw(model.getRenderer(), model.getViewport());
>>>>>>> dev
  }

  @Override
  public void released(MouseStatus mouseStatus) {
    model.addLayer(doodle);
  }
}
