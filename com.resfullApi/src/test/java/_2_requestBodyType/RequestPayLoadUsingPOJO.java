package _2_requestBodyType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class RequestPayLoadUsingPOJO {

	int id = 0;

//	Pojo_PayloadRequest.java file has been used for getting the data
	
	@Test
//	@Ignore
	void testPostUsingPOJOclass() {

		Pojo_PayloadRequest data = new Pojo_PayloadRequest();
		data.setName("KirandA");
		data.setJob("Kiranjob");
		
// 		if request body contains arry object
//		String CourseArry[] = { "Java", "Selenium", "Python" };
//		data.setCourse(CourseArry);		

//POJO data are need not to be converted into string like JSON Object

		given().contentType("application/json").body(data).when().post("https://reqres.in/api/users").then()
				.statusCode(201).body("name", equalTo("KirandA")).body("job", equalTo("Kiranjob"))
				.header("Content-Type", "application/json; charset=utf-8").log().all();
	}

	@Test(priority = 1)
//	@Ignore
	void createAndStoreIDUsingPOJOclass() {

		Pojo_PayloadRequest data = new Pojo_PayloadRequest();
		data.setName("Kiran");
		data.setJob("Kiranjob");

		id = given().contentType("application/json").body(data).when().post("https://reqres.in/api/users").jsonPath()
				.getInt("id");

	}

	@Test(priority = 2, dependsOnMethods = { "createAndStoreIDUsingPOJOclass" })
//	@Ignore
	void deleteData() {
		given().when().delete("https://reqres.in/api/users/" + id).then().statusCode(204).log().all();

	}

}
