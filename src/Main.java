public class Main {
    public static void main(String[] args) {

        SocialNetwork sn = new SocialNetwork("MySocialApp");

        // Users (сендегі таблица бойынша)
        User u1 = new User(1, "Marlen1280");
        User u2 = new User(2, "Dastan01");
        User u3 = new User(3, "Serik");

        sn.addUser(u1);
        sn.addUser(u2);
        sn.addUser(u3);

        // Posts (сендегі таблица бойынша)
        Post p1 = new Post(1, "Hello World!", 3, 1);
        Post p2 = new Post(2, "My first day at the University", 2, 2);
        Post p3 = new Post(3, "Today I went for a walk", 0, 3);

        sn.addPost(p1);
        sn.addPost(p2);
        sn.addPost(p3);

        // Comments (сендегі таблица бойынша)
        Comment c1 = new Comment(1, "Hi", 1, 2);
        Comment c2 = new Comment(2, "Hello", 1, 3);
        Comment c3 = new Comment(3, "Good luck", 2, 1);
        Comment c4 = new Comment(4, "Great", 3, 2);

        sn.addComment(c1);
        sn.addComment(c2);
        sn.addComment(c3);
        sn.addComment(c4);

        // Print
        sn.printAll();

        // Compare objects
        System.out.println("\n=== COMPARISON ===");
        System.out.println("Post 1 has more likes than Post 2? " + p1.hasMoreLikesThan(p2));

        System.out.println("User1 equals User2? " + u1.equals(u2));

        // Update with setters
        System.out.println("\n=== UPDATE USING SETTER ===");
        u1.setUsername("MarlenNewName");
        p3.like(); // likes +1

        System.out.println(u1);
        System.out.println(p3);
    }
}
