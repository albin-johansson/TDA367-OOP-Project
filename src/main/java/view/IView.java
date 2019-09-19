package view;

import javafx.scene.canvas.GraphicsContext;
import model.canvas.ICanvasUpdateListener;

/**
 * The {@code IView} interface specifies the main view component in the Pimp application.
 * extends ICanvasUpdateListener
 */
public interface IView extends ICanvasUpdateListener {

    void setGraphics(GraphicsContext gc);

    void repaint();

}
