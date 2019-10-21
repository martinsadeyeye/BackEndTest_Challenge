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

    @BeforeMethod
    public void uniqueIDForEachMethod() {
        generateID();
    }

    @Test
    public void when_getUserIsCalled_expect_HeaderContentTypeToBeApplicationJson() {
        Response response = arrayListofUsers();

        response.
                then().
                assertThat().header("Content-Type", "application/json; charset=utf-8").
                extract().
                response();
    }

    @Test
    public void when_getUserIsCalled_expect_HTTPStatusCode200() {

        Response response = arrayListofUsers();

        response.
                then().
                assertThat().statusCode(200). //Verify HTTP Status Code from response
                extract().
                response();

        Assert.assertEquals(200, response.statusCode()); //Validate HTTP Status Code from response
    }

    @Test
    public void when_getUserIsCalled_expect_ArrayIsNotEmpty() {

        Response response = arrayListofUsers();

        userresponse = Arrays.asList(response.as(UsersResponse[].class));
        Assert.assertTrue(!userresponse.isEmpty());

    }

    @Test
    public void when_getUserIsCalled_expect_SamanthaToBeOnUserList() {
        Response response = arrayListofUsers();

        userresponse = Arrays.asList(response.as(UsersResponse[].class));
        Assert.assertEquals(10, userresponse.size());

        String Username = response.then().extract().path("[2].username");
        Assert.assertEquals(Username, "Samantha");
    }


    @Test
    public void when_getUserWithUniqueId3IsCalled_expect_UsernameSamantha() {

        Response response = userWithID(3);

        UsersResponse specificid = response.as(UsersResponse.class);

        //Get Username from response
        String username;
        username = specificid.getUsername();

        //Verify that the username is Samantha
        Assert.assertEquals(username, "Samantha");

    }

}