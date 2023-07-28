package com.saucelabs.pages;

import com.saucelabs.page.objects.MenuPageObjects;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MenuPage extends AppPage{

    public MenuPageObjects menuPageObjects = new MenuPageObjects();

    private static final Logger logger = LoggerFactory.getLogger(MenuPage.class.getName());

    public MenuPage(AppiumDriver driver) {
        super(driver);
        logger.info("Sauce - In MenuPage Ctr");
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), menuPageObjects);
    }


    public int getCartAmount() {
        try {

            WebElement cartBadge = menuPageObjects.cartBadge;
            if (isAndroid()) {
                return Integer.parseInt(cartBadge.getAttribute("text"));
            } else {
                return Integer.parseInt(cartBadge.getAttribute("label"));
            }
        } catch (Exception e){
            return 0;
        }
    }

}
