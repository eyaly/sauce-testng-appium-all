package com.saucelabs.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;

public class AppPage {

    protected AppiumDriver driver;
    private String platformName;
    private static final Logger logger = LoggerFactory.getLogger(AppPage.class.getName());

    public AppiumDriver getDriver(){
        return driver;
    }
    public AppPage(AppiumDriver driver) {
        this.driver = driver;
        platformName = driver.getCapabilities().getPlatformName().toString().toLowerCase();
    }

    protected void waitForIsShown(WebElement selector){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(selector));
    }

    public boolean isAndroid(){
        return platformName.toLowerCase().equals("android");
    }

    public boolean isIOS(){
        return platformName.toLowerCase().equals("ios");
    }

    public void scrollElement(WebElement el, String direction) {

        // 1. The rectangle of the element to scroll
        Rectangle rect = el.getRect();

        // 2. Determine the x and y position of initial touch
        // we scroll up/down and the x doesn't change
        int centerX = rect.x + (int)(rect.width /2);

        int startY;
        int endY;
        // The starting Y position
        if (direction.equalsIgnoreCase("up")) {
            startY = rect.y + (int) (rect.height * 0.8);
            // The end Y position
            endY = rect.y + (int) (rect.height * 0.2);
        } else { //down
            startY = rect.y + (int) (rect.height * 0.2);
            // The end Y position
            endY = rect.y + (int) (rect.height * 0.8);
        }

        // 3. swipe
        // finger
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tapPoint = new Sequence(finger, 1);
        // Move finger into start position
        tapPoint.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), centerX, startY));
        // Finger comes down into context with screen
        tapPoint.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        // Pause for a little bit
        tapPoint.addAction(new Pause(finger, Duration.ofMillis(100)));
        // Finger move to end position
        tapPoint.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centerX, endY));
        // Finger gets up, off the screen
        tapPoint.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Perform the scroll
        driver.perform(Arrays.asList(tapPoint));

        // always allow scroll action to complete
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    public WebElement isElementVisible(By byElement) {
        try {
            return driver.findElement(byElement);
        } catch (Exception e) {
            return null;
        }
    }

    public WebElement isElementVisibleFromElement(WebElement elementToStart, By byElement) {
        try {
            return elementToStart.findElement(byElement);
        } catch (Exception e) {
            return null;
        }
    }
}
