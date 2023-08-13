package com.saucelabs.tests;

import com.saucelabs.manager.AppiumDriverManager;
import com.saucelabs.pages.CatalogPage;
import com.saucelabs.pages.MenuPage;
import com.saucelabs.pages.ProductDetailsPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ItemDetailsTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ItemDetailsTest.class.getName());
    CatalogPage catalogPage;
    ProductDetailsPage productDetailsPage;
    MenuPage menuPage;

    public ItemDetailsTest() {
        super();
    }

    @Test
    public void add_product_to_cart() {

        String productName = "Sauce Labs Backpack";
        catalogPage = new CatalogPage(AppiumDriverManager.getDriver());
        catalogPage.selectProduct(productName);

        productDetailsPage = new ProductDetailsPage(AppiumDriverManager.getDriver());
        productDetailsPage.addProductToCart();
        waiting(2);
        menuPage = new MenuPage(AppiumDriverManager.getDriver());
        assertThat(1).isEqualTo(menuPage.getCartAmount());

        // For the video
        waiting(5);

    }
}