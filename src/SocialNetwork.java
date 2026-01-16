public class SocialNetwork {
    private String name;
    private int totalUsers;

    public SocialNetwork(String name, int totalUsers) {
        this.name = name;
        this.totalUsers = totalUsers;
    }

    public String getName() { return name; }
    public int getTotalUsers() { return totalUsers; }

    public void setName(String name) { this.name = name; }
    public void setTotalUsers(int totalUsers) { this.totalUsers = totalUsers; }

    public void registerUser() {
        this.totalUsers++;
    }

    @Override
    public String toString() {
        return "SocialNetwork{name='" + name + "', totalUsers=" + totalUsers + "}";
    }
}
