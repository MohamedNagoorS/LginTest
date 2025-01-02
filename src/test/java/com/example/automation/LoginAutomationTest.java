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
            LoginAutomation loginAutomation = new LoginAutomation(driver);

            // Navigate to the login page
            loginAutomation.navigateToLoginPage("https://practicetestautomation.com/practice-test-login/");

            // Perform login with valid credentials
            loginAutomation.login("student", "Password123");
            

            // Validate successful login
            String flashMessage = loginAutomation.getFlashMessage();
            assertTrue(flashMessage.contains("You logged into a secure area!"));
            
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
            loginAutomation.navigateToLoginPage("https://practice.expandtesting.com/login");

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
