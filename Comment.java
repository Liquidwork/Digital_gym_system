/**
 * This {@code Comment} class will record a single comment message under a
 * specific video.
 */
public class Comment {
    User author;
    String comment;

    /**
     * Construct a new comment with full parameters.
     * @param author author of the comment
     * @param comment comment text
     */
    public Comment(User author, String comment) {
        this.author = author;
        this.comment = comment;
    }

    /**
     * Get the author of this comment
     * @return author in {@link User} class
     */
    public User getAuthor() {
        return this.author;
    }

    /**
     * Get the this comment text
     * @return comment text in {@link String} class
     */
    public String getComment() {
        return this.comment;
    }

    @Override
    public String toString() {
        return "Comment:{" +
            " author='" + getAuthor() + "'" +
            ", comment='" + getComment() + "'" +
            "}";
    }

}
