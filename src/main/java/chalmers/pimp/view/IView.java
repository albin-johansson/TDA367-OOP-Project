package chalmers.pimp.view;

import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.canvas.ICanvasUpdateListener;

/**
 * The {@code IView} interface specifies the main chalmers.pimp.view component in the Pimp
 * application. extends {@link ICanvasUpdateListener}
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

  /**
   * Sets the paintWaterMark boolean.
   *
   * @param flag the new boolean value.
   */
  void setPaintWaterMarkBoolean(boolean flag);

  /**
   * Gets the paintWaterMark boolean.
   *
   * @return the boolean value.
   */
  boolean getPaintWaterMarkBoolean();
}