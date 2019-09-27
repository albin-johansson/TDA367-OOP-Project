package model.tools;

import javafx.scene.input.MouseEvent;
import model.canvas.layer.ILayer;

/**
 * A tool is a tool that manipulates a layer.
 */
public interface ITool {

  /**
   * Notifies a tool that the mouse has been dragged on the canvas.
   *
   * @param mouseEvent a mouse event including information about the mouse.
   */
  void dragged(MouseEvent mouseEvent);

  /**
   * Notifies a tool that the mouse has been clicked on the canvas.
   *
   * @param mouseEvent a mouse event including information about the mouse.
   */
  void pressed(MouseEvent mouseEvent);

  /**
   * Notifies a tool that the mouse has been released on the canvas.
   *
   * @param mouseEvent a mouse event including information about the mouse.
   */
  void released(MouseEvent mouseEvent);

  /**
   * Sets the tools target layer.
   *
   * @param iLayer the layer that the tool should target.
   */
  void setTarget(ILayer iLayer);
}
