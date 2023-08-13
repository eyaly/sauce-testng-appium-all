package com.saucelabs.tests;

import com.saucelabs.manager.AppiumDriverManager;
import com.saucelabs.pages.CatalogPage;
import com.saucelabs.pages.MenuPage;
import com.saucelabs.pages.ProductDetailsPage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class CatalogTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(CatalogTest.class.getName());
    CatalogPage catalogPage;
    ProductDetailsPage productDetailsPage;
    MenuPage menuPage;

    public CatalogTest() {
        super();
    }

    @Test
    public void select_product_from_the_top_of_the_catalog() {
        String productName = "Sauce Labs Backpack";
        selectProductFromCatalog(productName);
        isProductSelected(productName);

        // For the video
        waiting(5);
    }

    @Test
    public void select_product_from_the_bottom_of_the_catalog() {
        String productName = "Sauce Labs Onesie";
        selectProductFromCatalog(productName);
        isProductSelected(productName);

        // For the video
        waiting(5);
    }

    private void isProductSelected(String productName) {
        productDetailsPage = new ProductDetailsPage(AppiumDriverManager.getDriver());
        assertThat(productName).isEqualTo(productDetailsPage.getProductTitle());
    }

    private void selectProductFromCatalog(String productName) {

        catalogPage = new CatalogPage(AppiumDriverManager.getDriver());
        catalogPage.selectProduct(productName);

    }

}