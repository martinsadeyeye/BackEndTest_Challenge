package utilities;

public interface Endpoints {

    String GET_SEARCHUSERS = "/users";
    String GET_SEARCHUSERS_WITHID = "/users/{id}";
    String GET_FETCHPOSTS = "/posts";
    String GET_FETCHCOMMENTS = "/comments";
    String GET_FETCHPOST_WITHID = "/posts/{id}";
    String GET_FETCHPOST_WITHUSERID = "/posts";
    String GET_FETCHCOMMENT_WITHPOSTID = "/comments";
    String GET_FETCHCOMMENT_WITHPID = "/posts/1/comments";
    String GET_FETCHCOMMENTS_WITHID = "/comments/{id}";

}