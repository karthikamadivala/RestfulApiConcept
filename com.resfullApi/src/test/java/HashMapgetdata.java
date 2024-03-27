

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

public class HashMapgetdata {
	@Test
	// Post Method
	void testPostUsingHashMap() {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("name", "Karthik");
		data.put("email", "Karthik@gmail.com");
		data.put("gender", "Male");
		data.put("status", "Active");

		/*
		 * if we want to store array data in the HashMap First we have to store the data
		 * in the array then we have to put the same into the HashMap
		 */
		String CourseArry[] = { "Java", "Selenium", "Python" };
		data.put("course", CourseArry);

		for (Map.Entry<String, Object> entry : data.entrySet()) {
			String key = entry.getKey();
			Object tab = entry.getValue();

			if (tab.getClass().isArray()) {
				String myarray[] = (String[]) tab;
				System.out.println(key + " " + String.join(" ", myarray));
			} else {
				System.out.println(key + " " + tab);
			}
		}
		/*
		 * //Another way of using HAsh MAP To get the Multiple cookie value
		 * System.out.println("1nd way of using HashMAp"); Set<String> keylist =
		 * multiCookies.keySet(); for (String key : keylist) { String cookieValue =
		 * multiCookies.get(key); System.out.println("Cookie Name: " + key +
		 * " Cookie Value: " + cookieValue); }
		 */
		
		
		

	}

}
