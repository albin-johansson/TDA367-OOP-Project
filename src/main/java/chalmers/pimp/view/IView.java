package chalmers.pimp.view;

import chalmers.pimp.model.canvas.ICanvasUpdateListener;
import javafx.scene.canvas.GraphicsContext;

/**
 * The {@code IView} interface specifies the main chalmers.pimp.view component in the Pimp
 * application. extends {@link ICanvasUpdateListener}
 */
public interface IView extends ICanvasUpdateListener {

  /**
   * @param graphics the graphicscontext the chalmers.pimp.view should manipulate
   */
  void setRendererGraphics(GraphicsContext graphics);

  /**
   * Tells the View to repaint itself
   */
  void repaint();
}
