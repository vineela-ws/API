
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.Payload;
import files.ReusableMethods;

public class Base {

	public static void main(String[] args) throws IOException {
		// given -all input details
		// when -Submit the API
		// then -Validate the response

		RestAssured.baseURI = "http://rahulshettyacademy.com";
		String response = given().log().all().header("Content-Type", "application/json").body(Payload.AddPlace()).when()
				.post("maps/api/place/add/json").then().log().all().statusCode(200).body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		System.out.println("placeId");

		// Update Place
		String newAddress = "70 winter walk, USA";
		given().log().all().header("Content-Type", "application/json").body(Payload.AddPlace())
				.body("{\r\n" + "\"place_id\": \"" + placeId + "\",\r\n" + "\"address\": \"" + newAddress
						+ "\",\r\n" + "\"key\": \"qaclick123\"\r\n" + "}")
				.when().put("/maps/api/place/update/json").then().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		// Get Place
		String getPlaceResponce = given().log().all().queryParam("place_id", placeId).when()
				.get("maps/api/place/get/json").then().assertThat().log().all().statusCode(200).extract().response()
				.asString();
		
		JsonPath js1= ReusableMethods.rawToJson(response);
		String actualAddress = js1.getString("address");
		System.out.println("actualAddress");
		Assert.assertEquals(actualAddress, newAddress);
		
		//Getting the JSON from files
		String response1 = given().log().all().header("Content-Type", "application/json").body(Files.readAllBytes(Paths.get("AddPlace.Json"))).when()
				.post("maps/api/place/add/json").then().log().all().statusCode(200).body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

	}

}
