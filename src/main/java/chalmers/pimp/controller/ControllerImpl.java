package chalmers.pimp.controller;

import chalmers.pimp.controller.components.ImageChooser;
import chalmers.pimp.controller.components.PimpEditorPane;
import chalmers.pimp.model.IModel;
import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.canvas.layer.LayerFactory;
import chalmers.pimp.model.pixeldata.IRasterData;
import chalmers.pimp.model.tools.ITool;
import chalmers.pimp.model.tools.ToolFactory;
import chalmers.pimp.service.ImageExportService;
import chalmers.pimp.service.MouseStatusCreationService;
import chalmers.pimp.util.Resources;
import chalmers.pimp.view.IView;
import chalmers.pimp.view.renderer.RendererFactory;
import java.io.IOException;
import java.util.Objects;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
   * @param model the associated model instance.
   * @param view  the associated model instance.
   * @param stage the parent stage instance.
   * @throws NullPointerException if any arguments are {@code null}.
   * @throws IOException          if an error occurs when creating the controller panes.
   */
  ControllerImpl(IModel model, IView view, Stage stage) throws IOException {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    this.stage = Objects.requireNonNull(stage);

    var pane = new PimpEditorPane(model, this);
    IRenderer renderer = RendererFactory.createFXRenderer(pane.getGraphics());
    view.setRenderer(renderer);
    model.setRenderer(renderer);

    prepareStage(new Scene(pane, 800, 600));
    selectRasterPen();
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

  @Override
  public void run() {
    stage.show();
  }

  @Override
  public void centerViewport() {
    model.centerViewport();
  }

  @Override
  public void moveViewport(int dx, int dy) {
    model.moveViewport(dx, dy);
  }

  @Override
  public void setViewportWidth(int width) {
    model.setViewportWidth(width);
  }

  @Override
  public void setViewportHeight(int height) {
    model.setViewportHeight(height);
  }

  @Override
  public void selectRasterPen() {
    ITool pencil = ToolFactory.createRasterPen(10, model);
    model.setSelectedTool(pencil);
  }

  @Override
  public void selectEraser() {
    model.setSelectedTool(ToolFactory.createRasterEraser(10, model));
  }

  @Override
  public void selectRectangleTool() {
    ITool rectangleTool = ToolFactory.createShapeTool(model);
    model.setSelectedTool(rectangleTool);
  }

  @Override
  public void selectRotateTool() {
    ITool rotateTool = ToolFactory.createRotateTool(model);
    model.setSelectedTool(rotateTool);
  }

  @Override
  public void selectDoodleTool() {
    model.setSelectedTool(ToolFactory.createDoodleTool(2, model));
  }

  @Override
  public void selectMoveTool() {
    model.setSelectedTool(ToolFactory.createMoveTool(model));
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
  public void selectedToolPressed(MouseEvent mouseEvent) {
    model.selectedToolPressed(MouseStatusCreationService.createMouseStatus(mouseEvent, model));
  }

  @Override
  public void selectedToolDragged(MouseEvent mouseEvent) {
    model.selectedToolDragged(MouseStatusCreationService.createMouseStatus(mouseEvent, model));
  }

  @Override
  public void selectedToolReleased(MouseEvent mouseEvent) {
    model.selectedToolReleased(MouseStatusCreationService.createMouseStatus(mouseEvent, model));
  }

  @Override
  public void openImageChooser() {
    try {
      var imageChooser = new ImageChooser();

      IRasterData rasterData = imageChooser.openDialog(stage);
      if (rasterData == null) {
        return;
      }
      String pixelDataName = imageChooser.getMostRecentFileName();

      model.addLayer(LayerFactory.createRasterLayer(rasterData, pixelDataName));
    } catch (Exception e) {
      System.err.println("Failed to import image! Exception: " + e);
    }
  }

  @Override
  public void exportImage() {
    ImageExportService.exportImage(model.getWidth(), model.getHeight(), model.getLayers());
  }
}