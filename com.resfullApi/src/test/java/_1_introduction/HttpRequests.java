package _1_introduction;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import io.restassured.module.jsv.JsonSchemaValidator.*;

public class HttpRequests {

	
	/*
	 * Dependencies 1. rest-assured 2.json-path 3. json 4.gson 5.testng 6.
	 * scribejava-apis 7. json-schema-validator 8. xml-schema-validator
	 */
	//	URL of API:   https://reqres.in/  

	int id = 0;

	@Test
	@Ignore
	void getUsers() {
		given()
		.when().get("https://reqres.in/api/users?page=2")
		.then().statusCode(200).body("page", equalTo(2)).log()
				.all();
	}
	@Test
	@Ignore	
	void getUsersWithoutGivenMethod() {
		when().get("https://reqres.in/api/users?page=2")
		.then().statusCode(200).body("page", equalTo(2)).log()
				.all();
	}
	
	@Test
	@Ignore
	void createUser() {

		HashMap data = new HashMap();
		data.put("name", "Karthik");
		data.put("job", "Tester");

		given().contentType("application/json").body(data).when().post("https://reqres.in/api/users").then()
				.statusCode(201).log().all();
	}

	@Test(priority = 1)
//	@Ignore
	void createUseraAndGetID() {

		HashMap data = new HashMap();
		data.put("name", "Karthik");
		data.put("job", "Tester");

		id = given().contentType("application/json").body(data).when().post("https://reqres.in/api/users").jsonPath()
				.getInt("id");

		System.out.println("User iD is= " + id);
	}

	@Test(priority = 2, dependsOnMethods = { "createUseraAndGetID" })
//	@Ignore
	void updateUser() {

		HashMap data = new HashMap();
		data.put("name", "UpdatedName");
		data.put("job", "UpdatedJob");

		given().contentType("application/json").body(data).when().put("https://reqres.in/api/users/" + id).then()
				.statusCode(200).log().all();

	}

	@Test(priority = 3, dependsOnMethods = { "updateUser" })
//	@Ignore
	void deleteUser() {
		given().when().delete("https://reqres.in/api/users/" + id).then().statusCode(204).log().all();

	}
}
