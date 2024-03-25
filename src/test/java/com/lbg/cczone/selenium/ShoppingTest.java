package com.lbg.cczone.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
@Sql(scripts = { "classpath:CCZone-schema.sql",
		"classpath:CCZone-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class ShoppingTest {

	private RemoteWebDriver driver;

	@BeforeEach
	void init() {
		this.driver = new EdgeDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@Test
	@Order(1)
	void createCart() {

		this.driver.get("http://localhost:3000/shopping");

		WebElement ClickHere = this.driver.findElement(By.cssSelector("body > div > div:nth-child(3) > p > a"));
		this.driver.executeScript("arguments[0].scrollIntoView(true);", ClickHere);
		this.driver.executeScript("arguments[0].click();", ClickHere);

		WebElement buyer = this.driver.findElement(By.id("buyer"));
		buyer.sendKeys("John Smith");
		WebElement tel = this.driver.findElement(By.id("tel"));
		tel.sendKeys("0789789");
		WebElement address = this.driver.findElement(By.id("address"));
		address.sendKeys("41 South Gyle Crescen");
		WebElement userId = this.driver.findElement(By.id("userId"));
		userId.sendKeys("john");
		WebElement password = this.driver.findElement(By.id("password"));
		tel.sendKeys("john");

		WebElement submit = this.driver.findElement(By.id("itemSubmit"));
		this.driver.executeScript("arguments[0].scrollIntoView(true);", submit);
		this.driver.executeScript("arguments[0].click();", submit);

		WebElement createdCart = this.driver.findElement(
				By.cssSelector("body > div > div:nth-child(3) > div:nth-child(2) > div > div > div > h3 > button > u"));
		assertEquals(true, createdCart.getText().contains("John Smith"));
	}

	@Test
	@Order(2)
	void deleteCart() {
//		

		this.driver.get("http://localhost:3000/shopping");

		WebElement ClickHere = this.driver.findElement(By.cssSelector("body > div > div:nth-child(3) > p > a"));
		this.driver.executeScript("arguments[0].scrollIntoView(true);", ClickHere);
		this.driver.executeScript("arguments[0].click();", ClickHere);

		WebElement buyer = this.driver.findElement(By.id("buyer"));
		buyer.sendKeys("John Smith");
		WebElement tel = this.driver.findElement(By.id("tel"));
		tel.sendKeys("0789789");
		WebElement address = this.driver.findElement(By.id("address"));
		address.sendKeys("41 South Gyle Crescen");
		WebElement userId = this.driver.findElement(By.id("userId"));
		userId.sendKeys("john");
		WebElement password = this.driver.findElement(By.id("password"));
		tel.sendKeys("john");

		WebElement submit = this.driver.findElement(By.id("itemSubmit"));
		this.driver.executeScript("arguments[0].scrollIntoView(true);", submit);
		this.driver.executeScript("arguments[0].click();", submit);

		WebElement deleteShoppingCart = this.driver.findElement(By.cssSelector(
				"body > div > div:nth-child(3) > div > div > div > div > div.card-text > button:nth-child(3)"));
		this.driver.executeScript("arguments[0].scrollIntoView(true);", deleteShoppingCart);
		this.driver.executeScript("arguments[0].click();", deleteShoppingCart);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.invisibilityOf(deleteShoppingCart));
		List<WebElement> carts = this.driver
				.findElements(By.cssSelector("body > div > div:nth-child(3) > div:nth-child(5) > div > div > div"));
		Assertions.assertEquals(0, carts.size());
	}
//	@AfterEach
//	void tearDown() {
//		this.driver.quit();
//	}

}
