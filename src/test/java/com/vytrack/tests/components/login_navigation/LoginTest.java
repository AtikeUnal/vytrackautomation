package com.vytrack.tests.components.login_navigation;

import com.vytrack.utilities.Sleep;
import com.vytrack.utilities.VerificationUtils;
import com.vytrack.utilities.VyTrackUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {

    /*
    1. Login to Vytrack as a store manager
    2. Verify name of the store manager is displayed on top right
    3. Verify Dashboad page is open
    4. Log out
    5. Login to Vytrack as a sales manager
    6. Verify Dashboad page is open
    7. A different name should be displayed on top right
    8. Log out
    9. Login to Vytrack as a driver
    10. Verify Dashboad/Quick Launchpad page is open
    11. A different name should be displayed on top right

     */
    WebDriver driver;

    @BeforeMethod
    public void before() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://qa3.vytrack.com/user/login");
        driver.manage().window().maximize();
    }

    @Test

    public void loginPositive() throws InterruptedException {
        VyTrackUtil.login(driver, "storemanager70", "UserUser123");

        String managerName = "dropdown-toggle";


        Assert.assertTrue(VerificationUtils.isElementDisplayed(driver, By.className(managerName)));

        //Verify Dashboad page is open
        String title = "oro-subtitle";
        Assert.assertTrue(VerificationUtils.isElementDisplayed(driver, By.className(title)));
        String name = driver.findElement(By.className(managerName)).getText();
        System.out.println(name);
        //Log out
        Sleep.sleep(2);
        logout(managerName);
        Sleep.sleep(2);
//  5. Login to Vytrack as a sales manager
        VyTrackUtil.login(driver, "salesmanager128 ", "UserUser123");
//6. Verify Dashboad page is open
        Assert.assertTrue(VerificationUtils.isElementDisplayed(driver, By.className(title)));
        Sleep.sleep(2);

        //A different name should be displayed on top right

        // Assert.assertFalse(VerificationUtils.verifyEquals(name,driver.findElement(By.className(managerName)));
//    7. A different name should be displayed on top right
        Assert.assertTrue(VerificationUtils.isElementDisplayed(driver, By.className(managerName)), name);

        // 8. Log out

        logout(managerName);

        //    9. Login to Vytrack as a driver


        VyTrackUtil.login(driver, "user27", "UserUser123");
        //    10. Verify Dashboad/Quick Launchpad page is open
        Assert.assertTrue(VerificationUtils.isElementDisplayed(driver, By.className(title)));
        // 11. A different name should be displayed on top right
        Assert.assertTrue(VerificationUtils.isElementDisplayed(driver, By.className(managerName)), name);


    }

    @Test
    public void loginNegative() throws InterruptedException {
/*
Open Vytrack login page
2. Enter valid username and invalid password information
3. Click login
4. Message Invalid user name or password. should be displayed
5. Page title and url should be same

 */

        VyTrackUtil.login(driver, "user27", "useruser12345");
        String alert = driver.findElement(By.xpath("//div[@class='alert alert-error']")).getText();
       // System.out.println(alert);
        Assert.assertEquals(alert, "Invalid user name or password.");

//5. Page title and url should be same
        String url="http://qa3.vytrack.com/user/login";

        Assert.assertEquals(driver.getCurrentUrl(),url);

        Assert.assertEquals(driver.getTitle(),"Login");

    }















    public void logout(String managerName) throws InterruptedException {
        Sleep.sleep(1);
        driver.findElement(By.className(managerName)).click();
        driver.findElement(By.xpath("//a[@class='no-hash']")).click();


    }
}
