package base_resources;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;

public class Provider {

	@DataProvider
	public Object[][] dataSet() {
		Map<String, Integer> data = new HashMap<String, Integer>();
		data.put("Cucumber", 5);
		data.put("Brocolli", 4);
		data.put("Beans", 3);

		Object[][] objData = new Object[1][1];
		objData[0][0] = data;

		return objData;
	}
	
}
