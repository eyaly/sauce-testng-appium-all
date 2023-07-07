package com.saucelabs.pages;

import com.saucelabs.tests.BaseTest;
import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppPage {

    protected AppiumDriver driver;
    private String platformName;
    private static final Logger logger = LoggerFactory.getLogger(AppPage.class.getName());

    public AppiumDriver getDriver(){
        return driver;
    }
    public AppPage(AppiumDriver driver) {
        this.driver = driver;
        System.out.println("EYAL - In AppPage Ctr ");
        platformName = driver.getCapabilities().getPlatformName().toString().toLowerCase();
        System.out.println("EYAL - In AppPage Ctr. AFTER ");
    }

    public boolean isAndroid(){
        if (platformName.toLowerCase().equals("android" ))
            return true;
        else
           return false;
    }

    public boolean isIOS(){
        if (platformName.toLowerCase().equals("ios" ))
            return true;
        else
            return false;
    }
}
