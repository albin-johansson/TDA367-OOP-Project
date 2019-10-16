package chalmers.pimp.controller.components;

import chalmers.pimp.controller.ControllerUtils;
import chalmers.pimp.model.IModel;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.model.canvas.layer.LayerType;
import chalmers.pimp.util.Resources;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;

/**
 * The {@code LayerItemPane} class represents a "layer item pane", which contains information about
 * a specific layer in the model. Instances of this class are designed to be disposed upon layer
 * model updates. In other words, instances of the {@code LayerItemPane} class are meant to be
 * short-lived.
 *
 * @see LayerItemContainerPane
 */
final class LayerItemPane extends AnchorPane {

  private static final Image EYE_OPEN_IMAGE;
  private static final Image EYE_CLOSED_IMAGE;

  static {
    try {
      URL path = Resources.find(LayerItemPane.class, "images/light/eye_closed.png");
      EYE_CLOSED_IMAGE = new Image(path.toURI().toString());

      path = Resources.find(LayerItemPane.class, "images/light/eye_open.png");
      EYE_OPEN_IMAGE = new Image(path.toURI().toString());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @FXML
  @SuppressWarnings("unused")
  private TextField textField;
  @FXML
  @SuppressWarnings("unused")
  private ToggleButton toggleButton;
  @FXML
  @SuppressWarnings("unused")
  private ImageView imageView;
  @FXML
  @SuppressWarnings("unused")
  private ImageView layerTypeIcon;
  @FXML
  @SuppressWarnings("unused")
  private ContextMenu contextMenu;

  private final IModel model;
  private final int associatedLayerIndex;

  /**
   * @param model                the associated model instance.
   * @param associatedLayerIndex the layer depth index of the associated layer in the model.
   * @throws IOException          if the associated FXML file cannot be found.
   * @throws NullPointerException if any references are {@code null}.
   */
  LayerItemPane(IModel model, int associatedLayerIndex) throws IOException {
    this.model = Objects.requireNonNull(model);
    this.associatedLayerIndex = associatedLayerIndex;

    ControllerUtils.makeController(this, Resources.find(getClass(), "layer_item.fxml"));

    textField.setText(model.getLayerName(associatedLayerIndex));

    IReadOnlyLayer activeLayer = model.getActiveLayer();
    if ((activeLayer != null) && (activeLayer.getDepthIndex() == associatedLayerIndex)) {
      setStyle("-fx-background-color: -selected-color;");
    }

    addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
      if (event.getButton() == MouseButton.PRIMARY) {
        selectLayer();
      } else if (event.getButton() == MouseButton.SECONDARY) {
        openContextMenu(event.getSceneX(), event.getSceneY());
      }
    });

    updateVisibilityHint();

    setOnDragDetected(this::handleDragDetected);
    setOnDragOver(this::handleDragOver);
    setOnDragDropped(this::handleDragDropped);
    setOnDragExited(this::handleDragExited);

    // FIXME replace with service
    String path = "images/light/" + LayerType.RASTER.name().toLowerCase() + ".png";

    // TODO create service for creating JavaFX images from a URL
    try {
      layerTypeIcon.setImage(new Image(Resources.find(getClass(), path).toURI().toString()));
    } catch (Exception e) {
      System.err.println("Failed to load layerTypeIcon icon! Exception: " + e);
    }
  }

  /**
   * Handles a mouse event triggered by initiating a mouse drag on the layer item pane.
   *
   * @param event the associated mouse event.
   */
  private void handleDragDetected(MouseEvent event) {
    if (event.isPrimaryButtonDown()) {
      Dragboard dragBoard = startDragAndDrop(TransferMode.MOVE);

      var snapshotParams = new SnapshotParameters();

      WritableImage image = snapshot(snapshotParams, null);

      dragBoard.setDragView(image);
      model.selectLayer(associatedLayerIndex);

      var content = new ClipboardContent();
      content.putString(String.valueOf(associatedLayerIndex));
      dragBoard.setContent(content);
    }
    event.consume();
  }

  /**
   * Handles a drag event triggered by a drag drop on the layer item pane.
   *
   * @param event the associated drag event.
   */
  private void handleDragDropped(DragEvent event) {
    Dragboard db = event.getDragboard();

    boolean success = false;

    if (db.hasString()) {
      int originDepth = Integer.parseInt(db.getString());

      IReadOnlyLayer activeLayer = model.getActiveLayer();
      if (activeLayer != null) {
        int dz = associatedLayerIndex - originDepth;
        model.changeLayerDepthIndex(activeLayer.getDepthIndex(), dz);
        success = true;
      }
    }

    event.setDropCompleted(success);
    event.consume();
  }

  /**
   * Handles a drag event triggered by a drag over the layer item pane.
   *
   * @param event the associated drag event.
   */
  private void handleDragOver(DragEvent event) {
    if (event.getGestureSource() != this && event.getDragboard().hasString()) {
      event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
      setStyle("-fx-border-color: -accent-color;");
    }
    event.consume();
  }

  /**
   * Handles a drag event triggered by a drag that exited the layer item pane.
   *
   * @param event the associated drag event.
   */
  private void handleDragExited(DragEvent event) {
    setStyle("-fx-border-width: 0,0,0,0");
  }

  /**
   * Updates the state of the layer visibility hint.
   */
  private void updateVisibilityHint() {
    if (model.isLayerVisible(associatedLayerIndex)) {
      imageView.setImage(EYE_OPEN_IMAGE);
    } else {
      imageView.setImage(EYE_CLOSED_IMAGE);
    }
  }

  /**
   * Opens a context menu for this layer item pane.
   *
   * @param x the x-coordinate of the context menu.
   * @param y the y-coordinate of the context menu.
   */
  private void openContextMenu(double x, double y) {
    contextMenu.show(this, x, y);
  }

  /**
   * Makes the layer associated with the layer item pane selected.
   */
  private void selectLayer() {
    model.selectLayer(associatedLayerIndex);
  }

  @FXML
  @SuppressWarnings("unused")
  private void toggleVisibility() {
    model.setLayerVisibility(associatedLayerIndex, toggleButton.isSelected());
    updateVisibilityHint();
  }

  @FXML
  @SuppressWarnings("unused")
  private void updateLayerName() {
    String temp = textField.getText();
    if (temp.isEmpty()) {
      textField.setText(model.getLayerName(associatedLayerIndex));
    } else {
      model.setLayerName(associatedLayerIndex, textField.getText());
    }
  }

  @FXML
  @SuppressWarnings("unused")
  private void decreaseZIndex() {
    model.changeLayerDepthIndex(associatedLayerIndex, -1);
  }

  @FXML
  @SuppressWarnings("unused")
  private void increaseZIndex() {
    model.changeLayerDepthIndex(associatedLayerIndex, 1);
  }

  @FXML
  @SuppressWarnings("unused")
  private void removeLayer() {
    model.removeLayer(associatedLayerIndex);
  }

  @FXML
  private void renameLayer(){
    textField.setEditable(true);
    textField.requestFocus();
  }

  @FXML
  private void setName(){
    model.setLayerName(associatedLayerIndex, textField.getText());
    textField.setEditable(false);
    requestFocus();
  }
}