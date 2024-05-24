package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "chrome-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless", "--disable-gpu", "--remote-allow-origins=*");
        options.setCapability("ignore-certificate-errors", true);

        WebDriver driver = new ChromeDriver(options);

        testValidAdminLogin(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        testLogout(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        testInvalidLogin(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        testValidLogin(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        testAddToFavourite(driver);
    }
    private static void testValidAdminLogin(WebDriver driver) {
        driver.navigate().to("http://localhost:3000");

        driver.findElement(By.xpath("/html/body/div/nav/div/div/div[3]/a")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        driver.findElement(By.xpath("//*[@id=\"email\"]"))
                .sendKeys("admin@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"password\"]"))
                .sendKeys("asdf1234");

        driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[3]/button")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        if (driver.findElement(By.xpath("/html/body/div/div/header/div/h1")).isDisplayed()) {
            System.out.println("Admin login with Valid Credentials: Passed");
        } else {
            System.out.println("Admin login with Valid Credentials: Failed");
        }
    }

    private static void testLogout(WebDriver driver) {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/nav/div[10]/div[2]/span")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        if (driver.findElement(By.xpath("//*[@id=\"root\"]/nav/div/div/div[3]/a")).getText().equals("Login / Signup")) {
            System.out.println("Logout Test: Passed");
        } else {
            System.out.println("Logout Test: Failed");
        }
    }

    private static void testValidLogin(WebDriver driver) {
        driver.navigate().to("http://localhost:3000");
        driver.findElement(By.xpath("/html/body/div/nav/div/div/div[3]/a")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        driver.findElement(By.xpath("//*[@id=\"email\"]"))
                .sendKeys("rashid@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"password\"]"))
                .sendKeys("asdf1234");

        driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[3]/button")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        if (driver.findElement(By.xpath("/html/body/div/nav/div/div/div[3]/div[2]/div/button/span[2]")).getText().equals("Open user menu")) {
            System.out.println("Login with Valid Credentials: Passed");
        } else {
            System.out.println("Login with Valid Credentials: Failed");
        }
    }

    private static void testInvalidLogin(WebDriver driver) {
        driver.navigate().to("http://localhost:3000");
        driver.findElement(By.xpath("/html/body/div/nav/div/div/div[3]/a")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        driver.findElement(By.xpath("//*[@id=\"email\"]"))
                .sendKeys("asdww@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"password\"]"))
                .sendKeys("asd2wda");

        driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[3]/button")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        if (driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div")).isDisplayed()) {
            System.out.println("Login with Invalid Credentials: Passed");
        } else {
            System.out.println("Login with Invalid Credentials: Failed");
        }
    }

    private static void testAddToFavourite(WebDriver driver) {
        driver.findElement(By.xpath("/html/body/div/div[1]/div/div[1]/div/button[1]")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        if (driver.findElement(By.xpath("//*[@id=\"swal2-title\"]")).getText().equals("Successfully!")) {
            System.out.println("Add to Favourite: Passed");
        } else {
            System.out.println("Add to Favourite: Failed");
        }
    }
}