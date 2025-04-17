package MobileSearchAutomation;


import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotOperations {
	// Method to take a screenshot and save it with the specified file name
	public static void takeScreenshot(WebDriver driver, String fileName) {

		// Cast the WebDriver instance to TakesScreenshot
		TakesScreenshot sc = (TakesScreenshot) driver;
		// Capture the screenshot and store it as a file
		File screenshot = sc.getScreenshotAs(OutputType.FILE);
		// Define the destination file path
		File destination = new File(System.getProperty("user.dir") + "\\Screenshots\\" + fileName + ".png");
		// Rename the screenshot file to the destination file
		screenshot.renameTo(destination);
	}
}
