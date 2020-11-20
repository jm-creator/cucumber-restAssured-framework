package stepDefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resourses.APIResources;
import resourses.TestDataBuild;
import resourses.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinations extends Utils {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    Response response;
    TestDataBuild dataBuild = new TestDataBuild();
    static String place_id;


    @Given("add place payload with {string} {string} {string}")
    public void add_place_payload(String name, String language, String address) throws IOException {
        requestSpecification = given().spec(requestSpecifications())
                .body(dataBuild.addPlacePayLoad(name, language, address));
    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_post_http_request(String resource, String method) {
        APIResources apiResources = APIResources.valueOf(resource);
        System.out.println(apiResources.getResource());
        responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        if (method.equalsIgnoreCase("POST"))
            response = requestSpecification.when().post(apiResources.getResource());
        else if (method.equalsIgnoreCase("GET"))
            response = requestSpecification.when().get(apiResources.getResource());
    }

    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer integer) {
        assertEquals(200, response.getStatusCode());
    }


    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String keyValue, String expectedValue) {
    /*  String responseString = response.asString();
        jsonPath = new JsonPath(responseString);
        place_id = jsonPath.get("place_id");
        assertEquals(expectedValue, jsonPath.get(keyValue).toString());  */
        assertEquals(getJsonPath(response, keyValue), expectedValue);
    }

    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
        place_id = getJsonPath(response, "place_id");
        requestSpecification = given().spec(requestSpecifications()).queryParam("place_id", place_id);
        user_calls_with_post_http_request(resource, "GET");
        String name = getJsonPath(response, "name");
        assertEquals(name, expectedName);
    }

}