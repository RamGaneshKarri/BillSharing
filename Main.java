import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();

        // Register users
        System.out.print("Enter the number of users to register: ");
        int userCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < userCount; i++) {
            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine();
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            manager.registerUser(userId, name, email);
        }

        // Create group
        System.out.print("\nEnter Group ID: ");
        String groupId = scanner.nextLine();
        System.out.print("Enter Group Name: ");
        String groupName = scanner.nextLine();
        ExpenseGroup group = manager.createGroup(groupId, groupName);

        // Add users to group
        System.out.print("Enter User IDs to add to the group (comma-separated): ");
        String[] userIds = scanner.nextLine().split(",");
        for (String userId : userIds) {
            group.addMember(manager.getUsers().get(userId.trim()));
        }

        // Create expense
        System.out.print("\nEnter Expense ID: ");
        String expenseId = scanner.nextLine();
        System.out.print("Enter Expense Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Total Expense Amount: ");
        double totalAmount = Double.parseDouble(scanner.nextLine());

        Expense expense = new Expense();
        expense.setExpenseId(expenseId);
        expense.setDescription(description);

        // Choose split strategy
        System.out.println("\nChoose Split Strategy:");
        System.out.println("1. Equal Split");
        System.out.println("2. Exact Split");
        System.out.println("3. Percent Split");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> expense.setSplitStrategy(new EqualSplit());
            case 2 -> {
                Map<User, Double> exactSplits;
                while (true) {
                    exactSplits = new HashMap<>();
                    double sum = 0;
                    for (User user : group.getMembers()) {
                        System.out.print("Enter exact amount for " + user.getName() + ": ");
                        double amount = Double.parseDouble(scanner.nextLine());
                        exactSplits.put(user, amount);
                        sum += amount;
                    }
                    if (Math.abs(totalAmount - sum) > 0.01) {
                        System.out.println("The exact amounts do not match the total amount. Please try again.");
                    } else {
                        break;
                    }
                }
                expense.setSplitStrategy(new ExactSplit(exactSplits));
            }
            case 3 -> {
                Map<User, Double> percentSplits = new HashMap<>();
                double sum = 0;
                for (User user : group.getMembers()) {
                    System.out.print("Enter percentage for " + user.getName() + ": ");
                    double percentage = Double.parseDouble(scanner.nextLine());
                    percentSplits.put(user, percentage);
                    sum += percentage;
                }
                if (Math.abs(sum - 100) > 0.01) {
                    System.out.println("The percentages do not add up to 100%. Exiting.");
                    return;
                }
                expense.setSplitStrategy(new PercentSplit(percentSplits));
            }
            default -> throw new IllegalArgumentException("Invalid choice.");
        }

        // Calculate and set splits
        expense.calculateAndSetSplits(totalAmount, group);

        // Display pending amounts
        System.out.println("\nPending Amounts:");
        System.out.println(expense.getFormattedPendingAmounts());

        scanner.close();
    }
}
