package chalmers.pimp.util;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class AnchorPanesTest {

  @Test
  void setAnchors() {
//    AnchorPane temp = new AnchorPane();
//    double bottom = AnchorPane.getBottomAnchor(temp);
//    double left = AnchorPane.getLeftAnchor(temp);
//    double right = AnchorPane.getRightAnchor(temp);
//    double top = AnchorPane.getTopAnchor(temp);
//    assertEquals(0, bottom + top + right + left);
    assertThrows(NullPointerException.class, () -> AnchorPanes.setAnchors(null, 1, 1, 1, 1));
//    AnchorPanes.setAnchors(temp, 1,1,1,1);
//    bottom = AnchorPane.getBottomAnchor(temp);
//    left = AnchorPane.getLeftAnchor(temp);
//    right = AnchorPane.getRightAnchor(temp);
//    top = AnchorPane.getTopAnchor(temp);
//    assertEquals(4,  bottom + top + right + left);
  }
}