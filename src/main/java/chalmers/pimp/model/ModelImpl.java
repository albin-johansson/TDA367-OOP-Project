package chalmers.pimp.model;

import chalmers.pimp.model.canvas.Canvas;
import chalmers.pimp.model.canvas.ICanvasUpdateListener;
import chalmers.pimp.model.canvas.ILayerUpdateListener;
import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.model.color.ColorFactory;
import chalmers.pimp.model.command.CommandFactory;
import chalmers.pimp.model.command.CommandManager;
import chalmers.pimp.model.command.ICommand;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.PixelData;
import chalmers.pimp.model.tools.ITool;
import chalmers.pimp.model.tools.ToolFactory;
import java.util.Objects;

/**
 * The {@code ModelImpl} class is an implementation of the {@code IModel} interface.
 */
final class ModelImpl implements IModel {

  private final CommandManager commandManager;
  private Canvas canvas;
  private Stroke stroke;
  private ITool selectedTool;
  private IRenderer renderer;

  ModelImpl() {
    canvas = new Canvas();
    commandManager = new CommandManager();

    stroke = null;
    selectedTool = ToolFactory.createPencil(2, ColorFactory.createColor(0xFF, 0, 0xFF), this);
  }

  /**
   * Checks if there is a active tool selected.
   *
   * @return true if there is a tool selected.
   */
  private boolean hasSelectedTool() {
    return selectedTool != null;
  }

  @Override
  public void startStroke(IPixel pixel, int diameter) {
    Objects.requireNonNull(pixel);
    stroke = new Stroke(createSnapShot(), diameter);
    updateStroke(pixel);
  }

  @Override
  public void updateStroke(IPixel pixel) {
    Objects.requireNonNull(pixel);
    if (stroke != null) {
      stroke.add(pixel);
      stroke.updatePixels(this, pixel);
    }
  }

  @Override
  public void endStroke(IPixel pixel) {
    Objects.requireNonNull(pixel);
    if (stroke != null) {
      stroke.add(pixel);
      stroke.updatePixels(this, pixel);

      ICommand command = CommandFactory.createStrokeCommand(this, stroke);
      commandManager.insertCommand(command);
      stroke = null;
    }
  }

  @Override
  public void addLayer(ILayer layer) {
    ICommand addLayerCmd = CommandFactory.createAddLayerCommand(this, layer);
    addLayerCmd.execute();

    commandManager.insertCommand(addLayerCmd);
    notifyAllCanvasUpdateListeners();
  }

  @Override
  public void removeLayer(ILayer layer) {
    canvas.removeLayer(layer); // TODO replace with command
    notifyAllCanvasUpdateListeners();
  }

  @Override
  public void removeLayer(int layerIndex) {
    canvas.removeLayer(layerIndex);
  }

  @Override
  public void selectLayer(IReadOnlyLayer layer) {
    canvas.selectLayer(layer);
  }

  @Override
  public void selectLayer(int layerIndex) {
    canvas.selectLayer(layerIndex);
  }

  @Override
  public void moveLayer(IReadOnlyLayer layer, int steps) {
    canvas.changeDepthIndex(layer, steps);
  }

  @Override
  public void setLayerX(int x) {
    canvas.setLayerX(x);
  }

  @Override
  public void setLayerY(int y) {
    canvas.setLayerY(y);
  }

  @Override
  public void restore(ModelMemento memento) {
    canvas = memento.getCanvas();
    // TODO...
  }

  @Override
  public void setPixel(IPixel pixel) {
    canvas.setPixel(pixel);
  }

  @Override
  public void setPixels(int x, int y, PixelData pixelData) {
    canvas.setPixels(x, y, pixelData);
  }

  @Override
  public void setLayerName(IReadOnlyLayer layer, String layerName) {
    canvas.setLayerName(layer, layerName);
  }

  @Override
  public void setLayerName(int layerIndex, String layerName) {
    canvas.setLayerName(layerIndex, layerName);
  }

  @Override
  public void setLayerVisibility(IReadOnlyLayer layer, boolean isVisible) {
    canvas.setLayerVisibility(layer, isVisible);
  }

  @Override
  public void setLayerVisibility(int layerIndex, boolean isVisible) {
    canvas.setLayerVisibility(layerIndex, isVisible);
  }

  @Override
  public void addCanvasUpdateListener(ICanvasUpdateListener listener) {
    canvas.addCanvasUpdateListener(listener);
  }

  @Override
  public void addUndoRedoListener(IUndoRedoListener listener) {
    commandManager.addUndoRedoListener(listener);
  }

  @Override
  public void addLayerUpdateListener(ILayerUpdateListener listener) {
    canvas.addLayerUpdateListener(listener);
  }

  @Override
  public void addCommand(ICommand command) {
    commandManager.insertCommand(command);
  }

  @Override
  public Iterable<? extends IReadOnlyLayer> getLayers() {
    return canvas.getLayers();
  }

  @Override
  public IReadOnlyLayer getActiveLayer() {
    return canvas.getActiveLayer();
  }

  @Override
  public void setSelectedTool(ITool selectedTool) {
    this.selectedTool = selectedTool;
  }

  @Override
  public void selectedToolPressed(MouseStatus mouseStatus) {
    if (hasSelectedTool()) {
      selectedTool.pressed(mouseStatus);
    }
  }

  @Override
  public void selectedToolDragged(MouseStatus mouseStatus) {
    if (hasSelectedTool()) {
      selectedTool.dragged(mouseStatus);
    }
  }

  @Override
  public void selectedToolReleased(MouseStatus mouseStatus) {
    if (hasSelectedTool()) {
      selectedTool.released(mouseStatus);
    }
  }

  @Override
  public ModelMemento createSnapShot() {
    var canvasCopy = new Canvas(canvas);
    return new ModelMemento(canvasCopy);
  }

  @Override
  public void undo() {
    commandManager.undo();

    canvas.notifyLayerUpdateListeners();
    canvas.notifyCanvasUpdateListeners();
  }

  @Override
  public void redo() {
    commandManager.redo();

    canvas.notifyLayerUpdateListeners();
    canvas.notifyCanvasUpdateListeners();
  }

  @Override
  public void moveSelectedLayer(int xAmount, int yAmount) {
    canvas.moveSelectedLayer(xAmount, yAmount);
  }

  @Override
  public Canvas getCanvas() {
    return canvas;
  }

  @Override
  public IRenderer getRenderer() {
    return renderer;
  }

  @Override
  public void setRenderer(IRenderer renderer) {
    this.renderer = Objects.requireNonNull(renderer);
  }

  @Override
  public void notifyAllCanvasUpdateListeners() {
    canvas.notifyAllCanvasListeners();
  }
}