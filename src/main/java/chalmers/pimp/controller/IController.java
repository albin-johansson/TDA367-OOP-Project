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
   * Centers the viewport over the canvas.
   */
  void centerViewport();

  /**
   * Moves the viewport.
   *
   * @param dx the x-axis offset, may be negative.
   * @param dy the y-axis offset, may be negative.
   */
  void moveViewport(int dx, int dy);

  /**
   * Sets the width of the viewport.
   *
   * @param width the new width of the viewport.
   */
  void setViewportWidth(int width);

  /**
   * Sets the height of the viewport.
   *
   * @param height the new width of the viewport.
   */
  void setViewportHeight(int height);

  /**
   * Sets a raster pen as the selected tool.
   */
  void selectRasterPen();

  /**
   * Sets a transparent pencil as the selected tool.
   */
  void selectEraser();

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
   * Sets a move tool as selected tool in model.
   */
  void selectMoveTool();

  /**
   * Undoes the previously executed command. This method has no effect if there is nothing to undo.
   */
  void undo();

  /**
   * Redoes the previously undone command. This method has no effect if there is nothing to redo.
   */
  void redo();

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
   * Opens a file chooser dialog, which allows the user to import an image. If an image is selected,
   * it's injected into the model.
   */
  void openImageChooser();

  /**
   * Attempts to save the current canvas state into an image. Invoking this method will open a modal
   * dialog. This method has no effect if the save operation fails.
   */
  void exportImage();
}