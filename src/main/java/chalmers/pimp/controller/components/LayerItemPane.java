package chalmers.pimp.controller.components;

import chalmers.pimp.controller.ControllerUtils;
import chalmers.pimp.model.IModel;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.util.Resources;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
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

  @FXML
  private AnchorPane rootPane;

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
  private Label textLabel;
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
  // private IModel model;
  // private IReadOnlyLayer layer;

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

    textLabel.setText(model.getLayerName(associatedLayerIndex));

    IReadOnlyLayer activeLayer = model.getActiveLayer();
    if ((activeLayer != null) && (activeLayer.getDepthIndex() == associatedLayerIndex)) {
      setStyle("-fx-background-color: -selected-color;");
    }

    updateVisibilityHint();
    // this.layer = Objects.requireNonNull(layer);
    // textLabel.setText(layer.getName());
    // updateVisibilityImage();

    addDragEventHandler();
    addDragOverEventHandler();
    addDragExitedEventHandler();
    addDropEventHandler();
  }

  /**
   * Adds the EventHandler for when a LayerItem is being dragged.
   */
  private void addDragEventHandler() {
    rootPane.setOnDragDetected((MouseEvent e) -> {

      if (e.isPrimaryButtonDown()) {
        Dragboard db = rootPane.startDragAndDrop(TransferMode.MOVE);

        SnapshotParameters sp = new SnapshotParameters();

        WritableImage image = rootPane.snapshot(sp, null);

        db.setDragView(image);
        model.selectLayer(layer);

        ClipboardContent content = new ClipboardContent();
        content.putString(String.valueOf(layer.getDepthIndex()));
        db.setContent(content);
      }
      e.consume();
    });
  }

  /**
   * Adds the EventHandler for when a LayerItem is having another Item dragged over it.
   */
  private void addDragOverEventHandler() {
    rootPane.addEventHandler(DragEvent.DRAG_OVER, (DragEvent event) -> {
      if (event.getGestureSource() != rootPane
          && event.getDragboard().hasString()) {
        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        rootPane.setStyle("-fx-border-color: #990bc8;");
      }
      event.consume();
    });
  }

  /**
   * Removes the border from previous drag action.
   */
  private void addDragExitedEventHandler() {
    rootPane.addEventHandler(DragEvent.DRAG_EXITED, (DragEvent event) -> {
      rootPane.setStyle("-fx-border-width: 0,0,0,0");
    });
  }

  /**
   * Adds the EventHandler for when another LayerItemPane is dropped on top of this LayerItemPane
   */
  private void addDropEventHandler() {
    rootPane.addEventHandler(DragEvent.DRAG_DROPPED, (DragEvent event) -> {

      Dragboard db = event.getDragboard();

      boolean success = false;

      if (db.hasString()) {
        int originDepth = Integer.parseInt(db.getString());
        model.moveLayer(model.getActiveLayer(), layer.getDepthIndex() - originDepth);
        success = true;
      }

      event.setDropCompleted(success);
      event.consume();
    });
  }

//  void setTypeIcon() {
//
//    //TODO Fix themes
//    String path = "images/light/" + layer.getLayerType().name().toLowerCase() + ".png";
//
//    try {
//      layerTypeIcon.setImage(new Image(Resources.find(getClass(), path).toURI().toString()));
//    } catch (Exception e) {
//      System.err.println("Failed to load layerTypeIcon icon! Exception: " + e);
//    }
//  }

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

  @FXML
  @SuppressWarnings("unused")
  private void toggleVisibility() {
    model.setLayerVisibility(associatedLayerIndex, toggleButton.isSelected());
    updateVisibilityHint();
  }

  @FXML
  @SuppressWarnings("unused")
  private void updateLayerName() {
    String temp = textLabel.getText();
    if (temp.isEmpty()) {
      textLabel.setText(model.getLayerName(associatedLayerIndex));
    } else {
      model.setLayerName(associatedLayerIndex, textLabel.getText());
    }
  }

  @FXML
  @SuppressWarnings("unused")
  private void updateActiveLayer() {
    model.selectLayer(associatedLayerIndex);
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
  private void openContextMenu(ContextMenuEvent c) {
    contextMenu.show(rootPane, c.getSceneX(), c.getSceneY());
  }
  
  @FXML
  @SuppressWarnings("unused")
  private void removeLayer() {
    model.removeLayer(associatedLayerIndex);
  }
}