package requests;

import hook.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import responseModels.CommentsResponse;
import responseModels.PostsResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static utilities.TestUtilities.*;

public class FetchUserPostCommentsTest extends TestBase {

    @BeforeTest
    public void setBaseURI() throws IOException {
        initializeBaseURI();
    }

    @Test
    public void when_getCommentsIsCalled_expect_HeaderContentTypeToBeApplicationJson() {
        Response response = arrayListOfComments();

        Assert.assertEquals("application/json; charset=utf-8", response.contentType()); //Validate HTTP Status Code from response
    }

    @Test
    public void when_getCommentsIsCalled_expect_HTTPStatusCode200() {

        Response response = arrayListOfComments();

        Assert.assertEquals(200, response.statusCode()); //Validate HTTP Status Code from response
    }

    @Test
    public void when_getCommentsIsCalled_expect_ArrayIsNotEmpty() {

        Response response = arrayListOfComments();

        List<CommentsResponse> comments = Arrays.asList(response.as(CommentsResponse[].class));
        Assert.assertTrue(!comments.isEmpty());
    }

    @Test
    public void when_getCommentIsCalledWithPostId_expect_OnlyCommentsRelatedToSpecifiedPost() {
        Response response = commentsForSpecificPostId(1);

        List<CommentsResponse> comments = Arrays.asList(response.as(CommentsResponse[].class));

        for(int i = 0; i < comments.size(); i++) {
            Assert.assertEquals(comments.get(i).getPostId(), 1);
        }
    }

    @Test
    public void when_getCommentsIsCalled_expect_ResultCanBeFilteredByUser() {
        Response getUserPosts = postsByUserId(3); //All User's posts

        List<PostsResponse> userPosts = Arrays.asList(getUserPosts.as(PostsResponse[].class));

        List<Integer> postIds = new ArrayList<>();

        //Set User's Post IDs in an Array List
        for(int i = 0; i < userPosts.size(); i++) {
            postIds.add(userPosts.get(i).getId());
        }

        //Retrieve all comments related to User's post
        for(int postId : postIds) {
            Response getCommentsByPostId = commentsForSpecificPostId(postId);
            List<CommentsResponse> postComments = Arrays.asList(getCommentsByPostId.as(CommentsResponse[].class));

            //Verify email format
            for(int i = 0; i < postComments.size(); i++) {
                String email = postComments.get(i).getEmail();
                Assert.assertTrue(isEmailValid(email));
            }
        }

    }
}