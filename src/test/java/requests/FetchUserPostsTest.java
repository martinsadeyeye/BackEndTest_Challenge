package requests;

import hook.TestBase;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.Endpoints;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class FetchUserPostsTest extends TestBase {


    @BeforeTest
    public void setBaseURI() throws IOException {
        initializeBaseURI();
    }

    @Test
    public void when_getPostIsCalled_expect_HTTPStatusCode200() {

    }

    @Test
    public void when_getPostIsCalled_expect_HeaderContentType_ApplicationJson() {

    }

    @Test
    public void when_getPostIsCalled_expect_ArrayIsNotEmpty() {

    }

    @Test
    public void when_getPostIsCalled_expect_ListOfPosts() {

    }

    @Test
    public void when_getPostIsCalled_expect_ListOfTitle() {

    }

    @Test
    public void when_getPostIsCalled_expect_ListOfUserId() {

    }

    @Test
    public void when_getPostIsCalled_expect_SamanthasId() {

    }

    @Test
    public void when_getPostWithUniqueUserIdIsCalled_expect_ListofPostwithSameUserId() {

    }

    public void when_getPostWithUniqueIdIsCalled_expect_SingleData() {

    }


}
