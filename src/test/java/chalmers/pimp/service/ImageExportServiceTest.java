package chalmers.pimp.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ImageExportServiceTest {

  @Test
  void exportImage() {
    assertThrows(IllegalArgumentException.class, () -> ImageExportService.exportImage(0, 0, null));
    assertThrows(NullPointerException.class, () -> ImageExportService.exportImage(1, 1, null));
  }
}