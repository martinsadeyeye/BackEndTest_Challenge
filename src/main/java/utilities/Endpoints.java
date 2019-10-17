package utilities;

public interface Endpoints {

    String GET_SEARCHUSERS = "/users";
    String GET_FETCHPOSTS = "/posts";
    String GET_FETCHCOMMENTS = "/comments";
    String GET_FETCHPOST_WITHID = "/posts/3";
    String GET_FETCHPOST_WITHUSERID = "/posts?userId=1";
    String GET_FETCHCOMMENT_WITHPOSTID = "/comments?postId=1";
    String GET_FETCHCOMMENT_WITHPID = "/posts/1/comments";

}
