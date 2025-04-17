package MobileSearchAutomation;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Main {
    public static ExtentSparkReporter sparkReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeTest
    public static void driverSetup() {
        MobileSearchAutomation.setupWebDriver("chrome"); // Or "edge"
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//reports//report.html");
        sparkReporter.config().setDocumentTitle("Amazon Mobile Search Automation");
        sparkReporter.config().setReportName("Automation Report");
        sparkReporter.config().setTheme(Theme.STANDARD);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Computer Name", "localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester Name", "Your Name");
        extent.setSystemInfo("os", "Windows10");
        extent.setSystemInfo("Browser name", "Chrome");
    }

    @Test(priority = 1)
    public static void searchMobile() {
        try {
            test = extent.createTest("Testing Mobile Search");
MobileSearchAutomation.openWebSite();
MobileSearchAutomation.searchMobile();
            test.pass("Mobile Search successful", MediaEntityBuilder
                    .createScreenCaptureFromPath(System.getProperty("user.dir") + "/Screenshots/searchResult.png").build());
        } catch (Exception e) {
            test.fail("Test FAILED: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public static void sortByNewestArrivals() {
        try {
            test = extent.createTest("Testing Sort By Newest Arrivals");
            MobileSearchAutomation.sortByNewestArrivals();
            test.pass("Sorted by Newest Arrivals", MediaEntityBuilder
                    .createScreenCaptureFromPath(System.getProperty("user.dir") + "/Screenshots/sortByNewest.png").build());
        } catch (Exception e) {
            test.fail("Test FAILED: " + e.getMessage());
        }
    }

    @Test(priority = 3)
    public static void validateResults() {
        try {
            test = extent.createTest("Testing Validation of Search results");
            MobileSearchAutomation.validateSearchResults();
            test.pass("Search results validated", MediaEntityBuilder
                    .createScreenCaptureFromPath(System.getProperty("user.dir") + "/Screenshots/validateResults.png").build());
        } catch (Exception e) {
            test.fail("Test FAILED: " + e.getMessage());
        }
    }

    @AfterTest
    public static void quitBrowser() {
        extent.flush();
        MobileSearchAutomation.quitBrowser();
    }
}