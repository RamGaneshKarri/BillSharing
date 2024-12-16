import java.util.ArrayList;
import java.util.List;

public class ExpenseGroup {
    private String groupId;
    private String name;
    private List<User> members = new ArrayList<>();
    private List<Expense> expenses = new ArrayList<>();

    // Parameterized constructor
    public ExpenseGroup(String groupId, String name) {
        this.groupId = groupId;
        this.name = name;
    }

    // Getters
    public String getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    public List<User> getMembers() {
        return members;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    // Methods
    public void addMember(User user) {
        members.add(user);
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }
}
