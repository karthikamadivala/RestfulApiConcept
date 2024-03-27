package _7_schemaValidation;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.given;

import java.io.File;
import static org.hamcrest.Matchers.equalTo;

public class SchemaValidation {

	@Test
	void jsonSchemaValidation() {
		given().when().get("https://reqres.in/api/users?page=2").then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JSONSchema.json"));
	}

	@Test
	@Ignore
	void xmlSchemaValidation() {
		given().when().get("http://restapi.adequateshop.com/api/Traveler").then().assertThat()
				.body(RestAssuredMatchers.matchesXsdInClasspath("XMLSchema.xsd"));
	}
}
