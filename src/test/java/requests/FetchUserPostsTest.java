package requests;

import hook.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import responseModels.ListOfAllPostsSuccessResponse;
import responseModels.ListOfAllUsersSuccessResponse;
import responseModels.SpecificPostWithUserIdSuccessResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static utilities.TestUtilities.*;

public class FetchUserPostsTest extends TestBase {

    List<ListOfAllPostsSuccessResponse> Postlist;
    @BeforeTest
    public void setBaseURI() throws IOException {
        initializeBaseURI();
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
    public void when_getPostIsCalled_expect_HeaderContentTypeToBeApplicationJson() {
        Response response = arrayListOfposts();

        response.
                then().
                assertThat().header("Content-Type", "application/json; charset=utf-8").
                extract().
                response();
    }

    @Test
    public void when_getPostIsCalled_expect_ArrayIsNotEmpty() {
        Response response = arrayListOfposts();

        List<String> jsonResponse = response.jsonPath().getList("$");
        Assert.assertTrue(!jsonResponse.isEmpty());

    }

    @Test
    public void when_getPostIsCalled_expect_ListOfPostsSizeToBe10() {

        Response response = arrayListOfposts();

        List<String> jsonResponse = response.jsonPath().getList("$");
        Assert.assertEquals(100, jsonResponse.size());
    }

    @Test
    public void when_getPostIsCalled_expect_ListOfTitle() {
        Response response = arrayListOfposts();

        List<String> jsonResponse = response.jsonPath().getList("title");

        for (String post : jsonResponse) {
        }

        Assert.assertEquals("ea molestias quasi exercitationem repellat qui ipsa sit aut", jsonResponse.get(2));
    }

    @Test
    public void when_getPostIsCalled_expect_SamanthasIdshouldbeOntheList() {

        Response response = arrayListOfposts();

        Postlist = Arrays.asList(response.getBody().as(ListOfAllPostsSuccessResponse[].class));

        //Get id from response
        int id;
        id = Postlist.size();

        //Verify that the size is id in Post
        Assert.assertEquals(id, 100);
    }


    @Test
    public void when_getPostWithUniqueUserIdIsCalled_expect_ListofPostwithSameUserId() {

        Response response = postForSpecificUserId();
        List<SpecificPostWithUserIdSuccessResponse> specificpostwithUserId = Arrays.asList(response.getBody().as(SpecificPostWithUserIdSuccessResponse[].class));

        //Get id from response
        int userId;
        userId = specificpostwithUserId.size();

        //Verify that the size is UserId in Array
        Assert.assertEquals(userId, 10);
    }

    @Test
    public void when_getPostWithUniqueIdIsCalled_expect_SingleData() {

        Response response = postForSpecificId(3);

        ListOfAllPostsSuccessResponse listofAllPost = response.as(ListOfAllPostsSuccessResponse.class);

        //Get title from response
        String title;
        title = listofAllPost.getTitle();

        //Verify that the username is Samantha
        Assert.assertEquals(title, "ea molestias quasi exercitationem repellat qui ipsa sit aut");
    }


}
