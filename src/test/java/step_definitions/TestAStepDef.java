package step_definitions;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageobjects.FlipkartPageObjects;

public class TestAStepDef {
	public WebDriver driver;
	public CommonMethods commonMethods;
	SoftAssert softAssert;
	String searchTerm = "";
	FlipkartPageObjects flipkartPageObjects;

	public TestAStepDef() throws MalformedURLException,IOException {
		driver = Hooks.driver;
		commonMethods = new CommonMethods(driver);
		flipkartPageObjects = new FlipkartPageObjects(driver);
		softAssert = new SoftAssert();
	}


	@When("^I Open \"(.*?)\"$")
	public void i_Open(String url) throws Throwable {
		driver.get("https://www.flipkart.com/");
		commonMethods.waitForPageToLoad();
		flipkartPageObjects.closeButton.click();
	}

	@Then("^I Search for \"(.*?)\" in the search bar from home screen$")
	public void i_Search_for_in_the_search_bar_from_home_screen(String searchTerm) throws Throwable {
		this.searchTerm = searchTerm;
		flipkartPageObjects.searchBar.sendKeys(searchTerm);
		commonMethods.waitForVisibility(flipkartPageObjects.searchSuggestions.get(0));
		String searchText = flipkartPageObjects.searchSuggestions.get(0).getText();	
		softAssert.assertTrue(searchText.contains(searchTerm.toLowerCase()));
		flipkartPageObjects.searchSuggestions.get(0).click();
		commonMethods.waitForPageToLoad();
	}

	@Then("^I Verify the search results page$")
	public void i_Verify_the_search_results_page() throws Throwable {
		String searchResultText = flipkartPageObjects.searchResultText.getText();

		Assert.assertTrue(searchResultText.contains(searchTerm.toLowerCase()));

		int numberOfSearchResults = flipkartPageObjects.searchResultImages.size();
		Assert.assertEquals(numberOfSearchResults, 40);

		for(int i=0;i<4;i++) {
			commonMethods.waitUntilImageLoaded(flipkartPageObjects.searchResultImages.get(i));
			Boolean imagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript(""
					+ "return arguments[0].complete && "
					+ "typeof arguments[0].naturalWidth != \"undefined\" && "
					+ "arguments[0].naturalWidth > 0", 
					flipkartPageObjects.searchResultImages.get(i));
			Assert.assertTrue(imagePresent, "Image didn't load completely");
		}
	}

	@Then("^I Select price filter of max Rs (\\d+)$")
	public void i_Select_price_filter_of_max_Rs(int price) throws Throwable {

		Select priceRangeDropdownMax = new Select(flipkartPageObjects.priceRangeDropdown.get(1));
		priceRangeDropdownMax.selectByValue(Integer.toString(price));

		commonMethods.waitForInVisibility(flipkartPageObjects.spinner);
	}

	@When("^I Search and Apply the brand \"(.*?)\"$")
	public void i_Search_and_Apply_the_brand_Puma(String brand) throws Throwable {
		flipkartPageObjects.branchSearch.sendKeys(brand);

		flipkartPageObjects.puma.click();
		commonMethods.waitForInVisibility(flipkartPageObjects.spinner);
		Assert.assertEquals(flipkartPageObjects.searchResultBrandText.get(0).getText(),brand);
	}

	@Then("^I Open the details page of the first result$")
	public void i_Open_the_details_page_of_the_first_result() throws Throwable {
		flipkartPageObjects.searchResultImages.get(0).click();
		ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windowHandles.get(1));
		commonMethods.waitForPageToLoad();

		commonMethods.waitUntilImageLoaded(flipkartPageObjects.productImage);
		Boolean imagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", flipkartPageObjects.productImage);
		Assert.assertTrue(imagePresent, "Image didn't load completely");

		Assert.assertTrue(flipkartPageObjects.productName.isDisplayed());
		Assert.assertTrue(flipkartPageObjects.productPrice.isDisplayed());
		Assert.assertTrue(flipkartPageObjects.productSize.isDisplayed());
	}

	@Then("^I Change the size$")
	public void i_Change_the_size() throws Throwable {
		Boolean[] sizeAvailable = new Boolean[flipkartPageObjects.sizeList.size()];
		int sizeIndex = 0;
		while(sizeIndex < sizeAvailable.length) {
			if(!flipkartPageObjects.sizeList.get(sizeIndex).getAttribute("class").contains("_1wX7_H")) {
				flipkartPageObjects.sizeList.get(sizeIndex).click();
				commonMethods.waitForVisibility(flipkartPageObjects.buyNow);
				break;
			}
			else {
				sizeIndex++;
				continue;
			}
		}
		if(sizeIndex == sizeAvailable.length-1)
			Assert.fail("None of the sizes are available");
	}

	@Then("^I click on Buy Now$")
	public void i_click_on_Buy_Now() throws Throwable {
		flipkartPageObjects.buyNow.click();
		commonMethods.waitForPageToLoad();
	}
}
