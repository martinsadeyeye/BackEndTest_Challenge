package requests;

import hook.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import static org.hamcrest.Matchers.equalTo;
import static utilities.TestUtilities.*;

public class FetchUserPostCommentsTest extends TestBase {

    @BeforeTest
    public void setBaseURI() throws IOException {
        initializeBaseURI();
    }

    @Test
    public void when_getCommentIsCalled_expect_HTTPStatusCode200() {

        Response response = arrayListOfComments();

        response.
                then().
                assertThat().statusCode(200). //Verify HTTP Status Code from response
                extract().
                response();

        Assert.assertEquals(200, response.statusCode()); //Validate HTTP Status Code from response
    }

    @Test
    public void when_getCommentIsCalled_expect_HeaderContentTypeToBeApplicationJson() {
        Response response = arrayListOfComments();

        response.
                then().
                assertThat().header("Content-Type", "application/json; charset=utf-8").
                extract().
                response();
    }

    @Test
    public void when_getCommentIsCalled_expect_ArrayIsNotEmpty() {

    }

    @Test
    public void when_getCommentIsCalled_expect_ListOfnamesInComments() {
        Response response = arrayListOfComments();

        String Names = response.jsonPath().getString("name");
        System.out.println(Names);
    }

    @Test
    public void when_getCommentIsCalled_expect_ListOfPostId() {

        Response response = arrayListOfComments();

        String PostIds = response.jsonPath().getString("postId");
        System.out.println(PostIds);
    }

    @Test
    public void when_getCommentIsCalled_expect_ListOfEmail() {

        Response response = arrayListOfComments();

        String Emails = response.jsonPath().getString("email");
        System.out.println(Emails);

    }

    @Test
    public void when_getCommentWithUniquePostIdIsCalled_expect_ListofCommentswithSamePostId() {

        Response response = commentsForSpecificPostId();
        String PostIds = response.jsonPath().getString("postId");
        System.out.println(PostIds);
    }

    @Test
    public void when_getCommentWithUniqueIdIsCalled_expect_SingledatawithSpecifiedIdComments() {
        Response response = commentsForAsingleUser(3);

        String Id = response.jsonPath().getString("id");
        System.out.println(Id);

        response.
                then().
                assertThat().body("id", equalTo(3)).
                extract().
                response();
       // Assert.assertEquals("Nikita@garfield.biz",);
    }

    @Test
    public void when_getCommentIsCalled_expect_validEmailFormat() {
        Response response = commentsForAsingleUser(3);

    }

    @Test
    public void when_getCommentIsCalledWithId_expect_emailwithatsign() {
        Response response = commentsForAsingleUser(3);


    }


}
