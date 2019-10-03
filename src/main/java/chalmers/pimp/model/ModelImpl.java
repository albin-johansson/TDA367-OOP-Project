package chalmers.pimp.model;

import chalmers.pimp.model.canvas.Canvas;
import chalmers.pimp.model.canvas.ICanvasUpdateListener;
import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.ILayerUpdateListener;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.model.canvas.layer.LayerFactory;
import chalmers.pimp.model.command.CommandFactory;
import chalmers.pimp.model.command.CommandManager;
import chalmers.pimp.model.command.ICommand;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.PixelData;
import chalmers.pimp.model.tools.ITool;
import java.util.Objects;

/**
 * The {@code ModelImpl} class is an implementation of the {@code IModel} interface.
 */
final class ModelImpl implements IModel {

  private final CommandManager commandManager;
  private final UndoRedoListenerComposite undoRedoListeners;
  private Canvas canvas;
  private Stroke stroke;
  private ITool selectedTool;

  ModelImpl() {
    canvas = new Canvas();
    commandManager = new CommandManager();
    undoRedoListeners = new UndoRedoListenerComposite();

    stroke = null;
    selectedTool = null;
  }

  /**
   * Notifies all registered undo/redo listeners.
   */
  private void notifyUndoRedoListeners() {
    var event = new UndoRedoEvent();

    boolean isUndoable = commandManager.isUndoable();
    boolean isRedoable = commandManager.isRedoable();

    event.setUndoable(isUndoable);
    event.setRedoable(isRedoable);

    if (isUndoable) {
      event.setUndoCommandName(commandManager.getUndoCommandName());
    }

    if (isRedoable) {
      event.setRedoCommandName(commandManager.getRedoCommandName());
    }

    undoRedoListeners.undoRedoStateChanged(event);
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
    notifyUndoRedoListeners();
  }

  @Override
  public void removeLayer(ILayer layer) {
    canvas.removeLayer(layer);
  }

  @Override
  public void removeLayer(int layerIndex) {
    canvas.removeLayer(layerIndex);
  }

  @Override
  public void selectLayer(int layerIndex) {
    canvas.selectLayer(layerIndex);
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
  public void setLayerVisibility(IReadOnlyLayer layer, boolean isVisible) {
    canvas.setLayerVisible(layer, isVisible);
  }

  @Override
  public void setLayerVisibility(int layerIndex, boolean isVisible) {
    canvas.setLayerVisible(layerIndex, isVisible);
  }

  @Override
  public void addCanvasUpdateListener(ICanvasUpdateListener listener) {
    canvas.addCanvasUpdateListener(listener);
  }

  @Override
  public void addUndoRedoListener(IUndoRedoListener listener) {
    undoRedoListeners.add(listener);
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
  public int getAmountOfLayers() {
    return canvas.getAmountOfLayers();
  }

  @Override
  public void setSelectedTool(ITool selectedTool) {
    this.selectedTool = selectedTool;
  }

  @Override
  public void selectedToolPressed(MouseStatus mouseStatus) {
    if (hasSelectedTool()) {

      ILayer layer = LayerFactory.createRasterLayer(100, 100);
      canvas.addLayer(layer);
      canvas.selectLayer(0);
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
    notifyUndoRedoListeners();
  }

  @Override
  public ModelMemento createSnapShot() {
    var canvasCopy = new Canvas(canvas);
    return new ModelMemento(canvasCopy);
  }

  @Override
  public void undo() {
    commandManager.undo();
    notifyUndoRedoListeners();
  }

  @Override
  public void redo() {
    commandManager.redo();
    notifyUndoRedoListeners();
  }

  @Override
  public void moveSelectedLayer(int xAmount, int yAmount) {
    canvas.moveSelectedLayer(xAmount, yAmount);
  }

  @Override
  public Canvas getCanvas() {
    return canvas;
  }
}