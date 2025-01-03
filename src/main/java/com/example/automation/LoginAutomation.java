package com.example.automation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class LoginAutomation {

    private final WebDriver driver;

    LoginAutomation(WebDriver driver) {
        this.driver = driver;
    }

    void navigateToLoginPage(String url) {
        driver.get(url);
    }

    void login(String username, String password) {
               WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("button.radius"));
        
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        
        loginButton.click();
    }
    
    String getFlashMessage() {
        return driver.findElement(By.cssSelector(".flash")).getText();
    }
}
