package step_definitions;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;


public class CommonMethods {
	WebDriver driver;

	public CommonMethods(WebDriver driver) throws IOException{
		this.driver = driver;
	}

	public void waitForPageToLoad() {
		ExpectedCondition<Boolean> expectation = new
				ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(expectation);
		} catch (Throwable error) {

		}
	}

	public boolean waitForVisibility(WebElement targetElement) {
		try{
			WebDriverWait wait = new WebDriverWait(driver, 45);
			wait.until(ExpectedConditions.visibilityOf(targetElement));
			return true;
		}
		catch(TimeoutException e ){
			throw new TimeoutException();
		}
	}

	public boolean waitForInVisibility(By targetElement) {
		try{
			WebDriverWait wait = new WebDriverWait(driver, 45);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(targetElement));
			return true;
		}
		catch(TimeoutException e ){
			throw new TimeoutException();
		}
	}

	public boolean waitForClickability(WebElement targetElement) {
		try{
			WebDriverWait wait = new WebDriverWait(driver, 45);
			wait.until(ExpectedConditions.elementToBeClickable(targetElement));
			return true;
		}
		catch(TimeoutException e ){
			throw new TimeoutException();
		}
	}

	public void waitUntilImageLoaded (final WebElement targetElement) {
		WebDriverWait wait = new WebDriverWait(driver, 45);
		wait.until(
				new Predicate<WebDriver>() {	
					@Override
					public boolean apply(WebDriver driver) {
						return (Boolean) ((JavascriptExecutor)driver).executeScript(""
								+ "return arguments[0].complete "
								+ "&& "
								+ "typeof arguments[0].naturalWidth != \"undefined\" "
								+ "&& arguments[0].naturalWidth > 0",targetElement);
					}
				}
				);
	}
}
