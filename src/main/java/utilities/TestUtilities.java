package utilities;


import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

import java.util.Base64;
import java.util.Random;

import static io.restassured.RestAssured.given;

/**
 * This class contains static utility variables
 * and methods that are reused across the project
 *
 * @author Martins Adeyeye
 */
public class TestUtilities {

    public static int id;


    /**
     * This utility method performs Base64 encode operation using RFC4648 encoder.
     * This is based on the assumption that the Base64 encoded data required by the
     * endpoints should be in this format.
     *
     * @param s String to be encoded
     * @return A Base64 encoded string
     **/
    public static String encodeInBase64(String s) {
        byte[] base64EncodedData = Base64.getEncoder().encode(s.getBytes());
        String base64DataString = new String(base64EncodedData);
        return base64DataString;
    }


    ResponseSpecification checkStatusCodeAndContentType =
            new ResponseSpecBuilder().
                    expectStatusCode(200).
                    expectContentType(ContentType.JSON).
                    build();

    /**
     * This method creates a valid HTTP GET request to Users.
     * It also logs all request details.
     *
     * @return response
     */

    public static Response arrayListofUsers() {

        Response response =
                given().
                        contentType(ContentType.JSON).
                        when().
                        get(Endpoints.GET_SEARCHUSERS).
                        then().
                        extract().
                        response();

        return response;

    }


    /**
     * This method creates a valid HTTP GET request to Retrieve the details for User with the Id.
     * It also logs all request details.
     *
     * @return response
     */

    public static Response userWithID(int id) {

        Response response =
                given().
                        accept(ContentType.JSON).
                        pathParam("id", id).
                        when().
                        get(Endpoints.GET_SEARCHUSERS_WITHID).
                        then().contentType(ContentType.JSON).
                        extract().
                        response();

        return response;

    }

    /**
     * This method creates a valid HTTP GET request to Posts.
     * It also logs all request details.
     *
     * @return response
     */

    public static Response arrayListOfposts() {

        Response response =
                given().
                        contentType(ContentType.JSON).
                        when().
                        get(Endpoints.GET_FETCHPOSTS).
                        then().
                        extract().
                        response();

        return response;

    }


    /**
     * This method creates a valid HTTP GET request to Comments.
     * It also logs all request details.
     *
     * @return response
     */

    public static Response arrayListOfComments() {

        Response response =
                given().
                        contentType(ContentType.JSON).
                        when().
                        get(Endpoints.GET_FETCHCOMMENTS).
                        then().
                        extract().
                        response();

        return response;

    }


    /**
     * This method creates a valid HTTP GET request to Retrieve the details for Post with the Id.
     * It also logs all request details.
     *
     * @return response
     */

    public static Response postForSpecificId(int id) {

        Response response =
                given().
                        contentType(ContentType.JSON).
                        pathParam("id", id).
                        when().
                        get(Endpoints.GET_FETCHPOST_WITHID).
                        then().
                        extract().
                        response();

        return response;

    }


    /**
     * This method creates a valid HTTP GET request to Posts Using UserId.
     * It also logs all request details.
     *
     * @return response
     */

    public static Response postForSpecificUserId() {

        Response response =
                given().
                        contentType(ContentType.JSON).
                        when().
                        get(Endpoints.GET_FETCHPOST_WITHUSERID).
                        then().
                        extract().
                        response();

        return response;

    }


    /**
     * This method creates a valid HTTP GET request to comments for a specific PostId.
     * It also logs all request details.
     *
     * @return response
     */

    public static Response commentsForSpecificPostId() {

        Response response =
                given().
                        contentType(ContentType.JSON).
                        when().
                        get(Endpoints.GET_FETCHCOMMENT_WITHPOSTID).
                        then().
                        extract().
                        response();

        return response;

    }


    /**
     * This method creates a valid HTTP GET request to Retrieve all comment for PostId but  returns All Comments for all the POSts and user.
     * It also logs all request details.
     *
     * @return response
     */

    public static Response commentforaPostID() {

        Response response =
                given().
                        contentType(ContentType.JSON).
                        when().
                        get(Endpoints.GET_FETCHCOMMENT_WITHPID).
                        then().
                        extract().
                        response();

        return response;

    }


    /**
     * This method creates a valid HTTP GET request to Retrieve the details for the comment using the Id
     * It also logs all request details.
     *
     * @return response
     */

    public static Response commentsForAsingleUser(int id) {

        Response response =
                given().
                        contentType(ContentType.JSON).
                        pathParam("id", id).
                        when().
                        get(Endpoints.GET_FETCHCOMMENTS_WITHID).
                        then().
                        extract().
                        response();

        return response;

    }


    /**
     * This method generates random long values that are used as unique side IDs.
     * It ensures that the generated values are positive.
     */
    public static void generateID() {
        Random randomID = new Random();
        id = (int) (randomID.nextLong() & Long.MAX_VALUE); //Positive random IDs
    }
}