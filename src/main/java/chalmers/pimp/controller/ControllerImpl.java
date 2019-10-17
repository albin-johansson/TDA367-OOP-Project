package chalmers.pimp.controller;

import chalmers.pimp.controller.components.ImageChooser;
import chalmers.pimp.controller.components.PimpEditorPane;
import chalmers.pimp.model.IModel;
import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.MouseStatus;
import chalmers.pimp.model.MouseStatus.MouseButtonID;
import chalmers.pimp.model.canvas.layer.LayerFactory;
import chalmers.pimp.model.color.ColorFactory;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.pixeldata.PixelData;
import chalmers.pimp.model.tools.ITool;
import chalmers.pimp.model.tools.ToolFactory;
import chalmers.pimp.service.FXToMouseStatusButtonService;
import chalmers.pimp.util.Resources;
import chalmers.pimp.view.IView;
import chalmers.pimp.view.renderer.RendererFactory;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * The {@code ControllerImpl} class is an implementation of the {@code IController} interface.
 */
final class ControllerImpl implements IController {

  private final IModel model;
  private final IView view;
  private final Stage stage;
  private final PimpEditorPane pane;

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

    pane = new PimpEditorPane(model, this);
    IRenderer renderer = RendererFactory.createFXRenderer(pane.getGraphics());
    view.setRenderer(renderer);
    model.setRenderer(renderer);

    prepareStage(new Scene(pane, 800, 600));
    selectPencil();
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
    MouseButtonID buttonID = FXToMouseStatusButtonService.getMouseButtonID(event.getButton());
    return new MouseStatus((int) event.getX(), (int) event.getY(), buttonID);
  }

  @Override
  public void run() {
    stage.show();
  }

  @Override
  public void undo() {
    model.undo();
    view.repaint();
  }

  @Override
  public void redo() {
    model.redo();
    view.repaint();
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
  public void selectRectangleTool() {
    ITool rectangleTool = ToolFactory.createShapeTool(model);
    model.setSelectedTool(rectangleTool);
  }

  @Override
  public void selectDoodleTool() {
    model.setSelectedTool(
        ToolFactory.createDoodleTool(10, model));
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
  public void selectTextCool() {
    model.setSelectedTool(ToolFactory.createTextTool());
  }

  @Override
  public void openImageChooser() {
    try {
      var imageChooser = new ImageChooser();

      PixelData pixelData = imageChooser.openDialog(stage);
      if (pixelData == null) {
        return;
      }
      String pixelDataName = imageChooser.getMostRecentFileName();

      model.addLayer(LayerFactory.createRasterLayer(pixelData, pixelDataName));
    } catch (Exception e) {
      System.err.println("Failed to import image! Exception: " + e);
    }
  }

  @Override
  public void exportImage() {
    FileChooser fileChooser = new FileChooser();

    fileChooser.getExtensionFilters()
        .add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));
    fileChooser.getExtensionFilters()
        .add(new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg"));

    File file = fileChooser.showSaveDialog(null);

    if (file != null) {
      try {
        WritableImage image = pane.getGraphics().getCanvas()
            .snapshot(new SnapshotParameters(), null);
        RenderedImage renderedImage = SwingFXUtils.fromFXImage(image, null);

        ImageIO.write(renderedImage, "png", file);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }

  @Override
  public void createNewLayer() {
    model.addLayer(LayerFactory.createRasterLayer(800, 600));
  }
}
