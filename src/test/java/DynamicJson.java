import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payload;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {

	@Test(dataProvider="BooksData")

	public void addBook(String aisle, String isbn ) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().header("Content-Type", "application/json")
				.body(Payload.addBook(aisle,isbn)).when().post("/Library/Addbook.php").then().assertThat()
				.statusCode(200).extract().response().asString();
		JsonPath js = ReusableMethods.rawToJson(response);
		String id = js.get("ID");
		System.out.println(id);
	}

	@DataProvider(name="BooksData")
	public Object[][] getData() {
		// Array = Collection of elements
		// MultiDimentional Array = Collection of Arrays

		return new Object[][] { { "6466", "adsfs" }, { "6467", "adsfs" }, { "6468", "adsfs" } };
	}

}
