import java.util.Objects;

public class Comment {
    private int commentId;
    private String comment;
    private int postId;
    private int userId;

    public Comment(int commentId, String comment, int postId, int userId) {
        this.commentId = commentId;
        this.comment = comment;
        this.postId = postId;
        this.userId = userId;
    }

    public int getCommentId() {
        return commentId;
    }

    public String getComment() {
        return comment;
    }

    public int getPostId() {
        return postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void printInfo() {
        System.out.println("Comment{id=" + commentId + ", postId=" + postId +
                ", userId=" + userId + ", text='" + comment + "'}");
    }

    @Override
    public String toString() {
        return "Comment{id=" + commentId + ", postId=" + postId +
                ", userId=" + userId + ", text='" + comment + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment c = (Comment) o;
        return commentId == c.commentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId);
    }
}
