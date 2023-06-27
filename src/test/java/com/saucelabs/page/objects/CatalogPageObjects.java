package com.saucelabs.page.objects;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;


public class CatalogPageObjects {

    @iOSXCUITFindBy(accessibility = "Sauce Lab Back Packs")
    public WebElement productBackPack;


    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/productIV")
    public List<WebElement> productsItems;

    @iOSXCUITFindBy(accessibility = "Products")
    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/productTV")
    public WebElement productsTitle;

    @iOSXCUITFindBy(accessibility = "ProductDetails-screen")
    @AndroidFindBy(accessibility = "Container for fragments")
    public WebElement productDetailsScreen;

    @iOSXCUITFindBy(accessibility = "Add To Cart")
    @AndroidFindBy(accessibility = "Tap to add product to cart")
    public WebElement addToCart;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"1\"]")
    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/cartTV")
    public WebElement CartOneItem;

//    By productBackPack =  AppiumBy.accessibilityId("Sauce Lab Back Packs");
//    By productsTitle =  AppiumBy.accessibilityId("Products");

//    By productDetailsScreen = AppiumBy.accessibilityId("ProductDetails-screen");
//    By addToCart = AppiumBy.accessibilityId("Add To Cart");
//    By.ByXPath CartOneItem =  new By.ByXPath("//XCUIElementTypeStaticText[@name=\"1\"]");
}
