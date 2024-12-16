import java.util.HashMap;
import java.util.Map;

public class ExactSplit implements SplitStrategy {
    private final Map<User, Double> exactAmounts;

    public ExactSplit(Map<User, Double> exactAmounts) {
        this.exactAmounts = exactAmounts;
    }

    @Override
    public Map<User, Double> calculateSplit(double totalAmount, ExpenseGroup group) {
        double totalExact = exactAmounts.values().stream().mapToDouble(Double::doubleValue).sum();
        if (Math.abs(totalAmount - totalExact) > 0.01) {
            throw new IllegalArgumentException("The exact amounts do not match the total amount.");
        }
        return new HashMap<>(exactAmounts);
    }
}
