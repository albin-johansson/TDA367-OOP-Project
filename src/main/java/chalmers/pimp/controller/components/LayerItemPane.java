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
 * The {@code LayerItemPane} class represents a layer item in the chalmers.pimp.view.
 */
final class LayerItemPane extends AnchorPane {

  @FXML
  private AnchorPane rootPane;

  private static final Image EYE_OPEN_IMAGE;
  private static final Image EYE_CLOSED_IMAGE;

  static {
    try {
      URL path = (Resources.find(LayerItemPane.class, "images/light/eye_closed.png"));
      EYE_CLOSED_IMAGE = new Image(path.toURI().toString());
      path = (Resources.find(LayerItemPane.class, "images/light/eye_open.png"));
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
  private ImageView layerTypeIcon;

  @FXML
  private ContextMenu contextMenu;

  private IModel model;
  private IReadOnlyLayer layer;

  /**
   * @param model an reference to the {@code IModel}.
   * @param layer the layer this {@code LayerItem} represents.
   * @throws IOException          if the associated FXML file cannot be found.
   * @throws NullPointerException if the IReadOnlyLayer argument is null
   */
  LayerItemPane(IModel model, IReadOnlyLayer layer) throws IOException {
    ControllerUtils.makeController(this, Resources.find(getClass(), "layer_item.fxml"));
    this.model = Objects.requireNonNull(model);
    this.layer = Objects.requireNonNull(layer);
    textLabel.setText(layer.getName());
    updateVisibilityImage();

    createContextMenu();

    addDragEventHandler();
    addDragOverEventHandler();
    addDragExitedEventHandler();
    addDropEventHandler();
  }

  private void createContextMenu(){
    ContextMenu contextMenu = new ContextMenu();

  }

  /**
   * Adds the drag EventHandler.
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
   * Adds the drag over EventHandler.
   */
  private void addDragOverEventHandler() {
    rootPane.addEventHandler(DragEvent.DRAG_OVER, (DragEvent event) -> {
      if (event.getGestureSource() != rootPane
          && event.getDragboard().hasString()) {
        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        //rootPane.setStyle("-fx-border-width: 2px 10px 4px 20px;");
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
   * Adds the drop EventHandler.
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

  /**
   * Toggles the visibility boolean connected with this layerItem's layer through the
   * chalmers.pimp.model's method
   */
  @FXML
  private void toggleVisibility() {
    for (IReadOnlyLayer l : model.getLayers()) {
      if (layer.equals(l)) {
        model.setLayerVisibility(l, toggleButton.isSelected());
        break;
      }
    }
    updateVisibilityImage();
  }

  /**
   * Updates this layerItem's layer's name through the chalmers.pimp.model.
   */
  @FXML
  private void updateLayerName() {
    String temp = textLabel.getText();
    if (temp == "") {
      textLabel.setText(layer.getName());
    } else {
      model.setLayerName(layer, textLabel.getText());
    }
  }

  /**
   * Checks which mouse button was pressed and calls the appropriate method.
   *
   * @param e the associated MouseEvent.
   */
  @FXML
  private void handleMouseEvent(MouseEvent e) {
    System.out.println(e.isPrimaryButtonDown());
    if (e.isPrimaryButtonDown()) {
      updateActiveLayer();
    } else if (e.isSecondaryButtonDown()) {
      //openContextMenu(e);
    }
  }

  /**
   * Sets this layer item's associated layer as the active layer.
   */
  @FXML
  private void updateActiveLayer() {
    model.selectLayer(layer);
    showIfLayerIsSelected();
  }

  @FXML
  private void openContextMenu(ContextMenuEvent c){
    contextMenu.show(rootPane,c.getSceneX(),c.getSceneY());
  }

  @FXML
  private void removeLayer() {
    model.removeLayer(layer);
  }

  //TODO Rethink if below methods should all be private and called by single update method...

  /**
   * Updates this pane, runs all related private methods.
   */
  void update() {
    updateVisibilityImage();
    showIfLayerIsSelected();
  }

  /**
   * Updates the image used on the visibility button based on "this" layers visibility in the
   * chalmers.pimp.model.
   */
  private void updateVisibilityImage() {
    if (layer.isVisible()) {
      imageView.setImage(EYE_OPEN_IMAGE);
    } else {
      imageView.setImage(EYE_CLOSED_IMAGE);
    }
  }

  /**
   * Sets the style for this {@code LayerItemPane} to Gray if the corresponding Layer is active.
   * Reverts to CSS default otherwise.
   */
  private void showIfLayerIsSelected() {
    if (layer == model.getActiveLayer()) {
      this.setStyle("-fx-background-color: -selected-color");
    } else {
      this.setStyle("");
    }
  }

  /**
   * Returns the {@code IReadOnlyLayer} that this LayerItem represents.
   *
   * @return the layer this LayerItem represents.
   */
  IReadOnlyLayer getLayer() {
    return layer;
  }

  /**
   * Sets the icon on the LayerItemPane to match that of the LayerType. Using String interpolation.
   */
  void setTypeIcon() {

    //TODO Fix themes
    String path = "images/light/" + layer.getLayerType().name().toLowerCase()
        + ".png";

    try {
      layerTypeIcon.setImage(new Image(Resources.find(getClass(), path).toURI().toString()));
    } catch (Exception e) {
      System.err.println("Failed to load layerTypeIcon icon! Exception: " + e);
    }
  }
}
