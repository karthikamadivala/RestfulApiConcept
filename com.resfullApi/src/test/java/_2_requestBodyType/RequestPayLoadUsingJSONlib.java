package _2_requestBodyType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class RequestPayLoadUsingJSONlib {

	int id = 0;

	// Uing org.json library
	@Test
//	@Ignore
	void testPostUsingJSONlibrary() {

		JSONObject data = new JSONObject();

		data.put("name", "Kiran");
		data.put("job", "Kiranjob");
		
// 		if request body contains arry object
//		String CourseArry[] = { "Java", "Selenium", "Python" };
//		data.put("course", CourseArry);

//		JSON Object data is converted into string	in request body	
		
		given().contentType("application/json").body(data.toString()).when().post("https://reqres.in/api/users").then()
				.statusCode(201).body("name", equalTo("Kiran")).body("job", equalTo("Kiranjob"))
				.header("Content-Type", "application/json; charset=utf-8").log().all();
	}

	@Test(priority = 1)
//	@Ignore
	void creatDataJSONlibraryStoreID() {

		JSONObject data = new JSONObject();

		data.put("name", "Kiran");
		data.put("job", "Kiranjob");

		id = given().contentType("application/json").body(data.toString()).when()
				.post("https://reqres.in/api/users").jsonPath().getInt("id");
		System.out.println("User iD= "+id);
	}

	@Test(priority = 2, dependsOnMethods = { "creatDataJSONlibraryStoreID" })
//	@Ignore
	void deleteData() {
		given().when().delete("https://reqres.in/api/users/" + id).then().statusCode(204).log().all();

	}
}
