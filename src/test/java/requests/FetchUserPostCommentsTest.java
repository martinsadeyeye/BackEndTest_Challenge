package requests;

import hook.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import responseModels.CommentsResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


import static utilities.TestUtilities.*;

public class FetchUserPostCommentsTest extends TestBase {

    List<CommentsResponse> commentsList;

    CommentsResponse comments;

    @BeforeTest
    public void setBaseURI() throws IOException {
        initializeBaseURI();
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
    public void when_getCommentIsCalled_expect_ArrayIsNotEmpty() {

        Response response = arrayListOfComments();

        commentsList = Arrays.asList(response.as(CommentsResponse[].class));
        Assert.assertTrue(!commentsList.isEmpty());
    }

    @Test
    public void when_getCommentWithUniquePostId1IsCalled_expect_ListofCommentswithSamePostId() {

        Response response = commentsForSpecificPostId();

        commentsList = Arrays.asList(response.as(CommentsResponse[].class));

        //Verify that the name on the comment
        Assert.assertEquals("odio adipisci rerum aut animi", commentsList.get(2).getName());

    }

    @Test
    public void when_getCommentWithUniqueIdIsCalled_expect_SingledatawithSpecifiedIdComments() {
        Response response = commentsForAsingleUser(3);

        //Deserialize to a "Type-Detail Response" Object
        comments = response.as(CommentsResponse.class);

        //Get name from response
        String name;
        name = comments.getName();

        //Verify that the name is Samantha
        Assert.assertEquals(name, "odio adipisci rerum aut animi");

    }

    @Test
    public void when_getCommentWithId3IsCalled_expect_validEmailFormat() {
        Response response = commentsForAsingleUser(3);

        //Deserialize to a "Type-Detail Response" Object
        comments = response.as(CommentsResponse.class);

        //Get email from response
        String email;
        email = comments.getEmail();

        //Verify that the email is for Samantha and contains . sign
        Assert.assertEquals(email, "Nikita@garfield.biz");
        Assert.assertTrue(email.contains("."));
    }

    @Test
    public void when_getCommentWithId3IsCalled_expect_emailThathasAtSign() {
        Response response = commentsForAsingleUser(3);

        //Deserialize to a "Type-Detail Response" Object
        comments = response.as(CommentsResponse.class);

        //Get Username from response
        String email;
        email = comments.getEmail();

        //Verify that the name is Samantha
        Assert.assertTrue(email.contains("@"));
    }
}