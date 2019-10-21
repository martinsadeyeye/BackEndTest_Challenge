package responseModels;


public class PostsResponse {

    private int userId;
    private int id;
    private String title;

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    private String body;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

}
