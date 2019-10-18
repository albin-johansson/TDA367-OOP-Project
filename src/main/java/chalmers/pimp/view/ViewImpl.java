package chalmers.pimp.view;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.IRenderer;
import java.util.Objects;

/**
 * The {@code ViewImpl} class is an implementation of the {@code IView} interface.
 */
final class ViewImpl implements IView {
  
  private final IModel model;
  private GraphicsHandler graphicsHandler;

  /**
   * @param model the associated model instance.
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
    Objects.requireNonNull(renderer);
    graphicsHandler = new GraphicsHandler(renderer, model);
  }

  @Override
  public void repaint() {
    if (graphicsHandler != null) {
      graphicsHandler.render();
    }
  }
}