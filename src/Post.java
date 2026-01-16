import java.util.Objects;

public class Post {
    private int postId;
    private String content;
    private int likes;
    private int userId; // author id

    public Post(int postId, String content, int likes, int userId) {
        this.postId = postId;
        this.content = content;
        this.likes = likes;
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public String getContent() {
        return content;
    }

    public int getLikes() {
        return likes;
    }

    public int getUserId() {
        return userId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void like() {
        this.likes++;
    }

    public void printInfo() {
        System.out.println("Post{id=" + postId + ", userId=" + userId +
                ", content='" + content + "', likes=" + likes + "}");
    }

    public boolean hasMoreLikesThan(Post other) {
        return this.likes > other.likes;
    }

    @Override
    public String toString() {
        return "Post{id=" + postId + ", userId=" + userId +
                ", content='" + content + "', likes=" + likes + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return postId == post.postId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId);
    }
}
