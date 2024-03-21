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

public class CartTest {

	private RemoteWebDriver driver;

	@BeforeEach
	void init() {
		this.driver = new EdgeDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@Test
	@Order(1)
	void getCartsTest() {

		this.driver.get("http://localhost:3001/admin");
//		this.driver.get("http://localhost:3000/cart");
		WebElement userId = this.driver.findElement(By.id("formGroupEmail"));
		userId.sendKeys("treesa");
		WebElement password = this.driver.findElement(By.id("formGroupPassword"));
		password.sendKeys("treesa");

		WebElement submit = this.driver.findElement(By.cssSelector("body > div > div.card > form > button"));
		this.driver.executeScript("arguments[0].scrollIntoView(true);", submit);
		this.driver.executeScript("arguments[0].click();", submit);

		WebElement createdCartBuyer = this.driver.findElement(
				By.cssSelector("body > div > div:nth-child(3) > div > div:nth-child(4) > div > div > div > div > h3"));
		assertEquals(true, createdCartBuyer.getText().contains("Sally"));

	}

	@Test
	@Order(2)
	void createCartTest() {
		this.driver.get("http://localhost:3000/cart");

		WebElement buyer = this.driver.findElement(By.id("buyer"));
		buyer.sendKeys("Sam");

		WebElement submit = this.driver.findElement(By.cssSelector("#cartCreate > div > div > button"));
//		submit.click();
		this.driver.executeScript("arguments[0].scrollIntoView(true);", submit);
		this.driver.executeScript("arguments[0].click();", submit);

	}

	@Test
	@Order(3)

	void addItemToCartTest() {
		this.driver.get("http://localhost:3000/cart");
		WebElement AddItem = this.driver.findElement(By.id("btn-addItem"));
//AddItem.click();
		this.driver.executeScript("arguments[0].scrollIntoView(true);", AddItem);
		this.driver.executeScript("arguments[0].click();", AddItem);

		WebElement newItemName = this.driver.findElement(By.id("itemName"));
		newItemName.sendKeys("Biscuit");

		WebElement newItemPrice = this.driver.findElement(By.id("itemPrice"));
		newItemPrice.sendKeys("4.50");

		WebElement newItemQuantity = this.driver.findElement(By.id("itemQuantity"));
		newItemQuantity.sendKeys("5");

		WebElement submit = this.driver.findElement(By.cssSelector("body > div > div:nth-child(3) > form > button"));

		this.driver.executeScript("arguments[0].scrollIntoView(true);", submit);
		this.driver.executeScript("arguments[0].click();", submit);

	}

	@Test
	@Order(4)

	void deleteCartTest() {
		this.driver.get("http://localhost:3000/cart");
		WebElement DeleteItem = this.driver.findElement(By.cssSelector(
				"body > div > div:nth-child(3) > div > div:nth-child(4) > div > div:nth-child(1) > div > div > div.card-text > button:nth-child(3)"));

		this.driver.executeScript("arguments[0].scrollIntoView(true);", DeleteItem);
		this.driver.executeScript("arguments[0].click();", DeleteItem);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.invisibilityOf(DeleteItem));
		List<WebElement> carts = this.driver.findElements(By.cssSelector(
				"body > div > div:nth-child(3) > div > div:nth-child(4) > div > div:nth-child(1) > div > div"));
		Assertions.assertEquals(1, carts.size());
	}

	@Test
	@Order(5)

	void updateItemQuantity() {
		this.driver.get("http://localhost:3000/cart");
		WebElement UpdateItem = this.driver.findElement(By.cssSelector(
				"body > div > div:nth-child(3) > div > div:nth-child(4) > div > div:nth-child(1) > div > div > div.card-text > button:nth-child(2)"));
		this.driver.executeScript("arguments[0].scrollIntoView(true);", UpdateItem);
		this.driver.executeScript("arguments[0].click();", UpdateItem);

		WebElement UpdateSelect = this.driver.findElement(
				By.cssSelector("body > div > div:nth-child(3) > div > div:nth-child(10) > div > button:nth-child(3)"));
		this.driver.executeScript("arguments[0].scrollIntoView(true);", UpdateSelect);
		this.driver.executeScript("arguments[0].click();", UpdateSelect);

		WebElement newItemQuantity = this.driver.findElement(By.id("itemQuantity"));
		newItemQuantity.sendKeys("8");

		WebElement UpdateSubmit = this.driver
				.findElement(By.cssSelector("body > div > div:nth-child(3) > form > button"));
		this.driver.executeScript("arguments[0].scrollIntoView(true);", UpdateSubmit);
		this.driver.executeScript("arguments[0].click();", UpdateSubmit);

		WebElement CheckQuantity = this.driver.findElement(
				By.cssSelector("body > div > div:nth-child(3) > div > div:nth-child(10) > div > div > p:nth-child(4)"));
		Assertions.assertEquals("QUANTITY: 8", CheckQuantity.getText());
	}

}
