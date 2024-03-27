package _4_PrasingJSONResponseBody;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import static org.hamcrest.Matchers.equalTo;

public class PrasingJSONResponse {

	@Test
	@Ignore
	void takingTokenfromRestfulBooker() {
		HashMap mp = new HashMap();
		mp.put("username", "admin");
		mp.put("password", "password123");

//		--------------------
		/*
		 * given().contentType("application/json").body(mp)
		 * .when().post("https://restful-booker.herokuapp.com/auth").then()
		 * .log().body();
		 */
		Response res = given().contentType("application/json").body(mp).when()
				.post("https://restful-booker.herokuapp.com/auth");
		
		System.out.println(res.jsonPath().get("token").toString());
	}

	@Test
	@Ignore
	void assertingJSONResopnseUsingMatcheerMethod() {
//----approch 1-----------------asserting json body using rest asured methods this is not the best approch to test the response----
		given().when().get("https://reqres.in/api/users?page=2").then().statusCode(200)
				.header("Content-type", "application/json; charset=utf-8").body("page", equalTo(2))
				.body("data[0].id", equalTo(7)).body("data[0].first_name", equalTo("Michael")).log().all();

	}

	@Test
	@Ignore
	void assertingJSONResponseJsonPathMethodUsingTestNG() {

//----approch 2----jsonPath()--------asserting json body using TestNG assertion methods ----

		Response res = given().when().get("https://reqres.in/api/users?page=2");

		res.getStatusCode();
		res.getHeader("ContentType");
		res.getBody();
//		res.getCookie("coockie name");
		res.jsonPath().get("data[0].id").toString();

// Validating the responses using TestNG and Response veriable
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getHeader("Content-Type"), "application/json; charset=utf-8");
		Assert.assertEquals(res.jsonPath().get("data[0].id").toString(), "7");
		Assert.assertEquals(res.jsonPath().get("data[0].email").toString(), "michael.lawson@reqres.in");
//		System.out.println(res.jsonPath().get("data.email").toString());

	}

// we cant iterate and assert the json body values using jsonPath method so we go for JSONObject	

	@Test
	@Ignore
	void assertingJSONResponseJSONObjectMethodUsingTestNG() {

// ----approch 3------JSONObject------asserting json body using TestNG assertion methods----
	
		Response res = given().when().get("https://reqres.in/api/users?page=2");
		
		JSONObject jo = new JSONObject(res.asString());
		int arrySize = jo.getJSONArray("data").length();
		
		System.out.println("\nFirst Names");
// Printing the data from the response Body
		for (int i = 0; i < arrySize; i++) {
			String firstName = jo.getJSONArray("data").getJSONObject(i).get("first_name").toString();
			System.out.println(firstName);
		}

// Aserting the response body data
		Boolean status = false;
		for (int i = 0; i < arrySize; i++) {
			String firstName = jo.getJSONArray("data").getJSONObject(i).get("first_name").toString();
			if (firstName.equals("Tobias")) {
				status = true;
				break;
			}
		}
		System.out.println("User is availble in the page");
		Assert.assertEquals(status, true);

// Aserting the response body data
		int sum = 0;
		double sum1 = 0;		
		for (int i = 0; i < arrySize; i++) {
			String id = jo.getJSONArray("data").getJSONObject(i).get("id").toString();
			sum = sum + Integer.parseInt(id);
			sum1 = sum1 + Double.parseDouble(id);
			
		}
		Assert.assertEquals(sum, 57);
		Assert.assertEquals(sum1, 57.0);
	}

	@Test
//	@Ignore
	void additionalExcercise() {
// ----approch 3------------asserting json body using TestNG assertion methods----
		Response res = given().when().get("https://reqres.in/api/users?page=2");
		System.out.println(res); // io.restassured.internal.RestAssuredResponseImpl@13bdf540
		System.out.println(res.toString()); // io.restassured.internal.RestAssuredResponseImpl@13bdf540
		System.out.println(res.getContentType());
		System.out.println(res.jsonPath()); // io.restassured.path.json.JsonPath@4a9860
		System.out.println(res.jsonPath().toString()); // io.restassured.path.json.JsonPath@7ca16adc
		System.out.println(res.jsonPath().get("data.id").toString()); // [7, 8, 9, 10, 11, 12]
		System.out.println(res.jsonPath().get("data").toString());
		System.out.println(res.jsonPath().get(".").toString());
		System.out.println(res.asString());
		System.out.println(res.toString());

		System.out.println("--------------");
		JSONObject jo = new JSONObject(res.asString());
		System.out.println(jo);
		System.out.println("page number " + jo.get("page"));
		System.out.println(jo.getJSONArray("data").getJSONObject(0).get("id"));

		System.out.println("jo length " + jo.length());
		System.out.println("jo data Arry length " + jo.getJSONArray("data").length());
		System.out.println("jo data object length " + jo.getJSONArray("data").getJSONObject(0).length());

	}

}
