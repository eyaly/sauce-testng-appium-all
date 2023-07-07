package com.saucelabs.manager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;


public class AppiumDriverManager {
    private static ThreadLocal<AppiumDriver> appiumDriver = new ThreadLocal<>();
    private static AtomicInteger appiumThreadCount = new AtomicInteger(0);
    private static final Logger logger = LoggerFactory.getLogger(AppiumDriverManager.class.getName());

    public static AppiumDriver getDriver() {
        return appiumDriver.get();
    }

    protected static void setDriver(AppiumDriver driver) {
        logger.info("AppiumDriverManager: Created AppiumDriver with capabilities: ");
        Capabilities capabilities = driver.getCapabilities();
        capabilities.getCapabilityNames().forEach(key -> logger.info("\t" + key
                + ":: " + capabilities.getCapability(key)));
        appiumDriver.set(driver);
        appiumThreadCount.incrementAndGet();
    }


    private AppiumDriver createAppiumDriver(MutableCapabilities capabilities, URL url)
            throws IOException, MalformedURLException {
        AppiumDriver currentDriverSession;
//        URL url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");
        String strMobilePlatform = capabilities.getCapability("platformName").toString();
        switch (strMobilePlatform.toLowerCase()) {
            case "ios":
                currentDriverSession = new IOSDriver(url, capabilities);
                break;
            case "android":
                currentDriverSession = new AndroidDriver(url, capabilities);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + strMobilePlatform);
        }
        logger.info("Session Created for " + "\n\tSession Id: " + currentDriverSession.getSessionId()
                + "\n\tUDID: " + currentDriverSession.getCapabilities().getCapability("deviceName"));

        return currentDriverSession;
    }


    public void startAppiumDriver(MutableCapabilities capabilities, URL url)
            throws Exception {
        logger.info("Initialise Driver with Capabilities: ");
        capabilities.getCapabilityNames().forEach(key -> logger.info("\t" + key
                + ":: " + capabilities.getCapability(key)));
        AppiumDriver currentDriverSession = createAppiumDriver(capabilities, url);
        AppiumDriverManager.setDriver(currentDriverSession);
    }

    public void stopAppiumDriver() {
        if (AppiumDriverManager.getDriver() != null
                && AppiumDriverManager.getDriver().getSessionId() != null) {
            logger.info("Session Deleting ---- "
                    + AppiumDriverManager.getDriver().getSessionId() + "---"
                    + AppiumDriverManager.getDriver().getCapabilities().getCapability("udid"));
            AppiumDriverManager.getDriver().quit();
            appiumThreadCount.decrementAndGet();
        }
    }

    public static int getThreadCount() {
        return appiumThreadCount.get();
    }
}