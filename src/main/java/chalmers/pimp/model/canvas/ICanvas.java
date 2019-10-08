package chalmers.pimp.model.canvas;

import chalmers.pimp.model.ICopiable;
import chalmers.pimp.model.IMementoTarget;
import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;
import chalmers.pimp.model.pixeldata.PixelData;

public interface ICanvas extends IMementoTarget<CanvasMemento>, ICopiable<ICanvas> {

  void notifyCanvasUpdateListeners();

  void notifyLayerUpdateListeners();

  void addCanvasUpdateListener(ICanvasUpdateListener listener);

  void addLayerUpdateListener(ILayerUpdateListener listener);

  void addLayer(ILayer layer);

  void removeLayer(IReadOnlyLayer layer);

  void removeLayer(int layerIndex);

  void selectLayer(int layerIndex);

  @Deprecated
  void selectLayer(IReadOnlyLayer layer);

  @Deprecated
  void changeDepthIndex(IReadOnlyLayer layer, int deltaZ);

  void moveActiveLayer(int xAmount, int yAmount);

  @Deprecated
  void setLayerVisibility(IReadOnlyLayer layer, boolean isVisible);

  void setPixel(IPixel pixel);

  void setPixels(int x, int y, IReadOnlyPixelData pixelData);

  void setLayerVisibility(int layerIndex, boolean isVisible);

  @Deprecated
  void setLayerName(IReadOnlyLayer layer, String layerName);

  void setLayerName(int layerIndex, String layerName);

  void setActiveLayerX(int x);

  void setActiveLayerY(int y);

  void setPixels(int x, int y, PixelData pixels);

  int getAmountOfLayers();

  IReadOnlyLayer getActiveLayer();

  Iterable<? extends IReadOnlyLayer> getLayers();
}