import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class JIRAAPI {

	@Test

	public void createBug() {

		RestAssured.baseURI = "https://amaranenivineela.atlassian.net/";
		String createIssueResponse = given().header("Content-Type", "application/json").header("Authorization",
				"Basic YW1hcmFuZW5pdmluZWVsYUBnbWFpbC5jb206QVRBVFQzeEZmR0Ywczc0aWdsckdUc0xKcGlwUlgwdmQ3UHJuVk5RNmk3Y0xOSi1QaGg3YVFPeWtObzhOWFlqNFl0WWMyS0NyeGs4UE1kTEtOaXFaUTllakp3ZXBOeHNBcHh1bGZvOTV2SG4tQkExU0FGQTZPRmVUWHBtR29mQzljd01OX0M4eEtibGgwcTVyNGh3cDFkSzd1QUd6NUd4YzhvYUZBRGNxZlktV1NxT1pUckxmMTBjPTdEQjU5NUND")
				.body("{\n" + "    \"fields\": {\n" + "       \"project\":\n" + "       {\n"
						+ "          \"key\": \"KAN\"\n" + "       },\n"
						+ "       \"summary\": \"Website items are not working- automation Rest Assured\",\n"
						+ "       \"issuetype\": {\n" + "          \"name\": \"Bug\"\n" + "       }\n" + "   }\n" + "}")
				.log().all().post("rest/api/3/issue").then().log().all().assertThat().statusCode(201)
				.contentType("application/json").extract().response().asString();
		JsonPath js = new JsonPath(createIssueResponse);
		String issueId = js.getString("id");
		System.out.println(issueId);
		given().pathParam("key", issueId).header("X-Atlassian-Token", "no-check").header("Authorization",
				"Basic YW1hcmFuZW5pdmluZWVsYUBnbWFpbC5jb206QVRBVFQzeEZmR0Ywczc0aWdsckdUc0xKcGlwUlgwdmQ3UHJuVk5RNmk3Y0xOSi1QaGg3YVFPeWtObzhOWFlqNFl0WWMyS0NyeGs4UE1kTEtOaXFaUTllakp3ZXBOeHNBcHh1bGZvOTV2SG4tQkExU0FGQTZPRmVUWHBtR29mQzljd01OX0M4eEtibGgwcTVyNGh3cDFkSzd1QUd6NUd4YzhvYUZBRGNxZlktV1NxT1pUckxmMTBjPTdEQjU5NUND")
				.multiPart("file",
						new File("C:\\Users\\91628\\Downloads\\WhatsApp Image 2024-12-08 at 12.10.00 PM.jpeg"))
				.log().all().post("rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
	}

}
