/**
 * {@code Video} class provide information about a video with its id, author, path and description. 
 */
public class Video {
    private int id;
    private User author;
    private String title;
    private String videoPath;
    private String description;

    /**
     * Construct a new {@code Video} class with all fields in it.
     * @param id
     * @param author
     * @param title
     * @param videoPath
     * @param description
     */
    public Video(int id, User author, String videoPath, String description) {
        this.id = id;
        this.author = author;
        this.videoPath = videoPath;
        this.description = description;
    }

    /**
     * Get the id of video
     * @return id of video
     */
    public int getId() {
        return this.id;
    }
    /**
     * Get the author of the video
     * @return {@link User} type author
     */
    public User getAuthor() {
        return this.author;
    }

    /**
     * 
     * @return
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Get the full path of the video.
     * @return {@link String} path of the video file 
     */
    public String getVideoPath() {
        return this.videoPath;
    }

    /**
     * Get the description of the video
     * @return {@link String} description of the video
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get a String with all field information
     */
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", author='" + getAuthor() + "'" +
            ", title='" + getTitle() + "'" +
            ", videoPath='" + getVideoPath() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }


}
