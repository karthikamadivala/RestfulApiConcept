package _3_ResponseBodyHeaderCookies;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class _1_ResponseLogsAndTypes {

	@Test
	
	private void headerLog() {
		given().when().get("https://www.google.com/").then().log().headers();

	}

	@Test
	@Ignore
	private void bodyLog() {
		given().when().get("https://www.google.com/").then().log().body();

	}

	@Test
	@Ignore
	private void cookiesLog() {
		given().when().get("https://www.google.com/").then().log().cookies();

	}

	@Test
	@Ignore
	private void wholeResponseLog() {
		given().when().get("https://www.google.com/").then().log().all();

	}
}
