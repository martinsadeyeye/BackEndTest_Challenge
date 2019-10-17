package requests;

import hook.TestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.Endpoints;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utilities.TestUtilities.arrayListofUsers;

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
    }

    @Test
    public void when_getUserIsCalled_expect_ArrayIsNotEmpty() {
        given().get(Endpoints.GET_SEARCHUSERS).then().statusCode(200).log().all();
    }

    @Test
    public void when_getUserIsCalled_expect_ArrayListOfUsers() {
        Response response = arrayListofUsers();

        List<String> jsonResponse = response.jsonPath().getList("$");
        System.out.println(jsonResponse.size());
    }

    @Test
    public void when_getUserIsCalled_expect_ArrayListOfUsername() {
        Response response = arrayListofUsers();

        String usernames = response.jsonPath().getString("username");
        System.out.println(usernames);
    }
    @Test
    public void when_getUserIsCalled_expect_ArrayListOfUserId() {
        Response response = arrayListofUsers();

        String id = response.jsonPath().getString("id");
        System.out.println(id);
    }

    @Test
    public void when_getUserIsCalled_expect_HeaderContentType_ApplicationJson() {
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

        response.
                then().
                assertThat().body("[2].username", equalTo("Samantha")).
                extract().
                response();
    }

}
