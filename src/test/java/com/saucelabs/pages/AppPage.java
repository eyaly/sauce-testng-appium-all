package com.saucelabs.pages;

import com.saucelabs.tests.BaseTest;
import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppPage {

    public AppiumDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(AppPage.class.getName());

    public AppPage(AppiumDriver driver) {
        this.driver = driver;
    }
}
