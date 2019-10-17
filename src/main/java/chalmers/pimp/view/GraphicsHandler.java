package chalmers.pimp.view;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.color.ColorFactory;
import chalmers.pimp.model.color.IReadOnlyColor;
import chalmers.pimp.model.viewport.IReadOnlyViewport;
import java.util.Objects;

/**
 * The {@code GraphicsHandler} class is responsible for rendering the visual representation of the
 * model canvas.
 */
final class GraphicsHandler {

  private static final IReadOnlyColor BLACK = ColorFactory.createColor(0, 0, 0);
  private static final IReadOnlyColor LIGHT_GRAY = ColorFactory.createColor(0xCC, 0xCC, 0xCC);
  private static final IReadOnlyColor WHITE = ColorFactory.createColor(0xFF, 0xFF, 0xFF);

  private final IRenderer renderer;
  private final IModel model;
  private final int shadowSize;

  /**
   * @param renderer the renderer that will be used.
   * @param model    the associated model instance.
   * @throws NullPointerException if any references are {@code null}.
   */
  GraphicsHandler(IRenderer renderer, IModel model) {
    this.renderer = Objects.requireNonNull(renderer);
    this.model = Objects.requireNonNull(model);
    shadowSize = 1;
  }

  /**
   * Draws the model canvas area. This method should be called <b>before</b> rendering the model.
   */
  private void drawCanvasArea() {
    IReadOnlyViewport viewport = model.getViewport();

    renderer.setFillColor(WHITE);

    int x = viewport.getRelativeX(0);
    int y = viewport.getRelativeY(0);
    int width = Math.max(model.getWidth(), renderer.getCanvasWidth());
    int height = Math.max(model.getHeight(), renderer.getCanvasHeight());

    renderer.fillRect(x, y, width, height);
  }

  /**
   * Fills the surrounding area of the model canvas. This method should be called <b>after</b>
   * rendering the model.
   */
  private void fillCanvasSurroundingArea(int vx, int vy, int modelWidth, int modelHeight) {
    final int canvasWidth = renderer.getCanvasWidth();
    final int canvasHeight = renderer.getCanvasHeight();

    renderer.setFillColor(LIGHT_GRAY);

    // left hand side
    renderer.fillRect(0, 0, vx, canvasHeight);

    // right hand side
    renderer.fillRect((vx + modelWidth), 0, (canvasWidth - (vx + modelWidth)), canvasHeight);

    // top
    renderer.fillRect(0, 0, canvasWidth, vy);

    // bottom
    renderer.fillRect(0, (vy + modelHeight), canvasWidth, (canvasHeight - (vy + modelHeight)));
  }

  /**
   * Draws the canvas shadow.
   *
   * @param vx          the viewport x-coordinate.
   * @param vy          the viewport y-coordinate.
   * @param modelWidth  the width of the model width.
   * @param modelHeight the height of the model height.
   */
  private void drawCanvasShadow(int vx, int vy, int modelWidth, int modelHeight) {
    renderer.setFillColor(BLACK);
    renderer.fillRect((vx + modelWidth), vy, shadowSize, modelHeight);
    renderer.fillRect(vx, (vy + modelHeight), modelWidth + shadowSize, shadowSize);
  }

  /**
   * Renders the model canvas.
   */
  void render() {
    final int vx = model.getViewport().getX();
    final int vy = model.getViewport().getY();
    final int modelWidth = model.getWidth();
    final int modelHeight = model.getHeight();

    drawCanvasArea();
    model.draw(renderer);
    fillCanvasSurroundingArea(vx, vy, modelWidth, modelHeight);
    drawCanvasShadow(vx, vy, modelWidth, modelHeight);
  }
}