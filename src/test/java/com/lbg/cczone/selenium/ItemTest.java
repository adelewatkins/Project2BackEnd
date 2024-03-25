package com.lbg.cczone.selenium;

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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
@Sql(scripts = { "classpath:CCZone-schema.sql",
		"classpath:CCZone-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class ItemTest {

	private RemoteWebDriver driver;

	@BeforeEach
	void init() {
		this.driver = new EdgeDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@Test
	@Order(2)
	void createItemPage() {
		this.driver.get("http://localhost:3000/item");

		WebElement newItemName = this.driver.findElement(By.id("itemName"));
		newItemName.sendKeys("Biscuit");

		WebElement newItemPrice = this.driver.findElement(By.id("itemPrice"));
		newItemPrice.sendKeys("4.50");

		WebElement newItemQuantity = this.driver.findElement(By.id("itemQuantity"));
		newItemQuantity.sendKeys("5");

		WebElement submit = this.driver.findElement(By.id("itemSubmit"));
//		submit.click();
		this.driver.executeScript("arguments[0].scrollIntoView(true);", submit);
		this.driver.executeScript("arguments[0].click();", submit);

	}

	@Test
	@Order(1)
	void getItems() {
		this.driver.get("http://localhost:3000/item");

		WebElement createdItemName = this.driver.findElement(
				By.cssSelector("body > div > div:nth-child(3) > div > div > div:nth-child(1) > div > p:nth-child(2)"));
		Assertions.assertEquals("ITEM : Cake", createdItemName.getText());

		WebElement createdPrice = this.driver.findElement(
				By.cssSelector("body > div > div:nth-child(3) > div > div > div:nth-child(1) > div > p:nth-child(3)"));
		Assertions.assertEquals("PRICE: £5.99", createdPrice.getText());

	}

	@Test
	@Order(3)
	void deleteItems() {
		this.driver.get("http://localhost:3000/item");

		WebElement Delete = this.driver.findElement(
				By.cssSelector("body > div > div:nth-child(3) > div > div > div:nth-child(1) > button:nth-child(4)"));

		this.driver.executeScript("arguments[0].scrollIntoView(true);", Delete);
		this.driver.executeScript("arguments[0].click();", Delete);
		driver.switchTo().alert().accept();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(Delete));
		List<WebElement> items = this.driver
				.findElements(By.cssSelector("body > div > div:nth-child(3) > div > div > div:nth-child(1) > div"));
		Assertions.assertEquals(1, items.size());
	}
//	@AfterEach
//	void tearDown() {
//		this.driver.quit();
//	}

}