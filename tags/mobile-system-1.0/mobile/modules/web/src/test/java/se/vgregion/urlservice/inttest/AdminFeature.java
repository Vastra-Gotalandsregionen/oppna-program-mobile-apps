/**
 * Copyright 2010 Västra Götalandsregionen
 *
 *   This library is free software; you can redistribute it and/or modify
 *   it under the terms of version 2.1 of the GNU Lesser General Public
 *   License as published by the Free Software Foundation.
 *
 *   This library is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public
 *   License along with this library; if not, write to the
 *   Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 *   Boston, MA 02111-1307  USA
 */
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