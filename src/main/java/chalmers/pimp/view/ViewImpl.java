package chalmers.pimp.view;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.color.ColorFactory;
import chalmers.pimp.model.viewport.IReadOnlyViewport;
import java.util.Objects;

/**
 * The {@code ViewImpl} class is an implementation of the {@code IView} interface.
 */
final class ViewImpl implements IView {

  private final IModel model;
  private IRenderer renderer;

  /**
   * @param model the associated model instance.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  ViewImpl(IModel model) {
    this.model = Objects.requireNonNull(model);
  }

  /**
   * Draws the model canvas area. This method should be called <b>before</b> rendering the model.
   */
  private void drawCanvasArea() {
    IReadOnlyViewport viewport = model.getViewport();

    renderer.setFillColor(ColorFactory.createColor(0xFF, 0xFF, 0xFF));

    int x = viewport.getRelativeX(0);
    int y = viewport.getRelativeY(0);

    renderer.fillRect(x, y, model.getWidth(), model.getHeight());
  }

  /**
   * Fills the surrounding area of the model canvas. This method should be called <b>after</b>
   * rendering the model.
   */
  private void fillCanvasSurroundingArea() {
    renderer.setFillColor(ColorFactory.createColor(0, 0, 0, 0xFF));

    final int vx = model.getViewport().getX();
    final int vy = model.getViewport().getY();
    final int canvasWidth = renderer.getCanvasWidth();
    final int canvasHeight = renderer.getCanvasHeight();
    final int modelWidth = model.getWidth();
    final int modelHeight = model.getHeight();

    // left hand side
    renderer.fillRect(0, 0, vx, canvasHeight);

    // right hand side
    renderer.fillRect((vx + modelWidth), 0, (canvasWidth - (vx + modelWidth)), canvasHeight);

    // top
    renderer.fillRect(0, 0, canvasWidth, vy);

    // bottom
    renderer.fillRect(0, (vy + modelHeight), canvasWidth, (canvasHeight - (vy + modelHeight)));
  }

  @Override
  public void canvasUpdated() {
    repaint();
  }

  @Override
  public void setRenderer(IRenderer renderer) {
    this.renderer = Objects.requireNonNull(renderer);
  }

  @Override
  public void repaint() {
    drawCanvasArea();
    model.draw(renderer);
    fillCanvasSurroundingArea();
  }
}