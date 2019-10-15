package chalmers.pimp.view;

import chalmers.pimp.model.IDrawable;
import chalmers.pimp.model.IModel;
import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.color.ColorFactory;
import java.util.Objects;

/**
 * The {@code ViewImpl} class is an implementation of the {@code IView} interface.
 */
final class ViewImpl implements IView {

  private static final String TM_STRING = "Made with PIMP™, by Smash Bros™ and powered by O'Boy™ and Barilla™";
  private static final int TM_FONT_SIZE = 356; //TODO Fix better ratio
  private final IModel model;
  private IRenderer renderer;

  /**
   * @param model the associated chalmers.pimp.model instance.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  ViewImpl(IModel model) {
    this.model = Objects.requireNonNull(model);
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
    renderer.setFillColor(ColorFactory.createColor(0xFF, 0xFF, 0xFF, 0xFF));
    renderer.fillRect(0, 0, renderer.getCanvasWidth(), renderer.getCanvasHeight());

    for (IDrawable drawable : model.getLayers()) {
      drawable.draw(renderer);
    }
    paintWaterMark();
  }

  private void paintWaterMark() {
//    renderer.setRotation(-35);
    renderer.setGlobalAlpha(0.5);
    renderer.setFillColor(ColorFactory.createColor(0xFF, 0xFF, 0xFF, 0xFF));
    renderer.setFillColor(ColorFactory.createColor(0, 0, 0, 0xFF));
    renderer.drawText(TM_STRING, 5, renderer.getCanvasHeight() - TM_FONT_SIZE / 35, TM_FONT_SIZE);
    renderer.setGlobalAlpha(1);
//    renderer.resetRotation();
  }
}