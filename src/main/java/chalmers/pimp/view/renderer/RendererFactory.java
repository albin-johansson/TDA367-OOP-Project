package chalmers.pimp.view.renderer;

import chalmers.pimp.model.IRenderer;
import javafx.scene.canvas.GraphicsContext;

/**
 * The {@code RendererFactory} class is a factory for creating instances of the {@code IRenderer}
 * interface.
 */
public class RendererFactory {

  private RendererFactory() {
  }

  /**
   * Creates and returns a new IRenderer
   *
   * @param g the graphics context the renderer should draw on.
   * @return a FX renderer.
   */
  public static IRenderer createFXRenderer(GraphicsContext g) {
    return new FXRenderer(g);
  }
}
