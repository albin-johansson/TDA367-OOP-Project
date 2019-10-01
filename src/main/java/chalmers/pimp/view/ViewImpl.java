package chalmers.pimp.view;

import chalmers.pimp.model.IDrawable;
import chalmers.pimp.model.IModel;
import chalmers.pimp.model.IRenderer;
import chalmers.pimp.view.renderer.RendererFactory;
import java.util.Objects;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import chalmers.pimp.model.IModel;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.service.PixelDataToFXService;

/**
 * The {@code ViewImpl} class is an implementation of the {@code IView} interface.
 */
final class ViewImpl implements IView {

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
  public void setRendererGraphics(GraphicsContext graphics) {
    renderer = RendererFactory.createFXRenderer(graphics);
  }

  @Override
  public void repaint() {
    for (IDrawable drawable : model.getLayers()) {
      drawable.draw(renderer);
    }
  }
}