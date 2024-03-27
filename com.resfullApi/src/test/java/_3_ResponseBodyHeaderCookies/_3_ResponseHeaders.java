package _3_ResponseBodyHeaderCookies;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.response.Response;

public class _3_ResponseHeaders {
	@Test
	@Ignore
	void verifyHeader() {
		given().when().get("https://reqres.in/api/users?page=2").then().statusCode(200)
				.contentType("application/json; charset=utf-8").header("Server", "cloudflare")
				.header("Content-Encoding", "gzip").header("Content-Type", "application/json; charset=utf-8").log()
				.headers();
	}

	@Test
//	@Ignore
	void getHeaderValues() {
		Response res = given().when().get("https://reqres.in/api/users?page=2");

//	    ------------------------------
		System.out.println("Header value using res.header()   :  \n"+ res.header("Content-Type"));
		System.out.println("Header value using res.getHeader():  \n"+ res.getHeader("Content-Type"));		
	
//		------------------------------
		System.out.println("-----Multi headers using res.headers() method----");
		for (Header header : res.headers()) {
			System.out.println(header);
		}
//		-----------------------------
		System.out.println("-----Multi headers using res.getHeaders() method----");
		for (Header header : res.getHeaders()) {
			System.out.println(header);
		}
	}
}
