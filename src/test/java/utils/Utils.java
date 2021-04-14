package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import io.qameta.allure.Step;

public class Utils {

	@Step("Get the value of the key '{key}' from configuration.properties file")
	public static String readProperty(String key) {
		//Configure of value
		String value = "";
		//Go to the location of the file
		try (InputStream input = new FileInputStream("./src/test/resources/configuration.properties")) {
			Properties prop = new Properties();
			//Load a properties file
			prop.load(input);
			//Find value by key
			value = prop.getProperty(key);
		} catch (Exception e) {

		}
		return value;
	}
}
