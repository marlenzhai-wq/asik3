import java.util.Objects;

public class Profile {
    private String username;
    private String bio;
    private int followers;

    public Profile(String username, String bio, int followers) {
        this.username = username;
        this.bio = bio;
        this.followers = followers;
    }

    public String getUsername() { return username; }
    public String getBio() { return bio; }
    public int getFollowers() { return followers; }

    public void setUsername(String username) { this.username = username; }
    public void setBio(String bio) { this.bio = bio; }
    public void setFollowers(int followers) { this.followers = followers; }

    public void follow() {
        this.followers++;
    }

    public boolean hasMoreFollowersThan(Profile other) {
        return this.followers > other.followers;
    }

    @Override
    public String toString() {
        return "Profile{username='" + username + "', bio='" + bio + "', followers=" + followers + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile)) return false;
        Profile profile = (Profile) o;
        return Objects.equals(username, profile.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
