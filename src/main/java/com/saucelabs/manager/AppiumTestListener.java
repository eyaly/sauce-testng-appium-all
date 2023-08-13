package com.saucelabs.manager;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.saucelabs.helpers.Constants.*;

public final class AppiumTestListener implements ITestListener {

    private AppiumDriverManager appiumDriverManager;
    private static final Logger logger = LoggerFactory.getLogger(AppiumTestListener.class.getName());

    public AppiumTestListener() {
        logger.info("AppiumTestListener: in AppiumTestListener Ctor ");
        appiumDriverManager = new AppiumDriverManager();

    }

    @Override
    public void onTestStart(ITestResult result)  {
        logger.info("Test started: " + result.getName());

        String methodName = result.getName();
        URL url = null;

        try {
            switch (region) {
                case "us":
                    url = new URL(SAUCE_US_URL);
                    break;
                case "eu":
                default:
                    url = new URL(SAUCE_EU_URL);
                    break;
            }
        } catch (Exception e) {
            // do nothing
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

        sauceOptions.setCapability("username", SAUCE_USERNAME);
        sauceOptions.setCapability("accessKey", SAUCE_ACCESS_KEY);
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
            logger.info("*** tunnel name is " + tunnelName);
            sauceOptions.setCapability("tunnelName", tunnelName);
        }

        caps.setCapability("sauce:options", sauceOptions);

        try {
            appiumDriverManager.startAppiumDriver(caps,url);
            logger.info("*** onTestStart. Num of appium threads: " + AppiumDriverManager.getThreadCount());

        } catch (Exception e) {
            logger.error("*** Problem to create the driver " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed: " + result.getName());
        teardown(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.info("Test failed: " + result.getName());
        teardown(result);
    }

    public void teardown( ITestResult result) {
        logger.info("Teardown");
        try {
            boolean bSuccess = result.isSuccess();
            ((JavascriptExecutor) AppiumDriverManager.getDriver()).executeScript("sauce:job-result=" + (bSuccess ? "passed" : "failed"));
            if (!bSuccess)
                ((JavascriptExecutor) AppiumDriverManager.getDriver()).executeScript("sauce:context=" + result.getThrowable().getMessage());

        } finally {
            logger.info("BaseTest teardown: Release driver");
            appiumDriverManager.stopAppiumDriver();
            logger.info("*** teardown. Num of appium threads: " + AppiumDriverManager.getThreadCount());
        }
    }
}
