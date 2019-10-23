package chalmers.pimp.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class RasterDataServiceTest {

  @Test
  void createPixelDataCopy() {
    assertThrows(NullPointerException.class, () -> RasterDataService.createPixelDataCopy(null));
  }

  @Test
  void toFXImage() {
    assertThrows(NullPointerException.class, () -> RasterDataService.toFXImage(null));
  }
}