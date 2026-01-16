public class Main {
    public static void main(String[] args) {

        SocialNetwork sn = new SocialNetwork("ChatNet", 1000000);
        System.out.println(sn);

        Profile p1 = new Profile("marlen", "Java developer", 1200);
        Profile p2 = new Profile("aidana", "Designer", 3000);

        System.out.println(p1);
        System.out.println(p2);

        // Compare profiles
        if (p1.hasMoreFollowersThan(p2)) {
            System.out.println(p1.getUsername() + " has more followers than " + p2.getUsername());
        } else {
            System.out.println(p2.getUsername() + " has more followers than " + p1.getUsername());
        }

        Post post1 = new Post("Hello world!", p1.getUsername(), 10);
        Post post2 = new Post("New design uploaded!", p2.getUsername(), 50);

        System.out.println(post1);
        System.out.println(post2);

        // Compare posts
        if (post1.hasMoreLikesThan(post2)) {
            System.out.println("Post 1 has more likes.");
        } else {
            System.out.println("Post 2 has more likes.");
        }

        // Actions
        p1.follow();
        post1.like();

        System.out.println("\nAfter actions:");
        System.out.println(p1);
        System.out.println(post1);
    }
}
