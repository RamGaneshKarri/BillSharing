import java.util.HashMap;
import java.util.Map;

public class ExpenseManager {
    private Map<String, User> users = new HashMap<>();
    private Map<String, ExpenseGroup> groups = new HashMap<>();
    private NotificationService notificationService = new NotificationService();

    // User Management
    public void registerUser(String userId, String name, String email) {
        if (!users.containsKey(userId)) {
            User user = new User();
            user.setUserId(userId);
            user.setName(name);
            user.setEmail(email);
            users.put(userId, user);
        }
    }

    public Map<String, User> getUsers() {
        return users;
    }

    // Group Management
    public ExpenseGroup createGroup(String groupId, String name) {
        ExpenseGroup group = new ExpenseGroup(groupId, name);
        groups.put(groupId, group);
        return group;
    }

    public void addExpenseToGroup(ExpenseGroup group, Expense expense, Map<User, Double> bifurcation) {
        for (Map.Entry<User, Double> entry : bifurcation.entrySet()) {
            expense.addBifurcation(entry.getKey(), entry.getValue());
            notificationService.notifyUser(entry.getKey(),
                    "You have been added to an expense: " + expense.getDescription());
        }
        group.addExpense(expense);
        expense.setState("Pending");
    }

    public void contributeToExpense(Expense expense, User user, double amount) {
        expense.addContribution(user, amount);
        if ("Settled".equals(expense.getState())) {
            System.out.println("Expense " + expense.getExpenseId() + " is now Settled!");
        }
    }
}
