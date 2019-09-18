package controller;

import controller.components.PimpEditorPane;
import java.io.IOException;
import java.util.Objects;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The {@code ControllerImpl} class is an implementation of the {@code IController} interface.
 */
final class ControllerImpl implements IController {

  private final Stage stage;

  /**
   * @param stage the parent stage instance.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  ControllerImpl(Stage stage) throws IOException {
    this.stage = Objects.requireNonNull(stage);

    PimpEditorPane pane = new PimpEditorPane();

    stage.setScene(new Scene(pane, 800, 600));
    stage.setMaximized(true);
    stage.show();
  }
}