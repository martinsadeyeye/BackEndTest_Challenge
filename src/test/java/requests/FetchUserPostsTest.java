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
    public void when_getPostIsCalled_expect_HeaderContentTypeToBeApplicationJson() {
        Response response = arrayListOfposts();

        response.
                then().
                assertThat().header("Content-Type", "application/json; charset=utf-8").
                extract().
                response();
    }

    @Test
    public void when_getPostIsCalled_expect_HTTPStatusCode200() {

        Response response = arrayListOfposts();

        response.
                then().
                assertThat().statusCode(200). //Verify HTTP Status Code from response
                extract().
                response();

        Assert.assertEquals(200, response.statusCode()); //Validate HTTP Status Code from response
    }

    @Test
    public void when_getPostIsCalled_expect_ArrayIsNotEmpty() {
        Response response = arrayListOfposts();

        Postlist = Arrays.asList(response.as(PostsResponse[].class));
        Assert.assertTrue(!Postlist.isEmpty());
    }

    @Test
    public void when_getPostIsCalled_expect_ListOfPostsSizeToBe100() {

        Response response = arrayListOfposts();

        Postlist = Arrays.asList(response.as(PostsResponse[].class));
        Assert.assertEquals(100, Postlist.size());
    }


    @Test
    public void when_getPostWithUniqueId3IsCalled_expect_postforUserWithId3() {

        Response response = postForSpecificId(3);

        PostsResponse listofAllPost = response.as(PostsResponse.class);

        //Get title from response
        String title;
        title = listofAllPost.getTitle();

        //Verify that the title is Samantha
        Assert.assertEquals(title, "ea molestias quasi exercitationem repellat qui ipsa sit aut");
    }

}
