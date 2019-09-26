package controller;

import controller.components.PimpEditorPane;
import java.io.IOException;
import java.util.Objects;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.IModel;
import util.Resources;
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

    PimpEditorPane pane = new PimpEditorPane(this.model, this);
    view.setGraphics(pane.getGraphics());

    prepareStage(new Scene(pane, 800, 600));
  }

  /**
   * Prepares the primary stage for use.
   *
   * @param scene the scene that will be added to the stage.
   * @throws NullPointerException if the supplied scene is {@code null}.
   */
  private void prepareStage(Scene scene) {
    Objects.requireNonNull(scene);
    stage.setScene(scene);
    stage.setMaximized(true);
    stage.setTitle("PIMP - Professional Image Manipulation Program");
    try {
      Image icon = new Image(Resources.find(getClass(), "images/pimp_icon.png").toURI().toString());
      stage.getIcons().add(icon);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    stage.show();
  }
}