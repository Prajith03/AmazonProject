package MobileSearchAutomation;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class MobileSearchAutomation {
    public static WebDriver driver;
    public static WebDriverWait wait;

    public static void setupWebDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
        }
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public static void openWebSite() {
        driver.get("https://www.amazon.in");
        ScreenshotOperations.takeScreenshot(driver, "homepage");
    }

    public static void searchMobile() throws IOException {
        try {
            List<String[]> inputData = ExcelOperations.readData("datas2.xlsx", "input");
            String searchText = inputData.get(0)[0]; // Assuming input in second row, first column
            WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
            searchBox.sendKeys(searchText);
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-search-submit-button")));
            searchButton.click();
            ScreenshotOperations.takeScreenshot(driver, "searchResult");
        } catch (Exception e) {
            System.err.println("Error in searchMobile(): " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void sortByNewestArrivals() {
        try {
            WebElement sortByDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("s-result-sort-select")));
            Select sortBy = new Select(sortByDropdown);
            sortBy.selectByVisibleText("Newest Arrivals");
            ScreenshotOperations.takeScreenshot(driver, "sortByNewest");
        } catch (Exception e) {
            System.err.println("Error in sortByNewestArrivals(): " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void validateSearchResults() throws IOException {
        try {
            WebElement resultSummaryElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-component-type='s-result-info-bar']")));
            String resultSummaryText = resultSummaryElement.getText();
            String[][] outputData = {{"Results", resultSummaryText}};
            ExcelOperations.writeData("datas2.xlsx", "output", outputData);
            ScreenshotOperations.takeScreenshot(driver, "validateResults");
        } catch (Exception e) {
            System.err.println("Error in validateSearchResults(): " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void quitBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}