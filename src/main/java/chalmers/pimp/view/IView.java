package chalmers.pimp.view;

import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.canvas.ICanvasUpdateListener;

/**
 * The {@code IView} interface specifies the main model component in the Pimp application. extends
 * {@link ICanvasUpdateListener}
 */
public interface IView extends ICanvasUpdateListener {

  /**
   * Tells the View to repaint itself
   */
  void repaint();

  /**
   * @param renderer the specific renderer the view should use.
   * @throws NullPointerException if the provided renderer is {@code null}.
   */
  void setRenderer(IRenderer renderer);
}