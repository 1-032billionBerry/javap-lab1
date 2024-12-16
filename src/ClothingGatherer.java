import java.util.Optional;
import java.util.stream.Gatherer;

/**
 * A custom gatherer that skips a specified number of ClothingItems from a given manufacturing city.
 */
class ClothingGatherer implements Gatherer<ClothingItem, Optional<ClothingItem>, ClothingItem> {

  private int skipCount;
  private final String skipCity;

  public ClothingGatherer(int skipCount, String skipCity) {
    this.skipCount = skipCount;
    this.skipCity = skipCity;
  }

  @Override
  public Integrator<Optional<ClothingItem>, ClothingItem, ClothingItem> integrator() {
    return Gatherer.Integrator.of((_, element, downstream) -> {
      if (element.manufacturingCity().equals(skipCity) && skipCount > 0) {
        skipCount--;
        return true;
      }
      return downstream.push(element);
    });
  }
}