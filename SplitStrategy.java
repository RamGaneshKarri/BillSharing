import java.util.Map;

public interface SplitStrategy {
    Map<User, Double> calculateSplit(double totalAmount, ExpenseGroup group);
}
