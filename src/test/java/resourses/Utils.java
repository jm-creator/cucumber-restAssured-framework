package resourses;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;


//reusable test methods
public class Utils {
    RequestSpecification requestSpecification;

    public RequestSpecification requestSpecifications() throws IOException {

        PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
        requestSpecification = new RequestSpecBuilder().
                setBaseUri(getGlobalValue("baseUrl"))
                .addQueryParam("key", "qaclick123")
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .setContentType(ContentType.JSON).build();
        return requestSpecification;
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/test/java/resourses/global.properties");
        properties.load(fileInputStream);
       return properties.getProperty(key);

    }
}
