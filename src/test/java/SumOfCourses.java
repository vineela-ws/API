import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class SumOfCourses {
	
	@Test
	public void sumOfCourses() {
		JsonPath js = new JsonPath(Payload.CoursePrice());
		int count = js.getInt("courses.size()");
		
		int sum =0;
		for (int i = 0; i < count; i++) {
			  int price = js.getInt("courses[" + i + "].price");
			  int copies = js.getInt("courses[" + i + "].copies");
			  int amount = price*copies;
			  sum= sum+amount;
		}
		  System.out.println(sum);
			int TotalAmount = js.getInt("dashboard.purchaseAmount");
			Assert.assertEquals(sum, TotalAmount);
		
	}

}
