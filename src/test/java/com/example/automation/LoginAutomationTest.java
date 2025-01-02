package com.example.automation;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginAutomationTest {

    @Test
    public void testSuccessfulLogin() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            LoginAutomation loginAutomation = new LoginAutomation(driver);

            // Navigate to the login page
            loginAutomation.navigateToLoginPage("https://the-internet.herokuapp.com/login");

            // Perform login with valid credentials
            loginAutomation.login("tomsmith", "SuperSecretPassword!");
            

            // Validate successful login
            String flashMessage = loginAutomation.getFlashMessage();
            assertTrue(flashMessage.contains("You logged into a secure area!"));
            
        } finally {
            driver.quit();
        }
    }
    // @Test
    // public void testSuccessfulLogin1() {
    //     WebDriverManager.chromedriver().setup();
    //     WebDriver driver = new ChromeDriver();

    //     try {
    //         LoginAutomation loginAutomation = new LoginAutomation(driver);

    //         // Navigate to the login page
    //         loginAutomation.navigateToLoginPage("https://the-internet.herokuapp.com/login");

    //         // Perform login with valid credentials
    //         loginAutomation.login1("tomsmith", "SuperSecretPassword!");
            

    //         // Validate successful login
    //         String flashMessage = loginAutomation.getFlashMessage();
    //         assertTrue(flashMessage.contains("You logged into a secure area!"));
            
    //     } finally {
    //         driver.quit();
    //     }
    // }

    @Test
    public void testFailedLogin() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            LoginAutomation loginAutomation = new LoginAutomation(driver);

            // Navigate to the login page
            loginAutomation.navigateToLoginPage("https://the-internet.herokuapp.com/login");

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
