package stepDefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resourses.TestDataBuild;
import resourses.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinations extends Utils {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    Response response;
    TestDataBuild dataBuild = new TestDataBuild();

    @Given("add place payload")
    public void add_place_payload() throws IOException {
        responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        requestSpecification = given().spec(requestSpecifications()).body(dataBuild.addPlacePayLoad());
    }

    @When("user calls {string} with Post http request")
    public void user_calls_with_post_http_request(String string) {
    response = requestSpecification.when().post("maps/api/place/add/json")
            .then().spec(responseSpecification).extract().response();
    }

    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
    assertEquals(response.getStatusCode(), 200);
}


    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String keyValue, String ExpectedValue) {
    }
}
