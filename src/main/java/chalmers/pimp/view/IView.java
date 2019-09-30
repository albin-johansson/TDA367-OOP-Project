package chalmers.pimp.view;

import javafx.scene.canvas.GraphicsContext;
import chalmers.pimp.model.canvas.ICanvasUpdateListener;

/**
 * The {@code IView} interface specifies the main chalmers.pimp.view component in the Pimp application. extends
 * {@link ICanvasUpdateListener}
 */
public interface IView extends ICanvasUpdateListener {

  /**
   * @param graphics the graphicscontext the chalmers.pimp.view should manipulate
   */
  void setGraphics(GraphicsContext graphics);

  /**
   * Tells the View to repaint itself
   */
  void repaint();
}
