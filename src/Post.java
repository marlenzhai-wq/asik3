import java.util.Objects;

public class Post {
    private String content;
    private String authorUsername;
    private int likes;

    public Post(String content, String authorUsername, int likes) {
        this.content = content;
        this.authorUsername = authorUsername;
        this.likes = likes;
    }

    public String getContent() { return content; }
    public String getAuthorUsername() { return authorUsername; }
    public int getLikes() { return likes; }

    public void setContent(String content) { this.content = content; }
    public void setAuthorUsername(String authorUsername) { this.authorUsername = authorUsername; }
    public void setLikes(int likes) { this.likes = likes; }

    public void like() {
        this.likes++;
    }

    public boolean hasMoreLikesThan(Post other) {
        return this.likes > other.likes;
    }

    @Override
    public String toString() {
        return "Post{author='" + authorUsername + "', content='" + content + "', likes=" + likes + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return Objects.equals(content, post.content) &&
                Objects.equals(authorUsername, post.authorUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, authorUsername);
    }
}
