package util;

import java.util.Objects;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 * Utility class for AnchorPanes
 */
public final class AnchorPanes {

  private AnchorPanes() {
  }

  /**
   * Utility method to set Anchor points
   *
   * @param node   the node to have all it's Anchor's set
   * @param top    set value for top
   * @param right  set value for right
   * @param bottom set value for bottom
   * @param left   set value for left
   * @throws NullPointerException if the Node argument is null
   */
  public static void setAnchors(Node node, int top, int right, int bottom, int left) {
    Objects.requireNonNull(node);
    AnchorPane.setTopAnchor(node, (double) top);
    AnchorPane.setBottomAnchor(node, (double) bottom);
    AnchorPane.setLeftAnchor(node, (double) left);
    AnchorPane.setRightAnchor(node, (double) right);
  }
}
