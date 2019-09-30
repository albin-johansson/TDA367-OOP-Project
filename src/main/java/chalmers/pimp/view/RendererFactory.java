package chalmers.pimp.view;

import chalmers.pimp.model.IRenderer;
import javafx.scene.canvas.GraphicsContext;

/**
 * The {@code RendererFactory} class is a factory for creating instances of the {@code IRenderer}
 * interface.
 */
public final class RendererFactory {

  private RendererFactory() {
  }

  /**
   * Creates and returns a IRenderer.
   *
   * @param graphicsContext the graphics context the IRenderer should mutate.
   * @return a renderer.
   */
  public IRenderer createRenderer(GraphicsContext graphicsContext) {
    return new FXRenderer(graphicsContext);
  }
}
