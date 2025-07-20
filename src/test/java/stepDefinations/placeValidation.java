package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class placeValidation extends Utils {

    RequestSpecification respo;
    ResponseSpecification resp;
    Response response;
    static String place_id;
    JsonPath js;
    TestDataBuild Testpayload = new TestDataBuild();


    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
        respo = given().log().all().spec(requestSpecification()).body(Testpayload.addPlacepayload(name, language, address));
    }

    @When("User calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {
        APIResources resourceAPI = APIResources.valueOf(resource);
        System.out.println("Resource URL is: " + resourceAPI.getResources());

        resp = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
        if (method.equalsIgnoreCase("POST")) {
            response = respo.when().post(resourceAPI.getResources())
                    .then().log().all().spec(resp).extract().response();
        }
        else if (method.equalsIgnoreCase("Get")) {
                response = respo.when().get(resourceAPI.getResources())
                        .then().log().all().spec(resp).extract().response();
            }
        }

        @Then("the API call got success with status code {int}")
        public void the_api_call_got_success_with_status_code (Integer int1){
            // Write code here that turns the phrase above into concrete actions
            assertEquals(response.getStatusCode(), 200);
        }

        @Then("{string} in response body is {string}")
        public void in_response_body_is (String keyValue, String Expected){
            // Write code here that turns the phrase above into concrete actions
            assertEquals(getJsonPath(response,keyValue), Expected);
        }

        @Then("verify place_Id created maps to {string} using {string}")
        public void verify_place_id_created_maps_to_using_get_place_api(String expectedName, String resources) throws IOException {
        // Write code here that turns the phrase above into concrete actions
           // prepare req spec
           place_id = getJsonPath(response,"place_id");
           respo = given().log().all().spec(requestSpecification()).queryParam("place_id", place_id);
           user_calls_with_http_request(resources, "GET");
           String name = getJsonPath(response,"name");
           assertEquals(name, expectedName);
    }
       @Given("DeletePlace Payload")
       public void delete_place_payload() throws IOException {
           respo = given().spec(requestSpecification()).body(Testpayload.deletePlacePayload(place_id));

    }
}
