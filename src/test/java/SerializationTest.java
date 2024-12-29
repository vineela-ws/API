import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.AddPlace;
import pojo.Location;


public class SerializationTest {

	public static void main(String[] args) {
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://rahulshettyacademy.com");
		p.setName("Frontline house");
		List<String> mylist = new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		
		p.setTypes(mylist);
		Location location = new Location();
		location.setLag(-38.383494);
		location.setLag(33.427362);

		
		p.setLocation(location);
		
		
		RestAssured.baseURI = "http://rahulshettyacademy.com";
		Response res = given().queryParam("key", "qaclick123")
				.body(p)
				.post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200).extract().response();
		
		String responseString = res.asString();
		System.out.println(responseString);

	}

}
