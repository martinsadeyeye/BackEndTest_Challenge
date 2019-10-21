# UserBlog Test Suite
Test Suite for the **Backend_Test_Challenge** 

## The Setup
- JAVA 8+ jdk must be [installed](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)  
- MAVEN must be [installed](https://maven.apache.org/download.cgi) 

## Running the Test Suite
1. Open the environment property file `<project_root_folder>\src\main\resources\environment.properties`,
update the value of the`HOST` key with the appropriate HOST
2. From the project root folder, run `mvn test`

## Viewing Test Report
1. Go to test report location: `<project_root_folder>\test-output`
2. Open `ExtentReportsTestNG.html` in a web browser
