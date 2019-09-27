package controller;

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

}