package chalmers.pimp.view.renderer;

import chalmers.pimp.model.IArea;
import chalmers.pimp.model.IRenderer;
import java.awt.Graphics2D;
import javafx.scene.canvas.GraphicsContext;

/**
 * The {@code RendererFactory} class is a factory for creating instances of the {@code IRenderer}
 * interface.
 */
public final class RendererFactory {

  private RendererFactory() {
  }

  /**
   * Creates and returns a Swing/AWT-based renderer.
   *
   * @param g    the graphics instance that the renderer will use.
   * @param area the rendering target area. This is used to obtain the width and height of the
   *             rendering target.
   * @return a Swing/AWT-based renderer.
   * @throws NullPointerException if any references are {@code null}.
   */
  public static IRenderer createSwingRenderer(Graphics2D g, IArea area) {
    return new SwingRenderer(g, area);
  }

  /**
   * Creates and returns a JavaFX-based renderer.
   *
   * @param g the graphics context the renderer should draw on.
   * @return a JavaFX-based renderer.
   * @throws NullPointerException if the graphics context is null.
   */
  public static IRenderer createFXRenderer(GraphicsContext g) {
    return new FXRenderer(g);
  }
}
