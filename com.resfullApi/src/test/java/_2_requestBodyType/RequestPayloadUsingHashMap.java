package _2_requestBodyType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;

public class RequestPayloadUsingHashMap {

	int id = 0;

	@Test(priority = 1)
	// Post Method
	void testPostUsingHashMap() {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("name", "Kiran");
		data.put("job", "Kiranjob");

// 		if request body contains arry object
//		String CourseArry[] = { "Java", "Selenium", "Python" };
//		data.put("course", CourseArry);

//Hash Map data is not converted into string
		
		given().contentType("application/json").body(data).when().post("https://reqres.in/api/users").then()
				.statusCode(201).body("name", equalTo("Kiran")).body("job", equalTo("Kiranjob"))
				.header("Content-Type", "application/json; charset=utf-8").log().all();
	}

	@Test(priority = 2)
	void createAndStoreID() {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("name", "Kiran");
		data.put("job", "Kiranjob");

		id = given().contentType("application/json").body(data).when().post("https://reqres.in/api/users").jsonPath()
				.getInt("id");

	}

	@Test(priority = 3, dependsOnMethods = { "createAndStoreID" })
	void deleteData() {
		given().when().delete("https://reqres.in/api/users/" + id).then().statusCode(204).log().all();

	}
}
