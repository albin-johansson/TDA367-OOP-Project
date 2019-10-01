package chalmers.pimp.model.tools;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.MouseStatus;

/**
 * A tool that moves the layer around by changing it's x and y coordinates
 */
public class MoveTool implements ITool {

  private int startX;
  private int startY;
  private final IModel model;

  MoveTool(IModel model){
    this.model = model;
  }

  @Override
  public void dragged(MouseStatus mouseStatus) {
    int xAmount = mouseStatus.getX() - startX;
    int yAmount = mouseStatus.getY() - startY;
    model.moveSelectedLayer(xAmount,yAmount);
  }

  @Override
  public void pressed(MouseStatus mouseStatus) {
    startX = mouseStatus.getX();
    startY = mouseStatus.getY();
  }

  @Override
  public void released(MouseStatus mouseStatus) {

  }
}
