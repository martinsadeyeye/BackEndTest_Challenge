package requests;

import hook.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import responseModels.ListOfAllCommentsSuccessResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        Response response = arrayListOfComments();

        List<Object> jsonResponse = response.jsonPath().getList("$");
        Assert.assertTrue(!jsonResponse.isEmpty());
    }

    @Test
    public void when_getCommentIsCalled_expect_ListOfnamesInComments() {
        Response response = arrayListOfComments();

        ArrayList<String> names = response.then().extract().path("name");
        for (String m : names) {
        }
        Assert.assertEquals("odio adipisci rerum aut animi", names.get(2));

    }

    @Test
    public void when_getCommentIsCalled_expect_ListOfPostId() {

        Response response = arrayListOfComments();

        ArrayList<Integer> postid = response.then().extract().path("postId");
        for (Integer pid : postid) {
        }

    }

    @Test
    public void when_getCommentIsCalled_expect_ListOfEmail() {

        Response response = arrayListOfComments();

        List<String> jsonResponse = response.jsonPath().getList("email");
        for (String eMail : jsonResponse) {
        }
        Assert.assertEquals("Nikita@garfield.biz", jsonResponse.get(2));
    }

    @Test
    public void when_getCommentWithUniquePostIdIsCalled_expect_ListofCommentswithSamePostId() {

        Response response = commentsForSpecificPostId();

        List<String> jsonResponse = response.jsonPath().getList("email");
        for (String emaillist : jsonResponse) {

        }
        Assert.assertEquals("Nikita@garfield.biz", jsonResponse.get(2));

    }

    @Test
    public void when_getCommentWithUniqueIdIsCalled_expect_SingledatawithSpecifiedIdComments() {
        Response response = commentsForAsingleUser(3);

        //Deserialize to a "Type-Detail Response" Object
        ListOfAllCommentsSuccessResponse listofallcomment = response.as(ListOfAllCommentsSuccessResponse.class);

        //Get Username from response
        String name;
        name = listofallcomment.getName();

        //Verify that the name is Samantha
        Assert.assertEquals(name, "odio adipisci rerum aut animi");

        response.prettyPrint();
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