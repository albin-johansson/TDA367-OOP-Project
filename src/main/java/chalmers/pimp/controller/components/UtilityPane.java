package chalmers.pimp.controller.components;

import chalmers.pimp.controller.ControllerUtils;
import chalmers.pimp.model.IModel;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.util.AnchorPanes;
import chalmers.pimp.util.Resources;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The {@code UtilityPane} is the right anchor pane that contains the color picker and the container
 * containing layers.
 */
class UtilityPane extends AnchorPane {

  private final LayerItemManagerPane layerItemManagerPane;
  private final ColorPickerPane colorPickerPane;
  private final IModel model;

  @FXML
  private VBox VBoxContainer;

  UtilityPane(IModel model) throws IOException {
    ControllerUtils.makeController(this, Resources.find(getClass(), "utility_pane.fxml"));
    this.model = Objects.requireNonNull(model);

    colorPickerPane = new ColorPickerPane(model);
    layerItemManagerPane = new LayerItemManagerPane();

    addColorPickerPane();
    addLayerItemManagerPane();
  }

  /**
   * Adds the color picker pane to the vbox and sets its anchor points to zero.
   */
  private void addColorPickerPane() {
    VBoxContainer.getChildren().add(colorPickerPane);
    AnchorPanes.setZeroAnchors(colorPickerPane);
  }

  /**
   * Adds the layer item manager pane to the vbox and sets its anchor points to zero.
   */
  private void addLayerItemManagerPane() throws IOException {
    VBoxContainer.getChildren().add(layerItemManagerPane);
    AnchorPanes.setZeroAnchors(layerItemManagerPane);
  }

  /**
   * Returns the color of the color picker pane.
   *
   * @return the color of the color picker pane.
   */
  public IColor getColor() {
    return colorPickerPane.getColor();
  }

  /**
   * Sets the color of the color picker pane.
   *
   * @param color the new color of the color picker.
   */
  public void setColor(IColor color) {
    colorPickerPane.setColor(color);
  }
}
