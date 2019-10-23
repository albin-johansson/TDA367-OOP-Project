package chalmers.pimp.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ImageImportServiceTest {

  @Test
  void importImage() {
    // We can't test it more than this, due to limitations with JavaFX
    assertThrows(NullPointerException.class, () -> ImageImportService.importImage(null));
  }
}