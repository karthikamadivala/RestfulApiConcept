package _6_uploadandDownload;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import java.io.File;
import static org.hamcrest.Matchers.equalTo;

public class UploadandDownload {

	@Test
	void singleFileUpload() {
		File myfile = new File("./com.resfullApi/src/test/java/uploadandDownload/text1.txt");
		given().multiPart("file", myfile).contentType("multipart/form-data").when().post("enter the url ").then()
				.statusCode(200).body("filename", equalTo("text1")).log().all();
	}

	@Test
	@Ignore
	private void multiFileUpload() {
		File myfile1 = new File("./com.resfullApi/src/test/java/uploadandDownload/text1.txt");
		File myfile2 = new File("./com.resfullApi/src/test/java/uploadandDownload/text2.txt");
		given().multiPart("files", myfile1).multiPart("files", myfile2).contentType("multipart/form-data").when()
				.post("enter the url ").then().statusCode(200).body("[0].filename", equalTo("text1"))
				.body("[0].filename", equalTo("text2")).log().all();

	}

	@Test
	@Ignore
	void download() {
		given().when().get("URL of the download file").then().statusCode(200).log().all();

	}
}
