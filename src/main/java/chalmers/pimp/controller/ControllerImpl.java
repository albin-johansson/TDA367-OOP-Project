package chalmers.pimp.controller;

import chalmers.pimp.controller.components.ImageChooser;
import chalmers.pimp.controller.components.PimpEditorPane;
import chalmers.pimp.model.IModel;
import chalmers.pimp.model.MouseStatus;
import chalmers.pimp.model.canvas.layer.LayerFactory;
import chalmers.pimp.model.color.ColorFactory;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.pixeldata.PixelData;
import chalmers.pimp.model.tools.ITool;
import chalmers.pimp.model.tools.ToolFactory;
import chalmers.pimp.util.Resources;
import chalmers.pimp.view.IView;
import java.io.IOException;
import java.util.Objects;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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

    var pane = new PimpEditorPane(model, this);
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
      var icon = new Image(Resources.find(getClass(), "images/pimp_icon.png").toURI().toString());
      stage.getIcons().add(icon);
    } catch (Exception e) {
      System.err.println("Failed to load PIMP icon! Exception: " + e);
    }
  }

  /**
   * Creates and returns a mouse status instance that describes the supplied mouse event.
   *
   * @param event the mouse event that will be "copied".
   * @return a mouse status instance that describes the supplied mouse event.
   * @throws NullPointerException if the supplied mouse event is {@code null}.
   */
  private MouseStatus createMouseStatus(MouseEvent event) {
    Objects.requireNonNull(event);
    int buttonID = fxButtonToInt(event.getButton());
    return new MouseStatus((int) event.getX(), (int) event.getY(), buttonID);
  }

  @Override
  public void run() {
    stage.show();
  }

  @Override
  public void selectPencil() {
    ITool pencil = ToolFactory.createPencil(10, ColorFactory.createColor(255, 100, 50, 255), model);
    model.setSelectedTool(pencil);
  }

  @Override
  public void selectEraser() {
    IColor color = ColorFactory.createColor(0, 0, 0, 0);
    model.setSelectedTool(ToolFactory.createPencil(10, color, model));
  }

  @Override
  public void selectBucket() {

  }

  @Override
  public void selectMoveTool() {
    model.setSelectedTool(ToolFactory.createMoveTool(model));
  }

  @Override
  public void selectedToolPressed(MouseEvent mouseEvent) {
    model.selectedToolPressed(createMouseStatus(mouseEvent));
  }

  @Override
  public void selectedToolDragged(MouseEvent mouseEvent) {
    model.selectedToolDragged(createMouseStatus(mouseEvent));
  }

  @Override
  public void selectedToolReleased(MouseEvent mouseEvent) {
    model.selectedToolReleased(createMouseStatus(mouseEvent));
  }

  @Override
  public void openImageChooser() {
    try {
      var imageChooser = new ImageChooser();

      PixelData pixelData = imageChooser.openDialog(stage);
      if (pixelData == null) {
        return;
      }

      model.addLayer(LayerFactory.createRasterLayer(pixelData));
    } catch (Exception e) {
      System.err.println("Failed to import image! Exception: " + e);
    }
  }

  /**
   * Converts the button pressed to an integer representation.
   *
   * @param mouseButton the enum value that describes the mouse button.
   * @return an int representation of the supplied enum value.
   * @throws IllegalStateException if the supplied value isn't supported.
   * @throws NullPointerException  if the supplied value is {@code null}.
   */
  private int fxButtonToInt(MouseButton mouseButton) {
    //TODO Change int representation to ENUM when updated in Model
    Objects.requireNonNull(mouseButton);
    switch (mouseButton) {
      case NONE:
        return 0;
      case PRIMARY:
        return 1;
      case MIDDLE:
        return 2;
      case SECONDARY:
        return 3;
      default:
        throw new IllegalStateException("Invalid mouse button value: " + mouseButton);
    }
  }
}