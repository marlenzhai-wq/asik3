import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Connection con = DBUtil.connect()) {
            System.out.println("✅ Connected to PostgreSQL!");

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter username: ");
            String username = sc.nextLine();

            int userId = insertUser(con, username);
            System.out.println("✅ User inserted with id=" + userId);

            System.out.print("Enter post content: ");
            String postContent = sc.nextLine();

            System.out.print("Enter likes (number): ");
            int likes = sc.nextInt();
            sc.nextLine();

            int postId = insertPost(con, userId, postContent, likes);
            System.out.println("✅ Post inserted with id=" + postId);

            System.out.print("Enter comment text: ");
            String commentText = sc.nextLine();

            int commentId = insertComment(con, postId, userId, commentText);
            System.out.println("✅ Comment inserted with id=" + commentId);

            System.out.println("\n=== USERS ===");
            printUsers(con);

            System.out.println("\n=== POSTS ===");
            printPosts(con);

            System.out.println("\n=== COMMENTS ===");
            printComments(con);

            System.out.print("\nDo you want to update likes for this post? (yes/no): ");
            String answer = sc.nextLine();

            if (answer.equalsIgnoreCase("yes")) {
                System.out.print("Enter new likes: ");
                int newLikes = sc.nextInt();
                sc.nextLine();

                updatePostLikes(con, postId, newLikes);
                System.out.println("✅ Likes updated!");

                System.out.println("\n=== POSTS AFTER UPDATE ===");
                printPosts(con);
            }

            System.out.print("\nDo you want to delete inserted data? (yes/no): ");
            String del = sc.nextLine();

            if (del.equalsIgnoreCase("yes")) {
                deleteComment(con, commentId);
                deletePost(con, postId);
                deleteUser(con, userId);

                System.out.println("🗑️ Deleted inserted user, post and comment.");
            } else {
                System.out.println("✅ Data kept in database (not deleted).");
            }

            System.out.println("\n=== FINAL DATA ===");
            printUsers(con);
            printPosts(con);
            printComments(con);

            System.out.println("\n✅ Done.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static int insertUser(Connection con, String username) throws SQLException {
        String sql = "INSERT INTO users(username) VALUES (?) RETURNING user_id";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
    }

    static void printUsers(Connection con) throws SQLException {
        String sql = "SELECT user_id, username FROM users ORDER BY user_id";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getInt("user_id") + " | " + rs.getString("username"));
            }
        }
    }

    static void deleteUser(Connection con, int userId) throws SQLException {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        }
    }

    static int insertPost(Connection con, int userId, String content, int likes) throws SQLException {
        String sql = "INSERT INTO posts(user_id, content, likes) VALUES (?, ?, ?) RETURNING post_id";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setString(2, content);
            ps.setInt(3, likes);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
    }

    static void printPosts(Connection con) throws SQLException {
        String sql = "SELECT post_id, user_id, content, likes FROM posts ORDER BY post_id";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(
                        rs.getInt("post_id") +
                                " | user_id=" + rs.getInt("user_id") +
                                " | likes=" + rs.getInt("likes") +
                                " | " + rs.getString("content")
                );
            }
        }
    }

    static void updatePostLikes(Connection con, int postId, int newLikes) throws SQLException {
        String sql = "UPDATE posts SET likes=? WHERE post_id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, newLikes);
            ps.setInt(2, postId);
            ps.executeUpdate();
        }
    }

    static void deletePost(Connection con, int postId) throws SQLException {
        String sql = "DELETE FROM posts WHERE post_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, postId);
            ps.executeUpdate();
        }
    }

    static int insertComment(Connection con, int postId, int userId, String commentText) throws SQLException {
        String sql = "INSERT INTO comments(post_id, user_id, comment) VALUES (?, ?, ?) RETURNING comment_id";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, postId);
            ps.setInt(2, userId);
            ps.setString(3, commentText);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
    }

    static void printComments(Connection con) throws SQLException {
        String sql = "SELECT comment_id, post_id, user_id, comment FROM comments ORDER BY comment_id";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(
                        rs.getInt("comment_id") +
                                " | post_id=" + rs.getInt("post_id") +
                                " | user_id=" + rs.getInt("user_id") +
                                " | " + rs.getString("comment")
                );
            }
        }
    }

    static void deleteComment(Connection con, int commentId) throws SQLException {
        String sql = "DELETE FROM comments WHERE comment_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, commentId);
            ps.executeUpdate();
        }
    }
}
