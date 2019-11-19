package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class FlipkartPageObjects extends BaseClass{

	public FlipkartPageObjects(WebDriver driver) {
		super(driver);
	}

	@FindBy(how=How.XPATH, using =  "//button[contains(@class,'_29YdH8')]")
	public  WebElement  closeButton;

	@FindBy(how=How.CLASS_NAME, using =  "LM6RPg")
	public   WebElement searchBar;

	@FindBy(how=How.XPATH, using =  "//ul//li[contains(@class,'_1va75j')]")
	public    List<WebElement> searchSuggestions;

	@FindBy(how=How.CLASS_NAME, using =  "_2yAnYN")
	public   WebElement searchResultText;

	@FindBy(how=How.XPATH, using =  "//img[contains(@class,'_3togXc')]")
	public   List<WebElement> searchResultImages;

	@FindBy(how=How.CLASS_NAME, using =  "_2B_pmu")
	public   List<WebElement> searchResultBrandText;


	@FindBy(how=How.CLASS_NAME, using =  "fPjUPw")
	public   List<WebElement> priceRangeDropdown;

	@FindBy(how=How.CLASS_NAME, using =  "_3vKPvR")
	public   WebElement branchSearch;

	public  By spinner = By.className("_2zN0mv");

	@FindBy(how=How.XPATH, using =  "//div[@title='Puma']")
	public   WebElement puma;

	@FindBy(how=How.XPATH, using =  "//img[contains(@class,'_3togXc')]")
	public   WebElement productImage;

	@FindBy(how=How.CLASS_NAME, using =  "_35KyD6")
	public   WebElement productName;

	@FindBy(how=How.CLASS_NAME, using =  "_1uv9Cb")
	public   WebElement productPrice;

	@FindBy(how=How.CLASS_NAME, using =  "_2a2WU_")
	public   WebElement productSize;

	@FindBy(how=How.XPATH, using =  "(//ul[@class='fUBI-_'])[2]//li[contains(@id,'swatch-')]//a")
	public   List<WebElement> sizeList;

	@FindBy(how=How.XPATH, using =  "//button[text()='BUY NOW']")
	public  WebElement buyNow;
}
