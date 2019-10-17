package utilities;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class Configuration {

    @BeforeSuite(alwaysRun = true)
    public void configure() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }
}
