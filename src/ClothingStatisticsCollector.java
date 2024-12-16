import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collector;

/**
 * Collector for calculating basic statistics of ClothingItem prices.
 */
public class ClothingStatisticsCollector implements Collector<ClothingItem, List<Integer>, ClothingStatistics> {

  @Override
  public Supplier<List<Integer>> supplier() {
    return ArrayList::new;
  }

  @Override
  public BiConsumer<List<Integer>, ClothingItem> accumulator() {
    return (accumulator, item) -> accumulator.add(item.price());
  }

  @Override
  public BinaryOperator<List<Integer>> combiner() {
    return (list1, list2) -> {
      list1.addAll(list2);
      return list1;
    };
  }

  @Override
  public Function<List<Integer>, ClothingStatistics> finisher() {
    return prices -> {
      int min = prices.stream().min(Integer::compare).orElse(0);
      int max = prices.stream().max(Integer::compare).orElse(0);
      double avg = prices.stream().mapToDouble(Integer::doubleValue).average().orElse(0);
      double stdDeviation = Math.sqrt(prices.stream().mapToDouble(price -> Math.pow(price - avg, 2)).average().orElse(0));
      return new ClothingStatistics(min, max, avg, stdDeviation);
    };
  }

  @Override
  public Set<Characteristics> characteristics() {
    return Set.of(Characteristics.CONCURRENT);
  }
}
