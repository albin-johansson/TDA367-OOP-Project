package controller;

import javafx.scene.input.MouseEvent;

/**
 * The {@code IController} interface specifies the facade for the controller component in the MVC
 * architecture that the Pimp application uses.
 */
public interface IController {

  /**
   * Runs the application.
   */
  void run();

  /**
   * Sets Pencil as selectedTool in model
   */
  void selectPencil();

  /**
   * Sets Eraser as selectedTool in model
   */
  void selectEraser();

  /**
   * Sets Bucket as selectedTool in model
   */
  void selectBucket();

  /**
   * Tells the model that the selected tool has been pressed.
   *
   * @param mouseEvent the associated mouseEvent
   */
  void selectedToolPressed(MouseEvent mouseEvent);

  /**
   * Tells the model that the selected tool has been dragged.
   *
   * @param mouseEvent the associated mouseEvent.
   */
  void selectedToolDragged(MouseEvent mouseEvent);

  /**
   * Tells the model that the selected tool has been released.
   *
   * @param mouseEvent the associated mouseEvent.
   */
  void selectedToolReleased(MouseEvent mouseEvent);
}