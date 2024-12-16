public class User {
    private String userId;
    private String name;
    private String email;
    private String bankDetails;

    // Default constructor
    public User() {
    }

    // Setters for each field
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBankDetails(String bankDetails) {
        this.bankDetails = bankDetails;
    }

    // Getters for each field
    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBankDetails() {
        return bankDetails;
    }
}
