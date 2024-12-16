import java.util.HashMap;
import java.util.Map;

public class ExpenseManager {
    private Map<String, User> users;
    private Map<String, ExpenseGroup> groups;

    public ExpenseManager() {
        this.users = new HashMap<>();
        this.groups = new HashMap<>();
    }

    public void registerUser(String userId, String name, String email) {
        users.put(userId, new User(userId, name, email));
    }

    public ExpenseGroup createGroup(String groupId, String name) {
        ExpenseGroup group = new ExpenseGroup(groupId, name);
        groups.put(groupId, group);
        return group;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public Map<String, ExpenseGroup> getGroups() {
        return groups;
    }
}
