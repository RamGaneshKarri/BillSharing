import java.util.HashMap;
import java.util.Map;

public class PercentSplit implements SplitStrategy {
    private final Map<User, Double> percentages;

    public PercentSplit(Map<User, Double> percentages) {
        this.percentages = percentages;
    }

    @Override
    public Map<User, Double> calculateSplit(double totalAmount, ExpenseGroup group) {
        Map<User, Double> splits = new HashMap<>();
        for (Map.Entry<User, Double> entry : percentages.entrySet()) {
            User user = entry.getKey();
            double percentage = entry.getValue();
            splits.put(user, (totalAmount * percentage) / 100);
        }
        return splits;
    }
}
