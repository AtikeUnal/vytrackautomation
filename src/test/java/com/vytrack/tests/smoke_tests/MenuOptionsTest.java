package com.vytrack.tests.smoke_tests;

import com.vytrack.utilities.VyTrackUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MenuOptionsTest {
    WebDriver driver;

    @BeforeMethod
    public void before() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://qa3.vytrack.com/user/login");
        driver.manage().window().maximize();
    }

    @Test
    public void MenuOptionsTest() throws InterruptedException {

/*
1. Login to Vytrack as a driver
2. Navigate to FleetàVehicles, verify page title Car - Entities - System - Car - Entities – System, page name Cars
3. Navigate to Customers à Accounts, verify page title Accounts - Customers, verify page name Accounts
4. Navigate to Customers à Contacts, verify page title Accounts - Customers, verify page name Contacts
5. Navigate to ActivitiesàCalendar Events, verify page title Calendar Events - Activities, page name Calendar Events
 */


        Thread.sleep(2000);

        VyTrackUtil.login(driver,"user27","UserUser123");
        //fleet
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@href='#']/span")).click();
        Thread.sleep(2000);
//<a href="entity/Extend_Entity_Carreservation"><span class="title title-level-2">Vehicles</span></a>

        //vehicle
        driver.findElement(By.xpath("//a[@href='entity/Extend_Entity_Carreservation']/span")).click();

//verify page title car
        Thread.sleep(2000);
        String expected = driver.findElement(By.className("oro-subtitle")).getText();

        String actual = "Cars";
        Thread.sleep(2000);
        Assert.assertEquals(expected, actual);
        Thread.sleep(3000);
        Assert.assertEquals(driver.getTitle(), "Car - Entities - System - Car - Entities - System");

        //navigate to customers accounts

        driver.findElement(By.xpath("(//a[@href='#'])[5]/span")).click();
        driver.findElement(By.xpath("//li[@data-route='oro_account_index']//span")).click();

        Thread.sleep(2000);

//verify page name
        String expected2 = driver.findElement(By.className("oro-subtitle")).getText();
        String actual2 = "Accounts";
        Thread.sleep(2000);
        Assert.assertEquals(expected2, actual2);
//verify page title

        Thread.sleep(3000);
        Assert.assertEquals(driver.getTitle(), "Accounts - Customers");

//Navigate to Customers -> Contacts, verify page title Accounts - Customers, verify page name Contacts
        driver.findElement(By.xpath("(//a[@href='#'])[5]/span")).click();

        driver.findElement(By.xpath("//a[.='Contacts']/span")).click();
        Thread.sleep(2000);

        //verify page name
        String expected3 = driver.findElement(By.className("oro-subtitle")).getText();
        String actual3 = "Contacts";
        Thread.sleep(2000);
        Assert.assertEquals(expected3, actual3);

        //verify page title
        Thread.sleep(3000);
        Assert.assertEquals(driver.getTitle(), "Contacts - Customers");

        Thread.sleep(2000);
        driver.findElement(By.xpath("((//a[@href='#'])/span)[3]")).click();
        driver.findElement(By.xpath("//span[.='Calendar Events']")).click();

//verify page name
        String expected4 = driver.findElement(By.className("oro-subtitle")).getText();
        String actual4 = "Calendar Events";
        Thread.sleep(2000);
        Assert.assertEquals(expected3, actual3);


        //verify page title
        Thread.sleep(3000);
        Assert.assertEquals(driver.getTitle(), "Calendar Events - Activities");
    }

    @AfterMethod
    public void after() {

        // driver.quit();
    }


    @Test
    public void MenuOptionsStore_manager() throws InterruptedException {

    /*
    1. Login to Vytrack as a store manager
2. Navigate to Dashboards à Dashboard, verify page title Dashboard - Dashboards, verify page name Dashboard
3. Navigate to FleetàVehicles, verify page title All - Car - Entities - System - Car - Entities - System, page name All Cars (updated)
4. Navigate to CustomersàAccounts, verify page title All - Accounts – Customers, verify page name All Accounts(updated)
5. Navigate to CustomersàContacts, verify page title All - Contacts - Customers, verify page name All Contacts (updated)
6. Navigate to Sales à Opportunities, verify page title Open Opportunities - Opportunities - Sales, verify page name Open Opportunities
7. Navigate to ActivitiesàCalls verify page title All - Calls - Activities, page name All Calls (updated)
8. Navigate to ActivitiesàCalendar Events, verify page title Calendar Events - Activities, page name All Calendar Events (updated)
     */


        WebElement Username = driver.findElement(By.name("_username"));
        Username.sendKeys("storemanager70");
        WebElement Password = driver.findElement(By.name("_password"));
        Password.sendKeys("UserUser123");
        Thread.sleep(1000);

        WebElement LogIn = driver.findElement(By.id("_submit"));
        LogIn.click();
        Thread.sleep(2000);

        //verift page title

        Assert.assertTrue(driver.getTitle().contains("Dashboard"));
        //verify name
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText(), "Dashboard");


        Thread.sleep(4000);
        driver.findElement(By.xpath("(//span[@class='title title-level-1'])[2]")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("(//span[@class='title title-level-2'])[3]")).click();

        Thread.sleep(3000);

        Assert.assertEquals(driver.getTitle(), "All - Car - Entities - System - Car - Entities - System");
        Thread.sleep(4000);
        Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText(), "All Cars");


//customers- Acounts
        driver.findElement(By.linkText("Customers")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[.='Accounts']")).click();
        Thread.sleep(4000);
        Assert.assertEquals(driver.getTitle(), "All - Accounts - Customers");
        Thread.sleep(4000);
        Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText(), "All Accounts");


//customers-contacts
        driver.findElement(By.linkText("Customers")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[.='Contacts']")).click();
        Thread.sleep(4000);
        Assert.assertEquals(driver.getTitle(), "All - Contacts - Customers");
        Thread.sleep(4000);
        Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText(), "All Contacts");


   //sales-opportunities

        driver.findElement(By.linkText("Sales")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[.='Opportunities']")).click();
        Thread.sleep(4000);
        Assert.assertEquals(driver.getTitle(), "Open Opportunities - Opportunities - Sales");
        Thread.sleep(4000);
        Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText(), "Open Opportunities");

//Activity--call
        driver.findElement(By.linkText("Activities")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[.='Calls']")).click();
        Thread.sleep(4000);
        Assert.assertEquals(driver.getTitle(), "All - Calls - Activities");
        Thread.sleep(4000);
        Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText(), "All Calls");


        //Activities--Calender

        driver.findElement(By.linkText("Activities")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[.='Calendar Events']")).click();
        Thread.sleep(4000);
        Assert.assertEquals(driver.getTitle(), "All - Calendar Events - Activities");
        Thread.sleep(4000);
        Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText(), "All Calendar Events");


    }


}

