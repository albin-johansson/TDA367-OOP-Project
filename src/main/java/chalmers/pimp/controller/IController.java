package chalmers.pimp.controller;

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
   * Sets a pencil as selected tool in model
   */
  void selectPencil();

  /**
   * Sets a transparent pencil as selected tool in model
   */
  void selectEraser();

  /**
   * Sets s bucket as selected tool in model
   */
  void selectBucket();

  /**
   * Sets the rectangle tool as the selected tool in model
   */
  void selectRectangleTool();

  /**
   * Sets the rotate tool as the selected tool in model.
   */
  void selectRotateTool();

  /**
   * Sets the doodle tool as the selected tool in model
   */
  void selectDoodleTool();

  /**
   * Undoes the previously executed command. This method has no effect if there is nothing to undo.
   */
  void undo();

  /**
   * Redoes the previously undone command. This method has no effect if there is nothing to redo.
   */
  void redo();

  /**
   * Sets a move tool as selected tool in model.
   */
  void selectMoveTool();

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

  /**
   * Creates a new raster layer.
   */
  void createNewLayer();

  /**
   * Opens a file chooser dialog, which allows the user to import an image. If an image is selected,
   * it's injected into the model.
   */
  void openImageChooser();

  /**
   * Opens file chooser save dialog and allows you to save the Image.
   */
  void exportImage();
}