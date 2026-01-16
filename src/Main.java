import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try (Connection con = DBUtil.connect()) {
            System.out.println("✅ Connected to PostgreSQL!");

            // --------- WRITE (INSERT) ----------
            int newUserId = insertUser(con, "TestUser99");
            int newPostId = insertPost(con, newUserId, "This post was inserted from Java", 0);
            int newCommentId = insertComment(con, newPostId, newUserId, "Nice!");

            // --------- READ (SELECT) ----------
            System.out.println("\n=== USERS ===");
            printUsers(con);

            System.out.println("\n=== POSTS ===");
            printPosts(con);

            System.out.println("\n=== COMMENTS ===");
            printComments(con);

            // --------- UPDATE ----------
            System.out.println("\n=== UPDATE ===");
            updatePostLikes(con, newPostId, 10);
            updateCommentText(con, newCommentId, "Updated comment from Java!");

            // --------- DELETE ----------
            System.out.println("\n=== DELETE ===");
            deleteComment(con, newCommentId);
            deletePost(con, newPostId);
            deleteUser(con, newUserId); // user өшсе, өз пост/коммент бар болса FK-ға байланысты қате болуы мүмкін

            // қайта оқу
            System.out.println("\n=== AFTER CHANGES ===");
            printUsers(con);
            printPosts(con);
            printComments(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------------- USERS ----------------
    static int insertUser(Connection con, String username) throws SQLException {
        String sql = "INSERT INTO users(username) VALUES (?) RETURNING user_id";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            rs.next();
            System.out.println("Inserted user: " + username);
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
            System.out.println("Deleted user id=" + userId);
        }
    }

    // ---------------- POSTS ----------------
    static int insertPost(Connection con, int userId, String content, int likes) throws SQLException {
        String sql = "INSERT INTO posts(user_id, content, likes) VALUES (?, ?, ?) RETURNING post_id";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setString(2, content);
            ps.setInt(3, likes);
            ResultSet rs = ps.executeQuery();
            rs.next();
            System.out.println("Inserted post for user_id=" + userId);
            return rs.getInt(1);
        }
    }

    static void printPosts(Connection con) throws SQLException {
        String sql = "SELECT post_id, user_id, content, likes FROM posts ORDER BY post_id";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(
                        rs.getInt("post_id") + " | user=" + rs.getInt("user_id") +
                                " | likes=" + rs.getInt("likes") +
                                " | " + rs.getString("content")
                );
            }
        }
    }

    static void updatePostLikes(Connection con, int postId, int newLikes) throws SQLException {
        String sql = "UPDATE posts SET likes = ? WHERE post_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, newLikes);
            ps.setInt(2, postId);
            ps.executeUpdate();
            System.out.println("Updated likes for post_id=" + postId);
        }
    }

    static void deletePost(Connection con, int postId) throws SQLException {
        String sql = "DELETE FROM posts WHERE post_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, postId);
            ps.executeUpdate();
            System.out.println("Deleted post id=" + postId);
        }
    }

    // ---------------- COMMENTS ----------------
    static int insertComment(Connection con, int postId, int userId, String text) throws SQLException {
        String sql = "INSERT INTO comments(post_id, user_id, comment) VALUES (?, ?, ?) RETURNING comment_id";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, postId);
            ps.setInt(2, userId);
            ps.setString(3, text);
            ResultSet rs = ps.executeQuery();
            rs.next();
            System.out.println("Inserted comment for post_id=" + postId);
            return rs.getInt(1);
        }
    }

    static void printComments(Connection con) throws SQLException {
        String sql = "SELECT comment_id, post_id, user_id, comment FROM comments ORDER BY comment_id";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(
                        rs.getInt("comment_id") + " | post=" + rs.getInt("post_id") +
                                " | user=" + rs.getInt("user_id") +
                                " | " + rs.getString("comment")
                );
            }
        }
    }

    static void updateCommentText(Connection con, int commentId, String newText) throws SQLException {
        String sql = "UPDATE comments SET comment = ? WHERE comment_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, newText);
            ps.setInt(2, commentId);
            ps.executeUpdate();
            System.out.println("Updated comment_id=" + commentId);
        }
    }

    static void deleteComment(Connection con, int commentId) throws SQLException {
        String sql = "DELETE FROM comments WHERE comment_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, commentId);
            ps.executeUpdate();
            System.out.println("Deleted comment id=" + commentId);
        }
    }
}
