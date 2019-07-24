package com.vytrack.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VyTrackUtil {

        public static void login(WebDriver driver, String username, String password) throws InterruptedException {
            WebElement Username = driver.findElement(By.name("_username"));
            Username.sendKeys(username);
        Thread.sleep(2000);
            WebElement Password = driver.findElement(By.name("_password"));
            Password.sendKeys(password);
            Thread.sleep(2000);
            WebElement LogIn = driver.findElement(By.id("_submit"));
            LogIn.click();
        }


        public static void selectMenuOption(WebDriver driver, String tab, String module) throws InterruptedException {
            // click on tab
            String tabXpath = "//span[@class='title title-level-1' and contains(text(), '" + tab + "')]";
            Thread.sleep(3000);
            driver.findElement(By.xpath(tabXpath)).click();
            Thread.sleep(1000);
            // click on module
            String moduleXpath = "//span[@class='title title-level-2' and contains(text(), '" + module + "')]";
            driver.findElement(By.xpath(moduleXpath)).click();
            Thread.sleep(2000);
        }


            public static void waitForUIOverlay(){
                WebDriverWait wait = new WebDriverWait(Driver.get(), 5);
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loader-mask.shown")));

            }
        }
