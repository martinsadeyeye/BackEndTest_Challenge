package requests;

import hook.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.Endpoints;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static utilities.TestUtilities.arrayListOfposts;
import static utilities.TestUtilities.postForSpecificId;

public class FetchUserPostsTest extends TestBase {


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

    }

    @Test
    public void when_getPostIsCalled_expect_ListOfPosts() {

    }

    @Test
    public void when_getPostIsCalled_expect_ListOfTitle() {

    }

    @Test
    public void when_getPostIsCalled_expect_ListOfUserId() {

    }

    @Test
    public void when_getPostIsCalled_expect_SamanthasId() {

    }

    @Test
    public void when_getPostWithUniqueUserIdIsCalled_expect_ListofPostwithSameUserId() {

    }

    @Test
    public void when_getPostWithUniqueIdIsCalled_expect_SingleData() {

        Response response = postForSpecificId(3);
        String Id = response.jsonPath().getString("id");
        System.out.println(Id);

        response.
                then().
                assertThat().body("title", equalTo("ea molestias quasi exercitationem repellat qui ipsa sit aut")).
                extract().
                response();
    }


}
