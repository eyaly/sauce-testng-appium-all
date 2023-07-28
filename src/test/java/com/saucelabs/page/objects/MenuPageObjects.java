package com.saucelabs.page.objects;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;


public class MenuPageObjects {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name=\"GrayRoundView Icons\"]/..//XCUIElementTypeStaticText")
    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/cartTV")
    public WebElement cartBadge;




}
