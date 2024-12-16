import java.time.LocalDate;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Generates random ClothingItem objects using the Stream API.
 */
public class ClothingGenerator {

  private static final String[] NAMES = {"Skirt", "Coat", "Jeans", "Dress", "Hoodie"};
  private static final String[] FABRICS = {"Cotton", "Wool", "Silk", "Leather", "Nylon"};
  private static final String[] CITIES = {"Kyiv", "Lviv", "Vinnytsia", "Chernihiv", "Zaporizhzhia"};

  public static Stream<ClothingItem> generateClothingItems() {
    Random random = new Random();
    return Stream.generate(() -> new ClothingItem(
            NAMES[random.nextInt(NAMES.length)],
            FABRICS[random.nextInt(FABRICS.length)],
            CITIES[random.nextInt(CITIES.length)],
            LocalDate.now().minusMonths(random.nextInt(240)),
            105 + random.nextInt(1005)
    ));
  }
}
