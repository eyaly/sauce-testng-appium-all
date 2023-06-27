package com.saucelabs.tests;

import com.saucelabs.manager.AppiumDriverManager;
import com.saucelabs.pages.CatalogPage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DemoAppTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(DemoAppTest.class.getName());
    CatalogPage catalogPage;

    // Locators
    // product item
//    By productBackPack =  AppiumBy.accessibilityId("Sauce Lab Back Packs");
//    By productsTitle =  AppiumBy.accessibilityId("Products");
//
//    By productDetailsScreen = AppiumBy.accessibilityId("ProductDetails-screen");
//    By addToCart = AppiumBy.accessibilityId("Add To Cart");
//    By.ByXPath CartOneItem =  new By.ByXPath("//XCUIElementTypeStaticText[@name=\"1\"]");

    public DemoAppTest() {
        super();
    }

    @Test
    public void addProductToCart() {

//        IOSDriver driver = (IOSDriver) AppiumDriverManager.getDriver();
        catalogPage = new CatalogPage(AppiumDriverManager.getDriver());
        catalogPage.addProductToCart();

//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(productsTitle));
//
//        // Select product
//        logger.info("Sauce - Start selectProduct test");
//        driver.findElement(productBackPack).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(productDetailsScreen));
//
//        // Add to Cart
//        driver.findElement(addToCart).click();
//
//        WebElement itemInCart = getItemInTheCart();
//        Assert.assertTrue(itemInCart !=null);
//        // For the video
        waiting(5);

    }

//    public WebElement getItemInTheCart() {
//
//        IOSDriver driver = (IOSDriver) AppiumDriverManager.getDriver();
//
//        WebElement itemInTheCart = null;
//        //wait for the product field to be visible and store that element into a variable
//        try {
//            itemInTheCart = driver.findElement(CartOneItem);
//        } catch (TimeoutException e){
//            return null;
//        }
//        return itemInTheCart;
//    }




}