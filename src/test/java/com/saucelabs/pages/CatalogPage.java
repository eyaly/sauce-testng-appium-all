package com.saucelabs.pages;

import com.saucelabs.manager.AppiumDriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import com.saucelabs.page.objects.CatalogPageObjects;

import java.time.Duration;

public class CatalogPage extends AppPage{

    public CatalogPageObjects catalogPageObjects = new CatalogPageObjects();

    private static final Logger logger = LoggerFactory.getLogger(CatalogPage.class.getName());

    public CatalogPage(AppiumDriver driver) {
        super(driver);
        logger.info("Sauce - In CatalogPage Ctr");
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), catalogPageObjects);
//        PageFactory.initElements(driver, this);
    }

    public void selectProduct(String productName) {
        logger.info("Sauce - selectProduct");
        AppiumDriver driver = AppiumDriverManager.getDriver();
    }

    public void addProductToCart() {

        logger.info("Sauce - addProductToCart");

        AppiumDriver driver = AppiumDriverManager.getDriver();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(catalogPageObjects.productsTitle));

        // Select product
        String platformName = driver.getCapabilities().getPlatformName().toString();
        logger.info("Sauce - platform is " + platformName);
        if (platformName.toLowerCase().equals("android" ))
        {
            (catalogPageObjects.productsItems).get(0).click();
        } else
        {
            (catalogPageObjects.productBackPack).click();
        }
        wait.until(ExpectedConditions.visibilityOf(catalogPageObjects.productDetailsScreen));

        // Add to Cart
        (catalogPageObjects.addToCart).click();

        WebElement itemInCart = getItemInTheCart(driver);
        Assert.assertTrue(itemInCart !=null);

    }

    private WebElement getItemInTheCart(AppiumDriver driver) {

        WebElement itemInTheCart = null;
        //wait for the product field to be visible and store that element into a variable
        try {
            itemInTheCart = catalogPageObjects.CartOneItem;
        } catch (TimeoutException e){
            return null;
        }
        return itemInTheCart;
    }
}
