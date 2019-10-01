package chalmers.pimp.controller;

import chalmers.pimp.controller.components.PimpEditorPane;
import java.awt.Color;
import java.io.IOException;
import java.util.Objects;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import chalmers.pimp.model.IModel;
import chalmers.pimp.model.MouseStatus;
import chalmers.pimp.model.tools.ToolFactory;
import chalmers.pimp.util.Resources;
import chalmers.pimp.view.IView;

/**
 * The {@code ControllerImpl} class is an implementation of the {@code IController} interface.
 */
final class ControllerImpl implements IController {

  private final IModel model;
  private final IView view;
  private final Stage stage;

  /**
   * @param model the associated chalmers.pimp.model instance.
   * @param view  the associated chalmers.pimp.view instance.
   * @param stage the parent stage instance.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  ControllerImpl(IModel model, IView view, Stage stage) throws IOException {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    this.stage = Objects.requireNonNull(stage);

    PimpEditorPane pane = new PimpEditorPane(model, this);
    view.setRendererGraphics(pane.getGraphics());

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

  @Override
  public void selectPencil() {
    model.setSelectedTool(ToolFactory.createPencil(10, Color.ORANGE, model));
  }

  @Override
  public void selectEraser() {
    Color color = new Color(0, 0, 0, 0);
    model.setSelectedTool(ToolFactory.createPencil(10, color, model));
  }

  @Override
  public void selectBucket() {

  }

  @Override
  public void selectedToolPressed(MouseEvent mouseEvent) {

    MouseStatus status = new MouseStatus((int) mouseEvent.getX(), (int) mouseEvent.getY(),
        fxButtonToInt(mouseEvent.getButton()));

    model.selectedToolPressed(status);
  }

  @Override
  public void selectedToolDragged(MouseEvent mouseEvent) {
    MouseStatus status = new MouseStatus((int) mouseEvent.getX(), (int) mouseEvent.getY(),
        fxButtonToInt(mouseEvent.getButton()));

    model.selectedToolDragged(status);
  }

  @Override
  public void selectedToolReleased(MouseEvent mouseEvent) {
    MouseStatus status = new MouseStatus((int) mouseEvent.getX(), (int) mouseEvent.getY(),
        fxButtonToInt(mouseEvent.getButton()));

    model.selectedToolReleased(status);
  }

  /**
   * Converts the button pressed to an int representation to reduce chalmers.pimp.model dependency
   * of JavaFX
   *
   * @param mouseButton the fx ENUM that tells which button has been pressed
   * @return an int representation
   * @throws IllegalStateException if mouseButton is not a MouseButton Enum
   */
  //TODO Change int representation to ENUM when updated in Model
  private int fxButtonToInt(MouseButton mouseButton) {
    int output = 0;
    switch (mouseButton) {
      case NONE:
        output = 0;
        break;
      case PRIMARY:
        output = 1;
        break;
      case MIDDLE:
        output = 2;
        break;
      case SECONDARY:
        output = 3;
        break;
      default:
        throw new IllegalStateException(
            "Mousebutton must be either NONE, PRIMARY, MIDDLE or SECONDARY");
    }

    return output;
  }
}