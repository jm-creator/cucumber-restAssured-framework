package resourses;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;


//reusable test methods
public class Utils {
    static RequestSpecification requestSpecification;

    public RequestSpecification requestSpecifications() throws IOException {
        if (requestSpecification == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            requestSpecification = new RequestSpecBuilder().
                    setBaseUri(getGlobalValue("baseUrl"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return requestSpecification;
        }
        return requestSpecification;
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/test/java/resourses/global.properties");
        properties.load(fileInputStream);
       return properties.getProperty(key);

    }

    public String getJsonPath(Response response, String key){
        String responseString = response.asString();
        JsonPath jsonpath = new JsonPath(responseString);
        return jsonpath.get(key).toString();
    }
}
