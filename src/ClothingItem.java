import java.time.LocalDate;

public record ClothingItem(String name, String fabricType, String manufacturingCity,
                           LocalDate manufacturingDate, int price) {
}
