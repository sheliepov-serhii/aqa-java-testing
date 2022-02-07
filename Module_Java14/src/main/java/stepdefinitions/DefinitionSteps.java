package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manager.PageFactoryManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class DefinitionSteps {

    private static final long DEFAULT_TIMEOUT = 150;
    private WebDriver driver;
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private PageFactoryManager pageFactoryManager;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Given("User opens {string} page")
    public void openPage(final String url) {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
    }


    @Then("Product list is displayed")
    public void productListIsDisplayed() {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        Assert.assertTrue(searchResultsPage.isProductListDisplayed());
    }

    @When("User click HamburgerMenu button")
    public void userClickHamburgerMenuButton() {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        homePage.clickPopupCategoryMenu();
    }

    @And("User click a product category")
    public void userClickAProductCategory() {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        homePage.clickComputersHamburgerMenu();
    }

    @And("User selects a subcategory")
    public void userSelectsASubcategory() {
        homePage.clickSubCategoryMenu();
    }

    @And("User click check_box parameter")
    public void userClickCheck_boxParameter() {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.clickHpMonitorsPage();
    }

    @And("User check availability product")
    public void userCheckAvailabilityProduct() {
        searchResultsPage.checkThatSearchResultsContainsWord();
    }

    @And("User checks amount of elements")
    public void userChecksAmountOfElements() {
        pageFactoryManager.getSearchResultsPage().waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchResultsPage.checkImageBlockAmount();
    }

    @And("User check count of images page")
    public void userCheckCountOfImagesPage() {
        pageFactoryManager.getSearchResultsPage().waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.checkCountList();
    }

    @And("User check product list is displayed")
    public void userCheckProductListIsDisplayed() {
        Assert.assertTrue(searchResultsPage.isProductListDisplayed());
    }
}
