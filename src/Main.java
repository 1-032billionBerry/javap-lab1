import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The main class that demonstrates filtering, grouping, statistical analysis,
 * and price analysis of a list of clothes.
 */
public class Main {

  public static void main(String[] args) {
    int N = 10;

    // Generate a list of clothing items
    List<ClothingItem> clothingItems = ClothingGenerator.generateClothingItems()
            .gather(new ClothingGatherer(N, "Lviv"))
            .limit(500)
            .toList();

    // Print all clothing items
    clothingItems.forEach(System.out::println);

    // Group clothing items by fabric type and filter by manufacturing date
    Map<String, List<ClothingItem>> groupedClothingItems = clothingItems.stream()
            .filter(item -> item.manufacturingDate().isAfter(LocalDate.now().minusMonths(60)))
            .collect(Collectors.groupingBy(ClothingItem::fabricType));

    groupedClothingItems.forEach((key, value) -> System.out.println(key + ": " + value.size()));

    // Calculate and print clothing statistics
    ClothingStatistics statistics = clothingItems.stream().collect(new ClothingStatisticsCollector());
    System.out.println(statistics);

    // Analyze and print price analysis
    Map<String, Long> priceAnalysis = PriceAnalysis.analyzePrices(clothingItems);
    System.out.println(priceAnalysis);
  }
}