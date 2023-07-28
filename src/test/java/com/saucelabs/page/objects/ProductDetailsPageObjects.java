package com.saucelabs.page.objects;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;


public class ProductDetailsPageObjects {

    @iOSXCUITFindBy(accessibility = "ProductDetails-screen")
    @AndroidFindBy(accessibility = "Displays selected product")
    public WebElement productDetailsScreen;

    @iOSXCUITFindBy(accessibility = "Add To Cart")
    @AndroidFindBy(accessibility = "Tap to add product to cart")
    public WebElement addToCart;

}
