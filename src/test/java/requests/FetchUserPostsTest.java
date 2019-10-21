package requests;

import hook.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import responseModels.PostsResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static utilities.TestUtilities.*;

public class FetchUserPostsTest extends TestBase {

    List<PostsResponse> Postlist;
    @BeforeTest
    public void setBaseURI() throws IOException {
        initializeBaseURI();
    }

    @Test
    public void when_getPostByUserIdIsCalledWithValidUserId_expect_HeaderContentTypeToBeApplicationJson() {
        Response response = postsByUserId(3);

        Assert.assertEquals("application/json; charset=utf-8", response.contentType()); //Validate Content Type from response
    }

    @Test
    public void when_getPostByUserIdIsCalledWithValidUserId_expect_HTTPStatusCode200() {

        Response response = postsByUserId(3);

        Assert.assertEquals(200, response.statusCode()); //Validate HTTP Status Code from response
    }

    @Test
    public void when_getPostByUserIdIsCalledWithValidUserId_expect_ArrayIsNotEmpty() {
        Response response = postsByUserId(3);

        Postlist = Arrays.asList(response.as(PostsResponse[].class));
        Assert.assertTrue(!Postlist.isEmpty());
    }

    @Test
    public void when_getPostByUserIdIsCalledWithValidUserId_expect_OnlySpecifiedUserPostsAreReturned() {
        Response response = postsByUserId(3);

        Postlist = Arrays.asList(response.as(PostsResponse[].class));

        for (int i = 0; i < Postlist.size(); i++) {
            Assert.assertEquals(Postlist.get(i).getUserId(), 3);
        }
    }

    @Test
    public void when_getPostByUserIdIsCalledWithInvalidUserId_expect_EmptyResult() {
        Response response = postsByUserId(0);

        Postlist = Arrays.asList(response.as(PostsResponse[].class));
        Assert.assertTrue(Postlist.isEmpty());
    }
}
