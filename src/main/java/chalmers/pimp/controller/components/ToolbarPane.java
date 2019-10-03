package chalmers.pimp.controller.components;

import chalmers.pimp.controller.ControllerUtils;
import chalmers.pimp.controller.IController;
import chalmers.pimp.model.IUndoRedoListener;
import chalmers.pimp.model.UndoRedoEvent;
import chalmers.pimp.util.Resources;
import java.io.IOException;
import java.util.Objects;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/**
 * The {@code ToolbarPane} class represents the top pane in the Pimp application.
 */
final class ToolbarPane extends AnchorPane implements IUndoRedoListener {

  private final IController controller;
  @FXML
  @SuppressWarnings("unused")
  private MenuItem undoMenuItem;
  @FXML
  @SuppressWarnings("unused")
  private MenuItem redoMenuItem;
  @FXML
  @SuppressWarnings("unused")
  private Button undoButton;
  @FXML
  @SuppressWarnings("unused")
  private Button redoButton;

  /**
   * @param controller the associated chalmers.pimp.controller instance.
   * @throws IOException          if the associated FXML file cannot be read.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  ToolbarPane(IController controller) throws IOException {
    this.controller = Objects.requireNonNull(controller);
    ControllerUtils.makeController(this, Resources.find(getClass(), "toolbar.fxml"));
  }

  @FXML
  private void undo() {
    controller.undo();
  }

  @FXML
  private void redo() {
    controller.redo();
  }

  @FXML
  private void exit() {
    Platform.exit();
  }

  @FXML
  private void createNewLayer() {
    controller.createNewLayer();
  }



  private void openImageChooser() {
    controller.openImageChooser();
  }

  @Override
  public void undoRedoStateChanged(UndoRedoEvent event) {
    undoButton.setDisable(!event.isUndoable());
    redoButton.setDisable(!event.isRedoable());

    undoMenuItem.setDisable(!event.isUndoable());
    redoMenuItem.setDisable(!event.isRedoable());

    undoMenuItem.setText("Undo " + event.getUndoCommandName());
    redoMenuItem.setText("Redo " + event.getRedoCommandName());
  }
}
