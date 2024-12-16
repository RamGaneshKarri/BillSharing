import java.util.HashMap;
import java.util.Map;

public class Expense {
    private String expenseId;
    private String description;
    private String state;
    private String createdBy;
    private Map<User, Double> pendingAmounts;
    private SplitStrategy splitStrategy;

    public Expense() {
        this.state = "Created";
        this.pendingAmounts = new HashMap<>();
    }

    // Setters and Getters
    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setSplitStrategy(SplitStrategy splitStrategy) {
        this.splitStrategy = splitStrategy;
    }

    public String getState() {
        return state;
    }

    public void calculateAndSetSplits(double totalAmount, ExpenseGroup group) {
        if (splitStrategy != null) {
            this.pendingAmounts = splitStrategy.calculateSplit(totalAmount, group);
            this.state = "Pending";
        } else {
            throw new IllegalStateException("No split strategy set for the expense.");
        }
    }

    public Map<User, Double> getPendingAmounts() {
        return pendingAmounts;
    }

    public String getFormattedPendingAmounts() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<User, Double> entry : pendingAmounts.entrySet()) {
            result.append(entry.getKey().getName())
                    .append(": $")
                    .append(String.format("%.2f", entry.getValue()))
                    .append("\n");
        }
        return result.toString();
    }
}
