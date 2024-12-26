import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	public static void main(String[] args) {
		JsonPath js = new JsonPath(Payload.CoursePrice());

		// Print no of courses return by API
		int count = js.getInt("courses.size()");
		System.out.println(count);

		// Print no of courses return by API
		int TotalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(TotalAmount);

		// Print Title of the first Course
		String titleFirstCourse = js.get("courses[2].title");
		System.out.println(titleFirstCourse);
		// Print all Course Titles and their respective prices

		for (int i = 0; i < count; i++) {
			String courseTiles = js.get("courses[" + i + "].title");
			System.out.println(courseTiles);
			System.out.println(js.get("courses[" + i + "].price").toString());
		}

		System.out.println("Print number of copies sold by RPA");
		for (int i = 0; i < count; i++) {
			String courseTiles = js.get("courses[" + i + "].title");
			if (courseTiles.equalsIgnoreCase("RPA")) {
				int copies = js.get("courses[" + i + "].copies");
				System.out.println(copies);
				break;
			}
		}

	}

}
