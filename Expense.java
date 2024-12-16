import java.util.HashMap;
import java.util.Map;

public class Expense {
    private String expenseId;
    private String description;
    private String createdBy;
    private Map<User, Double> pendingAmounts = new HashMap<>();
    private Map<User, Double> contributions = new HashMap<>();
    private String state = "Created"; // Initial state

    // Default constructor
    public Expense() {
    }

    // Setters
    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setState(String state) {
        this.state = state;
    }

    // Getters
    public String getExpenseId() {
        return expenseId;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getState() {
        return state;
    }

    public Map<User, Double> getPendingAmounts() {
        return pendingAmounts;
    }

    public void addBifurcation(User user, double amount) {
        pendingAmounts.put(user, amount);
    }

    public void addContribution(User user, double amount) {
        double remaining = pendingAmounts.getOrDefault(user, 0.0) - amount;
        pendingAmounts.put(user, Math.max(0, remaining));
        contributions.put(user, contributions.getOrDefault(user, 0.0) + amount);

        // Check if expense is settled
        boolean allSettled = pendingAmounts.values().stream().allMatch(v -> v == 0);
        if (allSettled) {
            setState("Settled");
        }
    }
}
