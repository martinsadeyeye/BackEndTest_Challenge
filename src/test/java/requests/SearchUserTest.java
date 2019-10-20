package requests;

import hook.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import responseModels.ListOfAllUsersSuccessResponse;

import java.io.IOException;
import java.util.List;

import static utilities.TestUtilities.*;

/**
 * This class contains all tests for the User
 *
 * @author Martins Adeyeye
 */
public class SearchUserTest extends TestBase {

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

        List<String> jsonResponse = response.jsonPath().getList("$");
        Assert.assertTrue(!jsonResponse.isEmpty());
    }

    @Test
    public void when_getUserIsCalled_expect_ArrayListOfUsers() {
        Response response = arrayListofUsers();

        List<String> jsonResponse = response.jsonPath().getList("username");
        Assert.assertEquals(10, jsonResponse.size());

        String Username = response.then().extract().path("[2].username");
        Assert.assertEquals(Username, "Samantha");
    }

    @Test
    public void when_getUserIsCalled_expect_ArrayListOfUsername() {
        Response response = arrayListofUsers();

        List<String> jsonResponse = response.jsonPath().getList("username");
        for (String em : jsonResponse) {

        }

        Assert.assertEquals("Samantha", jsonResponse.get(2));
    }

    @Test
    public void when_getUserIsCalled_expect_ArrayListOfUserId() {
        Response response = arrayListofUsers();

        List<String> jsonResponse = response.jsonPath().getList("id");
        for (String em : jsonResponse) {

        }
        Assert.assertEquals(3, jsonResponse.get(2));
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
    public void when_getUserIsCalled_expect_UsernameSamantha() {

        Response response = arrayListofUsers();

        //Deserialize to a "Type-Detail Response" Object
        ListOfAllUsersSuccessResponse listofalluser = response.as(ListOfAllUsersSuccessResponse.class);

        //Get Username from response
        int id;
        id = listofalluser.getId();

        //Verify that the username is Samantha
        Assert.assertEquals(id, 3);

    }

    @Test
    public void when_getUserWithUniqueIdIsCalled_expect_UsernameSamantha() {

        Response response = userWithID(3);

        //Deserialize to a "Type-Detail Response" Object
        ListOfAllUsersSuccessResponse listofalluser = response.as(ListOfAllUsersSuccessResponse.class);

        //Get Username from response
        String username;
        username = listofalluser.getUsername();

        //Verify that the username is Samantha
        Assert.assertEquals(username, "Samantha");

        response.prettyPrint();
    }

}
