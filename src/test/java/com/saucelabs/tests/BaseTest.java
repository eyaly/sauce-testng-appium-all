package com.saucelabs.tests;

import com.saucelabs.manager.AppiumTestListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.testng.annotations.Listeners;



@Listeners(AppiumTestListener.class)
public class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class.getName());

    public BaseTest() {
        logger.info("BaseTest: Starting");
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
