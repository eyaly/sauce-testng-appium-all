package com.saucelabs.pages;

import com.saucelabs.manager.AppiumDriverManager;
import com.saucelabs.page.objects.ProductDetailsPageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.Duration;

public class ProductDetailsPage extends AppPage{

    public ProductDetailsPageObjects productDetailsPageObjects = new ProductDetailsPageObjects();

    private static final Logger logger = LoggerFactory.getLogger(ProductDetailsPage.class.getName());

    public ProductDetailsPage(AppiumDriver driver) {
        super(driver);
        logger.info("Sauce - In ProductDetailsPage Ctr");
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), productDetailsPageObjects);
    }

    public void addProductToCart() {

        logger.info("Sauce - addProductToCart");

        AppiumDriver driver = AppiumDriverManager.getDriver();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(productDetailsPageObjects.productDetailsScreen));

        // Add to Cart
        (productDetailsPageObjects.addToCart).click();

    }
}
