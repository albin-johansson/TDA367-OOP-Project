package chalmers.pimp.controller.components;

import chalmers.pimp.controller.ControllerUtils;
import chalmers.pimp.model.IModel;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.util.Resources;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
  private Label layerName;
  @FXML
  @SuppressWarnings("unused")
  private ToggleButton toggleButton;
  @FXML
  @SuppressWarnings("unused")
  private ImageView imageView;
  @FXML
  @SuppressWarnings("unused")
  private ImageView layerPreview;
  @FXML
  @SuppressWarnings("unused")
  private ContextMenu contextMenu;
  @FXML
  @SuppressWarnings("unused")
  private AnchorPane standardPane;
  @FXML
  @SuppressWarnings("unused")
  private AnchorPane renamePane;
  @FXML
  @SuppressWarnings("unused")
  private TextField renameField;
  @FXML
  @SuppressWarnings("unused")
  private Button renameButton;

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

    layerName.setText(model.getLayerName(associatedLayerIndex));

    IReadOnlyLayer activeLayer = model.getActiveLayer();
    if ((activeLayer != null) && (activeLayer.getDepthIndex() == associatedLayerIndex)) {
      standardPane.setStyle("-fx-background-color: -selected-color;");
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

    /*// FIXME replace with service
    String path = "images/light/" + LayerType.RASTER.name().toLowerCase() + ".png";

    // TODO create service for creating JavaFX images from a URL
    try {
      layerTypeIcon.setImage(new Image(Resources.find(getClass(), path).toURI().toString()));
    } catch (Exception e) {
      System.err.println("Failed to load layerTypeIcon icon! Exception: " + e);
    }*/

    layerName.setContextMenu(contextMenu);
    renameField.focusedProperty().addListener((observable, oldvalue, newvalue) -> {
      if (!newvalue && !renameButton.isFocused()) {
        standardPane.toFront();
      }
    });
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

      if (model.hasActiveLayer()) {
        IReadOnlyLayer activeLayer = model.getActiveLayer();
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
      standardPane.setStyle("-fx-border-color: -accent-color;");
    }
    event.consume();
  }

  /**
   * Handles a drag event triggered by a drag that exited the layer item pane.
   *
   * @param event the associated drag event.
   */
  private void handleDragExited(DragEvent event) {
    standardPane.setStyle("-fx-border-width: 0,0,0,0");
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
    String temp = layerName.getText();
    if (temp.isEmpty()) {
      layerName.setText(model.getLayerName(associatedLayerIndex));
    } else {
      model.setLayerName(associatedLayerIndex, layerName.getText());
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

  /**
   * Sets the layerPreviewImage.
   *
   * @param image the new Image.
   */
  void setImage(Image image) {
    layerPreview.setImage(image);
  }

  @FXML
  private void renameLayer() {
    renamePane.toFront();
    renameField.clear();
    renameField.requestFocus();
  }

  @FXML
  private void setName() {
    model.setLayerName(associatedLayerIndex, renameField.getText());
    layerName.setText(renameField.getText());
    standardPane.toFront();
    requestFocus();
  }

  @FXML
  private void checkIfCancel(KeyEvent event) {
    if (event.getCode().equals(KeyCode.ESCAPE)) {
      standardPane.toFront();
    }
  }
}