package requests;

import hook.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.Endpoints;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utilities.TestUtilities.*;

public class SearchUserTest extends TestBase {

    @BeforeTest
    public void setBaseURI() throws IOException {
        initializeBaseURI();
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

    }

    @Test
    public void when_getUserIsCalled_expect_ArrayListOfUsers() {
        Response response = arrayListofUsers();

        List<String> jsonResponse = response.jsonPath().getList("$");
        System.out.println(jsonResponse.size());
        Assert.assertEquals(10, jsonResponse.size());
    }

    @Test
    public void when_getUserIsCalled_expect_ArrayListOfUsername() {
        Response response = arrayListofUsers();

        String userNames = response.jsonPath().getString("username");
        System.out.println(userNames);
    }

    @Test
    public void when_getUserIsCalled_expect_ArrayListOfUserId() {
        Response response = arrayListofUsers();

        String id = response.jsonPath().getString("id");
        System.out.println(id);
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
        String username = response.jsonPath().getString("username[2]");
        System.out.println(username);

        List<String> jsonResponse = response.jsonPath().getList("username");
        System.out.println(jsonResponse.get(2));

        response.
                then().
                assertThat().body("[2].username", equalTo("Samantha")).
                extract().
                response();

    }

    @Test
    public void when_getUserWithUniqueIdIsCalled_expect_UsernameSamantha() {

        Response response = userWithID(3);
        String username = response.jsonPath().getString("username");
        System.out.println(username);

        response.
                then().
                assertThat().body("username", equalTo("Samantha")).
                extract().
                response();

    }

}
