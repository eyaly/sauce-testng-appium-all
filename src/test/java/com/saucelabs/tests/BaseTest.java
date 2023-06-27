package com.saucelabs.tests;

import com.saucelabs.manager.AppiumDriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.saucelabs.helpers.Constants.*;

public class BaseTest {

    private AppiumDriverManager appiumDriverManager;
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class.getName());

    public BaseTest() {
        logger.info("BaseTest: Starting");
        appiumDriverManager = new AppiumDriverManager();
    }

    @BeforeMethod
    public void setup(Method method) throws MalformedURLException {

//        System.out.println("BeforeMethod hook");
        logger.info("setup: BeforeMethod hook");

        String methodName = method.getName();
        URL url;

        switch (region) {
            case "us":
                url = new URL(SAUCE_US_URL);
                break;
            case "eu":
            default:
                url = new URL(SAUCE_EU_URL);
                break;
        }

        boolean isBuildCap = false;
        MutableCapabilities caps = new MutableCapabilities();
        MutableCapabilities sauceOptions = new MutableCapabilities();

        for (Map.Entry<String, String> entry : Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getAllParameters().entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();

            if (k.startsWith(SAUCE_CAP)) {
                // Sauce capability
                String sauceCap = k.replaceFirst(SAUCE_CAP, "");
                if (sauceCap.equals("build")) {
                    isBuildCap = true;
                }

                if (v.contains(" ")) {
                    // handle a list such as in tags cap
                    List<String> capList = Arrays.asList(v.split(" "));
                    sauceOptions.setCapability(sauceCap, capList);
                } else {
                    sauceOptions.setCapability(sauceCap, v);
                }
            } else {
                caps.setCapability(k, v);
            }
        }

        sauceOptions.setCapability("username", System.getenv("SAUCE_USERNAME"));
        sauceOptions.setCapability("accessKey", System.getenv("SAUCE_ACCESS_KEY"));
        sauceOptions.setCapability("name", methodName);

        if (!isBuildCap) { //handle build cap
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH");
            String buildLocal = "sauceDemo-" +dateTime.format(formatter);
            String buildVal = System.getenv("BUILD_TAG");
            sauceOptions.setCapability("build", buildVal == null ? buildLocal : buildVal);
        }

        // handle tunnel
        String tunnelName = System.getenv("SAUCE_TUNNEL_NAME");
        if (!(tunnelName == null)) { //handle build cap
            System.out.println("*** tunnel name is " + tunnelName);
            sauceOptions.setCapability("tunnelName", tunnelName);
        }

        caps.setCapability("sauce:options", sauceOptions);

        try {
            appiumDriverManager.startAppiumDriver(caps,url);
        } catch (Exception e) {
            System.out.println("*** Problem to create the driver " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        logger.info("BaseTest teardown: AfterMethod hook");
        try {
            boolean bSuccess = result.isSuccess();
            ((JavascriptExecutor) AppiumDriverManager.getDriver()).executeScript("sauce:job-result=" + (bSuccess ? "passed" : "failed"));
            if (!bSuccess)
                ((JavascriptExecutor) AppiumDriverManager.getDriver()).executeScript("sauce:context=" +result.getThrowable().getMessage());

        } finally {
            logger.info("BaseTest teardown: Release driver");
            appiumDriverManager.stopAppiumDriver();
        }
    }

    public void waiting(int sec){
        try
        {
            Thread.sleep(sec*1000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
}
