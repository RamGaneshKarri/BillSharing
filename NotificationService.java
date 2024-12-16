public class NotificationService {
    public void notifyUser(User user, String message) {
        System.out.println("Notification to " + user.getName() + " (" + user.getEmail() + "): " + message);
    }
}
