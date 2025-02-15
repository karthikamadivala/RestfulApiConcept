package _2_requestBodyType;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

//URL of the API :  https://gorest.co.in/
public class Sample {

	/*
	 * Ways to Create Request Body 
	 * 1. HashMap 2. Uing org.json 3. 
	 * POJO(Plain old java objects) 4. Extranal JSON
	 */

	@Test
	// Post Method
	void testPostUsingHashMap() {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("name", "Kiran");
		data.put("job", "Kiranjob");
//		data.put("email", "Kiran@gmail.com");
//		data.put("gender", "Male");
//		data.put("status", "Active");

		/*
		 * if we want to store array data in the HashMap First we have to store the data
		 * in the array then we have to put the same into the HashMap
		 */
//		String CourseArry[] = { "Java", "Selenium", "Python" };
//		data.put("course", CourseArry);
		
//	int id=given().contentType("application/json").body(data).when().post("https://reqres.in/api/users").jsonPath().getInt("id");
	given().contentType("application/json").body(data).when().post("https://reqres.in/api/users")
	.then().statusCode(201)
	.body("name", equalTo("Kiran")).body("job", equalTo("Kiranjob")).header("Content-Type", "application/json; charset=utf-8")
	.log().all();
	}

}
