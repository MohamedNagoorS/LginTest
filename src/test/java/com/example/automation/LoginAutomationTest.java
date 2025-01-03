package com.example.automation;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

class LoginAutomationTest {

    @Test
    void testSuccessfulLogin() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the login page
            driver.get("https://the-internet.herokuapp.com/login");

            // Locate the username and password fields
            WebElement usernameField = driver.findElement(By.id("username"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.cssSelector("button.radius"));

            // Perform login with invalid credentials
            usernameField.sendKeys("invalidUser");
            passwordField.sendKeys("invalidPassword");
            loginButton.click();

            // Validate failed login
            String expectedMessage = "Your username is invalid!";
            String actualMessage = driver.findElement(By.cssSelector(".flash.error")).getText();
            assertEquals(true, actualMessage.contains(expectedMessage));
        } finally {
            driver.quit();
        }
    }


    @Test
    void testFailedLogin() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            LoginAutomation loginAutomation = new LoginAutomation(driver);

            // Navigate to the login page
           driver.get("https://the-internet.herokuapp.com/login");

            // Perform login with invalid credentials
            loginAutomation.login("invalidUser", "invalidPassword");

            // Validate failed login
            String flashMessage = loginAutomation.getFlashMessage();
            assertTrue(flashMessage.contains("Your username is invalid!"));
        } finally {
            driver.quit();
        }
    }
}
