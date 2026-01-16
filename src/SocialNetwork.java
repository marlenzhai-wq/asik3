import java.util.ArrayList;

public class SocialNetwork {
    private String name;

    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Post> posts = new ArrayList<>();
    private ArrayList<Comment> comments = new ArrayList<>();

    public SocialNetwork(String name) {
        this.name = name;
    }

    // Add methods
    public void addUser(User u) {
        users.add(u);
    }

    public void addPost(Post p) {
        posts.add(p);
    }

    public void addComment(Comment c) {
        comments.add(c);
    }

    // Print everything
    public void printAll() {
        System.out.println("=== Social Network: " + name + " ===");

        System.out.println("\nUSERS:");
        for (User u : users) {
            System.out.println(u);
        }

        System.out.println("\nPOSTS:");
        for (Post p : posts) {
            System.out.println(p);
        }

        System.out.println("\nCOMMENTS:");
        for (Comment c : comments) {
            System.out.println(c);
        }
    }
}
