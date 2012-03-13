package se.vgregion.urlservice.inttest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import cuke4duke.annotation.After;
import cuke4duke.annotation.Before;
import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;

public class AdminFeature extends IntegrationTestTemplate {
    
    private WebDriver d;

    @Before
    public void setupDriver() throws Exception {
        setServer();
        
        d = new FirefoxDriver();
    }

    @Given("^att jag är på admin sidan$")
    public void visit() {
        d.get(hubUrl + "/admin");
    }

    @When("^I search for \"([^\"]*)\"$")
    public void search(String query) {
        WebElement searchField = d.findElement(By.name("q"));
        searchField.sendKeys(query);
        // WebDriver will find the containing form for us from the searchField element
        searchField.submit();
    }
    
    @Then("^ska jag se (\\d+) skrivare$")
    public void shouldSee(int noOfPrinters) {
        assertEquals(noOfPrinters, d.findElements(By.className("printer")).size());
    }

    @When("^jag klickar på QR länken$")
    public void clickOnQr() {
        d.findElement(By.className("qr")).click();
    }

    @Then("^ska jag se en QR kod$")
    public void seeQr() {
        // TODO Firefox specific?
        assertTrue(d.getTitle().contains("PNG Image"));
    }
    
    @After
    public void closeBrowser() throws Exception {
        stopServer();
        
        if (d != null) {
            d.close();
            d.quit();
        }
    }

}