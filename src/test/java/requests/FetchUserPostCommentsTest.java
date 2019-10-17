package requests;

import hook.TestBase;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.Endpoints;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class FetchUserPostCommentsTest extends TestBase {

    @BeforeTest
    public void setBaseURI() throws IOException {
        initializeBaseURI();
    }

    @Test
    public void when_getUserIsCalled_expect_OneRecordPerUser() {
        given().get(Endpoints.GET_SEARCHUSERS).then().statusCode(200).log().all();
    }
}
