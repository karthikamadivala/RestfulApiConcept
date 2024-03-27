package _3_ResponseBodyHeaderCookies;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.response.Response;


//Body verifitcation is written in next packages


public class _4_ResponseBody {
	@Test
	void responseBody() {
		given().when().get("https://reqres.in/api/users?page=2").then().statusCode(200)
		.log().body();
		
	}

}
