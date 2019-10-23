package chalmers.pimp.model.tools;

import chalmers.pimp.model.MouseStatus;

/**
 * The {@code ITool} interface specifies objects that represent some sort of mouse-related tool.
 *
 * @see ToolFactory
 */
public interface ITool {

  /**
   * Notifies a tool that the mouse has been dragged on the canvas.
   *
   * @param mouseStatus the status of the mouse including necessary information.
   * @throws NullPointerException if the supplied mouse status is {@code null}.
   */
  void dragged(MouseStatus mouseStatus);

  /**
   * Notifies a tool that the mouse has been clicked on the canvas.
   *
   * @param mouseStatus the status of the mouse including necessary information.
   * @throws NullPointerException if the supplied mouse status is {@code null}.
   */
  void pressed(MouseStatus mouseStatus);

  /**
   * Notifies a tool that the mouse has been released on the canvas.
   *
   * @param mouseStatus the status of the mouse including necessary information.
   * @throws NullPointerException if the supplied mouse status is {@code null}.
   */
  void released(MouseStatus mouseStatus);
}
