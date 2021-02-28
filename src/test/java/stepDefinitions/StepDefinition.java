package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
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
import resources.apiResourcePaths;

public class StepDefinition extends Utils {
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	
	@Given("AddPlace payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {

		res = given().spec(requestSpecification()).body(data.addPlacePayload(name, address, language));

	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {

		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if (method.equalsIgnoreCase("POST"))
			response = res.when().post(resourceAPI.getResource());

		else if (method.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource());

		else if (method.equalsIgnoreCase("DELETE"))
			response = res.when().delete(resourceAPI.getResource());

	}

	@Then("API call sucess with status code {int}")
	public void api_call_sucess_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {

		assertEquals(getJsonPath(response, key), value);

	}

	@Then("verify place_id maps to {string} using {string}")
	public void verify_place_id_maps_to_using(String expectedname, String resource) throws IOException {

		 place_id = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String actualname = getJsonPath(response, "name");

		assertEquals(actualname, expectedname);

	}
	@Given("DeletePlace payload")
	public void delete_place_payload() throws IOException {
		res=given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}

}
