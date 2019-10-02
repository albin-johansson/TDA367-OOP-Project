package chalmers.pimp.controller;

import chalmers.pimp.model.canvas.layer.ILayer;
import javafx.scene.input.MouseEvent;

/**
 * The {@code IController} interface specifies the facade for the chalmers.pimp.controller component
 * in the MVC architecture that the Pimp application uses.
 */
public interface IController {

  /**
   * Runs the application.
   */
  void run();

  /**
   * Sets Pencil as selectedTool in chalmers.pimp.model
   */
  void selectPencil();

  /**
   * Sets a Transparent Pencil as selectedTool in chalmers.pimp.model
   */
  void selectEraser();

  /**
   * Sets Bucket as selectedTool in chalmers.pimp.model
   */
  void selectBucket();

  /**
   * Tells the chalmers.pimp.model that the selected tool has been pressed.
   *
   * @param mouseEvent the associated mouseEvent
   */
  void selectedToolPressed(MouseEvent mouseEvent);

  /**
   * Tells the chalmers.pimp.model that the selected tool has been dragged.
   *
   * @param mouseEvent the associated mouseEvent.
   */
  void selectedToolDragged(MouseEvent mouseEvent);

  /**
   * Tells the chalmers.pimp.model that the selected tool has been released.
   *
   * @param mouseEvent the associated mouseEvent.
   */
  void selectedToolReleased(MouseEvent mouseEvent);

  /**
   * Opens a file chooser window for the user to select an image which is imported as a new layer
   */
  void importImage();
}