import java.util.*;
import java.util.stream.Collectors;

/**
 * Utility class for analyzing clothing item prices and identifying outliers.
 */
public class PriceAnalysis {

  /**
   * Analyzes clothing item prices to find data and outliers.
   *
   * @param clothingItems List of clothes.
   * @return A map with counts of data and outliers.
   */
  public static Map<String, Long> analyzePrices(List<ClothingItem> clothingItems) {
    List<Integer> prices = clothingItems.stream().map(ClothingItem::price).sorted().toList();

    double Q1 = prices.get((int) Math.ceil(0.25 * prices.size()) - 1);
    double Q3 = prices.get((int) Math.ceil(0.75 * prices.size()) - 1);
    double IQR = Q3 - Q1;

    Map<Boolean, Long> result = clothingItems.stream().collect(Collectors.partitioningBy(
            item -> item.price() >= Q1 - 1.5 * IQR && item.price() <= Q3 + 1.5 * IQR,
            Collectors.counting()
    ));

    Map<String, Long> finalResult = new HashMap<>();
    finalResult.put("data", result.getOrDefault(true, 0L));
    finalResult.put("outliers", result.getOrDefault(false, 0L));

    return finalResult;
  }
}
