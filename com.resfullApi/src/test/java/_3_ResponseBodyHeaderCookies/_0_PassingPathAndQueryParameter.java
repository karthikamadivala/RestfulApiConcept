package _3_ResponseBodyHeaderCookies;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

public class _0_PassingPathAndQueryParameter {

//	URL:https:// reqres.in/api/users?page=2

//  We have to mention the defined the Path parameter in the restfull request method with curly bracets 
//	path parameters are not included in the by default.
//	By default query parameters included in the resfull request so no need to include in the get() method. 	
	
	@Test
	void testPathAndQueryParameter() {
		given()
		.pathParam("myuser", "users")
		.queryParam("page", 2)
		.queryParam("id", 6)
				.when().get("https://reqres.in/api/{myuser}").then().statusCode(200).log().all();

	}
}
