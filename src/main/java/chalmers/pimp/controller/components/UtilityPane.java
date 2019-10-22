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
final class UtilityPane extends AnchorPane {

  private final LayerItemContainerPane layerItemContainerPane;
  private final ColorPickerPane colorPickerPane;

  @FXML
  private VBox VBoxContainer;

  /**
   * @param model the associated model instance.
   * @throws IOException          if the utility pane fxml file is not found.
   * @throws NullPointerException if the provided model is {@code null}.
   */
  UtilityPane(IModel model) throws IOException {
    ControllerUtils.makeController(this, Resources.find(getClass(), "utility_pane.fxml"));
    Objects.requireNonNull(model);

    colorPickerPane = new ColorPickerPane(model);
    layerItemContainerPane = new LayerItemContainerPane(model);

    addColorPickerPane();
    addLayerItemContainerPane();

    model.addColorChangeListener(colorPickerPane);
    model.addLayerUpdateListener(layerItemContainerPane);
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
  private void addLayerItemContainerPane() {
    VBoxContainer.getChildren().add(layerItemContainerPane);
    AnchorPanes.setZeroAnchors(layerItemContainerPane);
  }

  /**
   * Returns the color of the color picker pane.
   *
   * @return the color of the color picker pane.
   */
  public IColor getColor() {
    return colorPickerPane.getColor();
  }
}
