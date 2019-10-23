package chalmers.pimp.util;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class AnchorPanesTest {

  @Test
  void setAnchors() {
    assertThrows(NullPointerException.class, () -> AnchorPanes.setAnchors(null, 1, 1, 1, 1));
  }

  @Test
  void setZeroAnchors() {
    assertThrows(NullPointerException.class, () -> AnchorPanes.setZeroAnchors(null));
  }
}