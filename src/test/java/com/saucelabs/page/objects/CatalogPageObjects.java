package com.saucelabs.page.objects;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;


public class CatalogPageObjects {

    @iOSXCUITFindBy(accessibility = "Sauce Labs Backpack")
    public WebElement productBackPack;

    @iOSXCUITFindBy(className = "XCUIElementTypeCollectionView")
    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/productRV")
    public WebElement allProductsOfCatalog;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/productIV")
    public List<WebElement> productsItems;

    @iOSXCUITFindBy(accessibility = "Products")
    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/productTV")
    public WebElement productsTitle;

}
