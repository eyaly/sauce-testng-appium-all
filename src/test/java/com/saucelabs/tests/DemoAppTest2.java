package com.saucelabs.tests;

import com.saucelabs.manager.AppiumDriverManager;
import com.saucelabs.pages.CatalogPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class DemoAppTest2 extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(DemoAppTest2.class.getName());
    CatalogPage catalogPage;

    public DemoAppTest2() {
        super();
    }

    @Test
    public void addProductToCart2() {

        catalogPage = new CatalogPage(AppiumDriverManager.getDriver());
        System.out.println("Waiting2...");
        waiting(20);
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