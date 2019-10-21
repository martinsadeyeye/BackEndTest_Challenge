package utilities;


import io.restassured.http.ContentType;
import io.restassured.response.Response;

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
     * This method creates a valid HTTP GET request to retrieve all Users.
     *
     * @return response
     */

    public static Response arrayListofUsers() {

        Response response =
                given().
                        contentType(ContentType.JSON).
                        when().
                        get(Endpoints.GET_USERS).
                        then().
                        extract().
                        response();

        return response;

    }


    /**
     * This method creates a valid HTTP GET request
     * to retrieve the details for User with the specified id.
     *
     * @param id
     *
     * @return response
     */

    public static Response userWithID(int id) {

        Response response =
                given().
                        accept(ContentType.JSON).
                        pathParam("id", id).
                        when().
                        get(Endpoints.GET_USERS_BY_ID).
                        then().
                        extract().
                        response();

        return response;

    }

    /**
     * This method creates a valid HTTP GET request
     * to retrieve all comments.
     *
     * @return response
     */

    public static Response arrayListOfComments() {

        Response response =
                given().
                        contentType(ContentType.JSON).
                        when().
                        get(Endpoints.GET_COMMENTS).
                        then().
                        extract().
                        response();

        return response;

    }

    /**
     * This method creates a valid HTTP GET request
     * to retrieve all posts related to a specific UserId.
     *
     * @param id
     *
     * @return response
     */

    public static Response postsByUserId(int id) {

        Response response =
                given().
                        contentType(ContentType.JSON).
                        queryParam("userId", id).
                        when().
                        get(Endpoints.GET_POSTS).
                        then().
                        extract().
                        response();

        return response;

    }


    /**
     * This method creates a valid HTTP GET request
     * to get comments for a specific PostId.
     *
     * @param postId
     *
     * @return response
     */

    public static Response commentsForSpecificPostId(int postId) {

        Response response =
                given().
                        contentType(ContentType.JSON).
                        queryParam("postId", postId).
                        when().
                        get(Endpoints.GET_COMMENTS).
                        then().
                        extract().
                        response();

        return response;

    }

    /**
     * This helper method validates email format
     *
     * @param email
     *
     * @return boolean
     */
    public static boolean isEmailValid(String email) {
        String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        return email.matches(regex);
    }
}