package requests;

import hook.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import responseModels.UsersResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static utilities.TestUtilities.*;

/**
 * This class contains all tests for the User
 *
 * @author Martins Adeyeye
 */
public class SearchUserTest extends TestBase {

    List<UsersResponse> userresponse;

    /**
     * Before the tests, it initializes the base URI
     * which will be used by each test method.
     *
     * @throws IOException
     */

    @BeforeTest
    public void setBaseURI() throws IOException {
        initializeBaseURI();
    }

    @Test
    public void when_getUsersIsCalled_expect_HeaderContentTypeToBeApplicationJson() {
        Response response = arrayListofUsers();

        Assert.assertEquals("application/json; charset=utf-8", response.contentType()); //Validate HTTP Status Code from response

    }

    @Test
    public void when_getUsersIsCalled_expect_HTTPStatusCode200() {

        Response response = arrayListofUsers();

        Assert.assertEquals(200, response.statusCode()); //Validate HTTP Status Code from response
    }

    @Test
    public void when_getUsersIsCalled_expect_ArrayIsNotEmpty() {

        Response response = arrayListofUsers();

        userresponse = Arrays.asList(response.as(UsersResponse[].class));
        Assert.assertTrue(!userresponse.isEmpty());

    }

    @Test
    public void when_getUsersIsCalled_expect_SamanthaToBeOnUserList() {
        Response response = arrayListofUsers();

        userresponse = Arrays.asList(response.as(UsersResponse[].class));

        String username = userresponse.get(2).getUsername();
        Assert.assertEquals(username, "Samantha");
    }

    @Test
    public void when_getUserIsCalledWithUserId3_expect_HTTPStatusCode200() {

        Response response = userWithID(3);

        Assert.assertEquals(200, response.statusCode()); //Validate HTTP Status Code from response
    }

    @Test
    public void when_getUserIsCalledWithUserId3_expect_UsernameSamantha() {

        Response response = userWithID(3);

        UsersResponse user = response.as(UsersResponse.class);

        //Get Username from response
        String username;
        username = user.getUsername();

        //Verify that the username is Samantha
        Assert.assertEquals(username, "Samantha");

    }

    @Test
    public void when_getUserIsCalledWithUserId3_expect_IdIs3() {

        Response response = userWithID(3);

        UsersResponse user = response.as(UsersResponse.class);

        //Get Username from response
        int id;
        id = user.getId();

        //Verify that the username is Samantha
        Assert.assertEquals(id, 3);

    }

    @Test
    public void when_getUserIsCalledWithNonExistingUserId_expect_NoResult() {

        Response response = userWithID(99999);

        //Verify that the result is empty
        Assert.assertEquals(response.getBody().asString(), "{}");
    }
}