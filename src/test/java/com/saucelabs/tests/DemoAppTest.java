package com.saucelabs.tests;

import com.saucelabs.manager.AppiumDriverManager;
import com.saucelabs.pages.CatalogPage;
import com.saucelabs.pages.MenuPage;
import com.saucelabs.pages.ProductDetailsPage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


public class DemoAppTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(DemoAppTest.class.getName());
    CatalogPage catalogPage;
    ProductDetailsPage productDetailsPage;
    MenuPage menuPage;

    public DemoAppTest() {
        super();
    }

    @Test
    public void addProductToCartFromTheTopOfTheCatalog() {

        String productName = "Sauce Labs Backpack";
        addProductToCart(productName);

    }

    @Test
    public void addProductToCartFromTheBottomOfTheCatalog() {

        String productName = "Sauce Labs Onesie";
        addProductToCart(productName);


    }

    private void addProductToCart(String productName) {

        catalogPage = new CatalogPage(AppiumDriverManager.getDriver());
        catalogPage.selectProduct(productName);
        productDetailsPage = new ProductDetailsPage(AppiumDriverManager.getDriver());
        productDetailsPage.addProductToCart();
        waiting(2);
        menuPage = new MenuPage(AppiumDriverManager.getDriver());

        logger.info("Sauce - number of items in the cart are: " + menuPage.getCartAmount());
        // For the video
        waiting(3);
    }

}