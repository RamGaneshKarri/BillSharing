import java.util.ArrayList;
import java.util.List;

public class ExpenseGroup {
    private String groupId;
    private String name;
    private List<User> members;

    public ExpenseGroup(String groupId, String name) {
        this.groupId = groupId;
        this.name = name;
        this.members = new ArrayList<>();
    }

    public void addMember(User user) {
        members.add(user);
    }

    public List<User> getMembers() {
        return members;
    }
}
