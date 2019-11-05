package hook;

import io.restassured.RestAssured;
import org.testng.annotations.Listeners;
import utilities.ExtentReporterNG;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Listeners(ExtentReporterNG.class)

/**
 * @author Martins Adeyeye
 */
public class TestBase {
    /**
     * This is assuming that this project might not be run on the same machine as the application under test.
     * Hence the HOST will be set from the environment property file in the resources folder of the project.
     **/
    public static Properties environment;

    public void initializeBaseURI() throws IOException {

        //Reading the object from location
        //Read file from location within project and get the defined HOST
        environment = new Properties();
        FileInputStream environmentFile = new FileInputStream(System.getProperty("user.dir")
                + "/src/main/resources/environment.properties"); //Location of the property file
        environment.load(environmentFile);

        //Set base URI
        RestAssured.baseURI = environment.getProperty("HOST");

    }
}
