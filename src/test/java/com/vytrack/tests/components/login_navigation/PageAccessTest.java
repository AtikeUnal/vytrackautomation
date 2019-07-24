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

public class PageAccessTest {

    /*
    1. Login to Vytrack as a store manager
2. Verify that you can access Vehicle contracts page
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
    public void VehicleContractsTestStoreManager() throws InterruptedException {
        VyTrackUtil.login(driver, "storemanager70", "UserUser123");
        Sleep.sleep(2);
        VyTrackUtil.selectMenuOption(driver, "Fleet", "Vehicle Contracts");


        Sleep.sleep(2);
    Assert.assertEquals(driver.getTitle(),"All - Vehicle Contract - Entities - System - Car - Entities - System");

    }

    @Test
    public void VehicleContractsTestSalesManager() throws InterruptedException {
/*
1. Login to Vytrack as a sales manager
2. Verify that you can access Vehicle contracts page

 */
        VyTrackUtil.login(driver, "salesmanager256", "UserUser123");
        Sleep.sleep(2);
        VyTrackUtil.selectMenuOption(driver, "Fleet", "Vehicle Contracts");
        Assert.assertEquals(driver.getTitle(),"All - Vehicle Contract - Entities - System - Car - Entities - System");

    }


    @Test

    public void VehicleContractsTruck() throws InterruptedException {
    /*
    Login to Vytrack as a driver
2. Verify that you cannot access Vehicle contracts page
3. Message You do not have permission to perform this action. should be displayed
     */


        VyTrackUtil.login(driver, "user27", "UserUser123");

        VyTrackUtil.selectMenuOption(driver, "Fleet", "Vehicle Contracts");
        String alert=driver.findElement(By.xpath("//div[.='You do not have permission to perform this action.']")).getText();

        Assert.assertEquals(alert,"You do not have permission to perform this action.");

    }
}