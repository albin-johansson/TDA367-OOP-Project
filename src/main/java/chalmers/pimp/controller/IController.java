package chalmers.pimp.controller;

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
   * Sets the MoveTool as selected tool in chalmers.pimp.model
   */
  void selectMoveTool();

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
}