package responseModels;


public class CommentsResponse {

    public int getPostId() {
        return postId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }

    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;



}