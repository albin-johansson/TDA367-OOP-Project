package chalmers.pimp.model.canvas;

import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;
import chalmers.pimp.model.pixeldata.PixelData;
import java.util.Objects;

/**
 * The {@code Canvas} class is an implementation of the {@link ICanvas} interface.
 *
 * @see ICanvas
 */
final class CanvasImpl implements ICanvas {

  private final CanvasUpdateListenerComposite canvasUpdateListeners;
  private LayerManager layerManager;

  CanvasImpl() {
    canvasUpdateListeners = new CanvasUpdateListenerComposite();
    layerManager = new LayerManager();
  }

  /**
   * Creates a copy of the supplied canvas.
   *
   * @param canvas the canvas that will be copied.
   * @throws NullPointerException if the supplied canvas is {@code null}.
   */
  private CanvasImpl(CanvasImpl canvas) {
    Objects.requireNonNull(canvas);
    canvasUpdateListeners = new CanvasUpdateListenerComposite(canvas.canvasUpdateListeners);
    layerManager = new LayerManager(canvas.layerManager);
  }

  @Override
  public void notifyCanvasUpdateListeners() {
    canvasUpdateListeners.canvasUpdated();
  }

  @Override
  public void notifyLayerUpdateListeners() {
    layerManager.notifyListeners();
  }

  @Override
  public void addCanvasUpdateListener(ICanvasUpdateListener listener) {
    canvasUpdateListeners.add(listener);
  }

  @Override
  public void addLayerUpdateListener(ILayerUpdateListener listener) {
    layerManager.addLayerUpdateListener(listener);
  }

  @Override
  public void addLayer(ILayer layer) {
    layerManager.addLayer(layer);
  }

  @Override
  public void removeLayer(IReadOnlyLayer layer) {
    layerManager.removeLayer(layer);
  }

  @Override
  public void removeLayer(int layerIndex) {
    layerManager.removeLayer(layerIndex);
  }

  @Override
  public void selectLayer(int layerIndex) {
    layerManager.selectLayer(layerIndex);
  }

  @Deprecated
  @Override
  public void selectLayer(IReadOnlyLayer layer) {
    layerManager.selectLayer(layer.getDepthIndex());
  }

  @Override
  public void changeDepthIndex(IReadOnlyLayer layer, int deltaZ) {
    layerManager.changeDepthIndex(layer, deltaZ);
  }

  @Override
  public void moveActiveLayer(int xAmount, int yAmount) {
    layerManager.moveActiveLayer(xAmount, yAmount);
    canvasUpdateListeners.canvasUpdated();
  }

  @Deprecated
  @Override
  public void setLayerVisibility(IReadOnlyLayer readOnlyLayer, boolean isVisible) {
    layerManager.setLayerVisibility(readOnlyLayer.getDepthIndex(), isVisible);
    canvasUpdateListeners.canvasUpdated();
  }

  @Override
  public void setPixel(IPixel pixel) {
    layerManager.setActiveLayerPixel(pixel);
    canvasUpdateListeners.canvasUpdated();
  }

  @Override
  public void setPixels(int x, int y, IReadOnlyPixelData pixelData) {
    layerManager.setActiveLayerPixels(x, y, pixelData);
    canvasUpdateListeners.canvasUpdated();
  }

  @Override
  public void setLayerVisibility(int layerIndex, boolean isVisible) {
    layerManager.setLayerVisibility(layerIndex, isVisible);
  }

  @Override
  public void setLayerName(IReadOnlyLayer layer, String layerName) {
    layerManager.setLayerName(layer.getDepthIndex(), layerName);
  }

  @Override
  public void setLayerName(int layerIndex, String layerName) {
    layerManager.setLayerName(layerIndex, layerName);
  }

  @Override
  public void setActiveLayerX(int x) {
    layerManager.setActiveLayerX(x);
  }

  @Override
  public void setActiveLayerY(int y) {
    layerManager.setActiveLayerY(y);
  }

  @Override
  public void setPixels(int x, int y, PixelData pixels) {
    layerManager.setActiveLayerPixels(x, y, pixels);
  }

  @Override
  public int getAmountOfLayers() {
    return layerManager.getAmountOfLayers();
  }

  @Override
  public IReadOnlyLayer getActiveLayer() {
    return layerManager.getActiveLayer();
  }

  @Override
  public Iterable<? extends IReadOnlyLayer> getLayers() {
    return layerManager.getLayers();
  }

  @Override
  public void restore(CanvasMemento memento) {
    layerManager = memento.getLayerManager();
  }

  @Override
  public CanvasMemento createSnapShot() {
    return new CanvasMemento(new LayerManager(layerManager));
  }

  @Override
  public String toString() {
    String id = getClass().getSimpleName() + "@" + Integer.toHexString(hashCode());
    String state = "Layer manager: " + layerManager;
    return "(" + id + " | " + state + ")";
  }

  @Override
  public ICanvas copy() {
    return new CanvasImpl(this);
  }
}
