package _2_requestBodyType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class RequestPayLoadUsingExtranlJSONfile {

	int id = 0;

	@Test
	@Ignore
	void testPostUsingExternalJSONFile() throws FileNotFoundException {

		File jsonFile = new File("G:\\SoftwareTesting_Stuff\\API Testing\\Restfull_API\\com.resfullApi\\src\\test\\java\\_2_requestBodyType\\body.json");
		FileReader fileRead = new FileReader(jsonFile);
		JSONTokener jt = new JSONTokener(fileRead);
		JSONObject data = new JSONObject(jt);

// we always have to convert json object to string before we pass that to request
		
		given().contentType("application/json").body(data.toString()).when().post("https://reqres.in/api/users").then()
				.statusCode(201).body("name", equalTo("Kiran")).body("job", equalTo("Kiranjob"))
				.header("Content-Type", "application/json; charset=utf-8").log().all();
	}

	@Test(priority = 1)
//	@Ignore
	void createAndStoreIDUsingExternalJSONFile() throws FileNotFoundException {

		File fileName = new File("G:\\SoftwareTesting_Stuff\\API Testing\\Restfull_API\\com.resfullApi\\src\\test\\java\\_2_requestBodyType\\body.json");
		FileReader freader = new FileReader(fileName);
		JSONTokener token = new JSONTokener(freader);
		JSONObject data = new JSONObject(token);

		id = given().contentType("application/json").body(data.toString()).when().post("https://reqres.in/api/users").jsonPath()
				.getInt("id");

	}

	@Test(priority = 2, dependsOnMethods = { "createAndStoreIDUsingExternalJSONFile" })
//	@Ignore
	void deleteData() {
		given().when().delete("https://reqres.in/api/users/" + id).then().statusCode(204).log().all();

	}
}
