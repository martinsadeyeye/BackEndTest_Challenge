package requests;

import hook.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.Endpoints;

import java.io.IOException;

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
        given().get(Endpoints.GET_SEARCHUSERS).then().statusCode(200).log().all();
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
    public void when_getUserIsCalled_expect_OneRecordPerUser() {

        Response response = arrayListofUsers();

        response.
                then().
                assertThat().body("[2].username", equalTo("Samantha")).
                extract().
                response();
    }

}
