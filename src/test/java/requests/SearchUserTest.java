package requests;


        import hook.TestBase;
        import org.testng.annotations.BeforeTest;
        import org.testng.annotations.Test;
        import utilities.Endpoints;

        import java.io.IOException;

        import static io.restassured.RestAssured.given;

public class SearchUserTest extends TestBase {

    @BeforeTest
    public void setBaseURI() throws IOException {
        initializeBaseURI();
    }


    @Test
    public void when_getUserIsCalled_expect_HTTPStatusCode200() {
        given().get(Endpoints.GET_SEARCHUSERS).then().statusCode(200).log().all();
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
        given().get(Endpoints.GET_SEARCHUSERS).then().header("Content-Type", "application/json; charset=utf-8");
    }

}
