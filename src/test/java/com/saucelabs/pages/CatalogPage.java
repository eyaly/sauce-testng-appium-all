package com.saucelabs.pages;

import com.saucelabs.manager.AppiumDriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.saucelabs.page.objects.CatalogPageObjects;

import java.util.List;

public class CatalogPage extends AppPage{

    public CatalogPageObjects catalogPageObjects = new CatalogPageObjects();

    private static final Logger logger = LoggerFactory.getLogger(CatalogPage.class.getName());

    public CatalogPage(AppiumDriver driver) {
        super(driver);
        logger.info("Sauce - In CatalogPage Ctr");
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), catalogPageObjects);
    }

    public void selectProduct(String productName) {
        logger.info("Sauce - selectProduct: " + productName);
        AppiumDriver driver = AppiumDriverManager.getDriver();
        WebElement productToSelect = null;
        try {
            productToSelect = swipeTillProductVisible(productName, catalogPageObjects.allProductsOfCatalog, "up", 2);
            productToSelect.click();
        } catch (Exception e) {
            logger.error("Sauce. " + e.getMessage());
        }
    }

    private WebElement swipeTillProductVisible(String productName, WebElement elementToScroll, String scrollDirection, int maxScrolls){
        int count = 0;
        do {
            WebElement elem = isElementVisible(AppiumBy.accessibilityId(productName));
            if (elem == null)
                scrollElement(elementToScroll, scrollDirection);
            else { // We found the element. Let's celebrate :-)
                logger.info("Found the element after " + count + " scrolls");
                if (isAndroid()) {
                    return elem;
                } else { // for iOS we need to find the image
                    List<WebElement> productItems = elementToScroll.findElements(By.name("ProductItem"));
                    for (WebElement productItem : productItems) {
                        WebElement elemProductTitle = isElementVisibleFromElement(productItem, AppiumBy.accessibilityId(productName));
                        if (elemProductTitle != null){
                            // found the title element -> return the image
                            return productItem.findElement(By.xpath("//XCUIElementTypeImage"));
                        }
                    }
                }
            }
            count++;
        } while (count <= maxScrolls);
        logger.info("Couldn't find the element after " + count + " scrolls");
        return null; //couldn't find the element
    }

//
//    public void addProductToCart() {
//
//        logger.info("Sauce - addProductToCart");
//
//        AppiumDriver driver = AppiumDriverManager.getDriver();
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        wait.until(ExpectedConditions.visibilityOf(catalogPageObjects.productsTitle));
//
//        // Select product
//        if (isAndroid() )
//        {
//            (catalogPageObjects.productsItems).get(0).click();
//        } else
//        {
//            (catalogPageObjects.productBackPack).click();
//        }
//        wait.until(ExpectedConditions.visibilityOf(catalogPageObjects.productDetailsScreen));
//
//        // Add to Cart
//        (catalogPageObjects.addToCart).click();
//
//        WebElement itemInCart = getItemInTheCart(driver);
//        Assert.assertTrue(itemInCart !=null);
//
//    }

//    private WebElement getItemInTheCart(AppiumDriver driver) {
//
//        WebElement itemInTheCart = null;
//        //wait for the product field to be visible and store that element into a variable
//        try {
//            itemInTheCart = catalogPageObjects.CartOneItem;
//        } catch (TimeoutException e){
//            return null;
//        }
//        return itemInTheCart;
//    }

}
