import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();

        // Register users
        System.out.print("Enter the number of users to register: ");
        int userCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < userCount; i++) {
            User user = new User();
            System.out.print("Enter User ID: ");
            user.setUserId(scanner.nextLine());

            System.out.print("Enter Name: ");
            user.setName(scanner.nextLine());

            System.out.print("Enter Email: ");
            user.setEmail(scanner.nextLine());

            manager.registerUser(user.getUserId(), user.getName(), user.getEmail());
        }

        // Create group
        System.out.print("Enter Group ID: ");
        String groupId = scanner.nextLine();

        System.out.print("Enter Group Name: ");
        String groupName = scanner.nextLine();

        ExpenseGroup group = manager.createGroup(groupId, groupName);

        // Add users to the group
        System.out.print("Enter User IDs to add to the group (comma-separated): ");
        String[] userIds = scanner.nextLine().split(",");
        for (String userId : userIds) {
            group.addMember(manager.getUsers().get(userId.trim()));
        }

        // Create an expense
        System.out.print("Enter Expense ID: ");
        String expenseId = scanner.nextLine();

        System.out.print("Enter Expense Description: ");
        String description = scanner.nextLine();

        Expense expense = new Expense();
        expense.setExpenseId(expenseId);
        expense.setDescription(description);
        expense.setCreatedBy(userIds[0].trim()); // First user is the creator

        // Add bifurcation
        Map<User, Double> bifurcation = new HashMap<>();
        for (String userId : userIds) {
            User user = manager.getUsers().get(userId.trim());
            System.out.print("Enter amount for " + user.getName() + ": ");
            bifurcation.put(user, Double.parseDouble(scanner.nextLine()));
        }

        manager.addExpenseToGroup(group, expense, bifurcation);

        // Make contributions
        for (User user : bifurcation.keySet()) {
            System.out.print("Enter contribution for " + user.getName() + ": ");
            double contribution = Double.parseDouble(scanner.nextLine());
            manager.contributeToExpense(expense, user, contribution);
        }

        // Display final expense state
        System.out.println("\nFinal Expense State: " + expense.getState());
        System.out.println("Pending Amounts: " + expense.getPendingAmounts());

        scanner.close();
    }
}
