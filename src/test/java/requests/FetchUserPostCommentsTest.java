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
    public void when_getCommentIsCalled_expect_HTTPStatusCode200() {

    }

    @Test
    public void when_getCommentIsCalled_expect_HeaderContentType_ApplicationJson() {

    }

    @Test
    public void when_getCommentIsCalled_expect_ArrayIsNotEmpty() {

    }

    @Test
    public void when_getCommentIsCalled_expect_ListOfComments() {

    }

    @Test
    public void when_getCommentIsCalled_expect_ListOfPostId() {

    }

    @Test
    public void when_getCommentIsCalled_expect_ListOfEmail() {

    }

    @Test
    public void when_getCommentWithUniquePostIdIsCalled_expect_ListofCommentwithSamePostId() {

    }

    @Test
    public void when_getCommentWithUniqueIdIsCalled_expect_SingleData() {

    }

    public void when_getCommentIsCalled_expect_validEmailFormat() {

    }


}
