package controller;

import controller.components.PimpEditorPane;
import java.io.IOException;
import java.util.Objects;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.IModel;
import view.IView;

/**
 * The {@code ControllerImpl} class is an implementation of the {@code IController} interface.
 */
final class ControllerImpl implements IController {

  private final IModel model;
  private final IView view;
  private final Stage stage;

  /**
   * @param model the associated model instance.
   * @param view the associated view instance.
   * @param stage the parent stage instance.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  ControllerImpl(IModel model, IView view, Stage stage) throws IOException {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    this.stage = Objects.requireNonNull(stage);

    PimpEditorPane pane = new PimpEditorPane();
    view.setGraphics(pane.getGraphics());

    stage.setScene(new Scene(pane, 800, 600));
    stage.setMaximized(true);

    pane.setOnCanvasPressed(e -> this.onCanvasPressed(e));
    pane.setOnCanvasReleased(e -> this.onCanvasReleased(e));
    pane.setOnCanvasDragged(e -> this.onCanvasDragged(e));
  }

  @Override
  public void run() {
    stage.show();
  }

  /**
   * Function which is executed when the user presses the canvas with the mouse
   *
   * @param e information about the mouse event
   */
  private void onCanvasPressed(MouseEvent e) {

  }

  /**
   * Function which is executed when mouse is released when clicking the canvas
   *
   * @param e information about the mouse event
   */
  private void onCanvasReleased(MouseEvent e) {

  }

  /**
   * Function which is executed each time the mouse position has changed during a drag
   *
   * @param e information about the mouse event
   */
  private void onCanvasDragged(MouseEvent e) {

  }
}