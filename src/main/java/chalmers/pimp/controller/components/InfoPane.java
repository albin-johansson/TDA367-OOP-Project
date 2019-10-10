package chalmers.pimp.controller.components;

import chalmers.pimp.controller.ControllerUtils;
import chalmers.pimp.util.Resources;
import java.io.IOException;
import javafx.scene.layout.AnchorPane;

public class InfoPane extends AnchorPane {

  InfoPane() throws IOException {
    ControllerUtils.makeController(this, Resources.find(getClass(), "info_pane.fxml"));
  }
}
