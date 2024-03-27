package _3_ResponseBodyHeaderCookies;

import static io.restassured.RestAssured.given;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class _2_ResponseCookies {

	 @Test
	 @Ignore
	void verifyCookies() {
		given().when().get("https://www.google.com/").then()
		// below one should fail since cookies allways change it not constant.
//		.cookie("AEC","Ackid1RAPyl_k2pJ73L24e12I38mfQkmPgOzPwQjfXsoN8V03EJjKMotcxM;Path=/;Domain=.google.com;Secure;HttpOnly;Expires=17/04/2024, 01:27;SameSite=lax")
				.cookie("AEC").log().cookies();
	}

	 @Test
		void getCookieValue() {
			String coockie="";
			
			coockie= given().when().get("https://www.google.com/").cookie("AEC");
			System.out.println(coockie);
		}

	 
	@Test
//	@Ignore
	void getCookiesValues() {
// Created the reference veriable 		
		Response res = given().when().get("https://www.google.com/");
		
//To get the single cookie value
		String singleCookie = res.getCookie("AEC");
		System.out.println("single cookie value: " + singleCookie);

//To get the Multiple cookies value at once
		Map<String, String> multiCookies = res.getCookies();
		
//one way of using HAsh MAP To get the Multiple cookie value		
		System.out.println("1st way of using HashMAp");

		for (Map.Entry<String, String> entry : multiCookies.entrySet()) {
			String mapKey = entry.getKey();
			String mapValue = entry.getValue();
			System.out.println("Cookie Name " + mapKey + " Cookie Value " + mapValue);
		}

	}
	
	
	@Test
	@Ignore
	void getCookiesValuesSecondWay() {
// Created the reference veriable 		
		Response res = given().when().get("https://www.google.com/");
		

//To get the Multiple cookies value at once
		Map<String, String> multiCookies = res.getCookies();
		

//2nd way of using HAsh MAP To get the Multiple cookie value
		System.out.println("2nd way of using HashMAp");
		
		Set<String> keylist = multiCookies.keySet();
		for (String key : keylist) {
			String cookieValue = multiCookies.get(key);
			System.out.println("Cookie Name: " + key + " Cookie Value: " + cookieValue);
		}
	}
}





