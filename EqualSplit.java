import java.util.HashMap;
import java.util.Map;

public class EqualSplit implements SplitStrategy {
    @Override
    public Map<User, Double> calculateSplit(double totalAmount, ExpenseGroup group) {
        Map<User, Double> splits = new HashMap<>();
        int memberCount = group.getMembers().size();
        double splitAmount = totalAmount / memberCount;

        for (User user : group.getMembers()) {
            splits.put(user, splitAmount);
        }

        return splits;
    }
}
