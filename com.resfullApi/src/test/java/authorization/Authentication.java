package authorization;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import _2_requestBodyType.Pojo_PayloadRequest;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.given;

import java.io.File;
import static org.hamcrest.Matchers.equalTo;

public class Authentication {

//	@Test
	void baicAuthentication() {
		given().auth().basic("postman", "password").when().get("https://postman-echo.com/basic-auth").then()
				.statusCode(200).body("authenticated", equalTo(true)).log().all();
	}

//	@Test
	void digestAuthentication() {
		given().auth().digest("postman", "password").when().get("https://postman-echo.com/basic-auth").then()
				.statusCode(200).body("authenticated", equalTo(true)).log().all();
	}

//	@Test
	void preemptiveAuthentication() {
		given().auth().preemptive().basic("postman", "password").when().get("https://postman-echo.com/basic-auth")
				.then().statusCode(200).body("authenticated", equalTo(true)).log().all();
	}

//	@Test
	private void bearerTokenAuthentication() {

		String bearerToken = "c1ceec6a256dd26c041fb682a2ff7b0eda30d820d47e926e56c953991b487001";

		given().header("Authorization", "Bearer" + bearerToken).contentType("appplication/json").when()
				.get("https://simple-books-api.glitch.me/books").then().log().all();

	}

	@Test
	void oauth1Authentication() {
		given().auth().oauth("consumerKey", "consumerSecret", "accessToken", "secretToken").when().get().then().log()
				.all();
	}

	@Test
	void oauth2Authentication() {
		given().auth().oauth2(null, null).when().get().then().log().all();
	}

}
