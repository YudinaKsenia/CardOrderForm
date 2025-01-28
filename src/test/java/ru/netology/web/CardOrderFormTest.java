package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CardOrderFormTest {
    private WebDriver driver;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        //options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
        driver = null;
    }

    @Test
    public void cardOrderTest () {

        driver.get("http://localhost:9999");

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Юдина Ксения");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+78001234567");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector(".button")).click();
        WebElement actualElement;
        actualElement = driver.findElement(By.cssSelector("[data-test-id= order-success]"));

        String actualText = actualElement.getText().trim();

        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualText);
        Assertions.assertTrue(actualElement.isDisplayed());
    }
}
