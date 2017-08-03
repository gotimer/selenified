package integration;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.coveros.selenified.output.Assert;
import com.coveros.selenified.selenium.Action;
import com.coveros.selenified.selenium.Element;
import com.coveros.selenified.selenium.Selenium.Locator;
import com.coveros.selenified.tools.TestBase;

public class ActionSwitchIT extends TestBase {

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        // set the base URL for the tests here
        setTestSite("http://172.31.2.65/");
        // set the author of the tests here
        setAuthor("Max Saperstone\n<br/>max.saperstone@coveros.com");
        // set the version of the tests or of the software, possibly with a
        // dynamic check
        setVersion("0.0.1");
    }

    @Test(groups = { "integration", "actions", "switch", "frame",
            "virtual" }, description = "An integration test to check the frame method")
    public void selectFrameIntTest() {
        // use this object to manipulate the page
        Action actions = this.actions.get();
        // use this object to verify the page looks as expected
        Assert asserts = this.asserts.get();
        // perform some actions
        asserts.checkElementNotDisplayed(Locator.ID, "message");
        actions.selectFrame(0);
        asserts.checkElementDisplayed(Locator.ID, "message");
        // verify no issues
        finish();
    }

    @Test(groups = { "integration", "actions", "switch", "frame",
            "virtual" }, description = "An integration test to check the frame method")
    public void selectFrameIntNotExistTest() {
        // use this object to manipulate the page
        Action actions = this.actions.get();
        // use this object to verify the page looks as expected
        Assert asserts = this.asserts.get();
        // perform some actions
        asserts.checkElementNotDisplayed(Locator.ID, "message");
        actions.selectFrame(2);
        // verify 1 issue
        finish(1);
    }

    @Test(groups = { "integration", "actions", "switch", "frame",
            "virtual" }, description = "An integration test to check the frame method")
    public void selectFrameNameTest() {
        // use this object to manipulate the page
        Action actions = this.actions.get();
        // use this object to verify the page looks as expected
        Assert asserts = this.asserts.get();
        // perform some actions
        asserts.checkElementNotDisplayed(Locator.ID, "message");
        actions.selectFrame("some_frame");
        asserts.checkElementDisplayed(Locator.ID, "message");
        // verify no issues
        finish();
    }

    @Test(groups = { "integration", "actions", "switch", "frame",
            "virtual" }, description = "An integration test to check the frame method")
    public void selectFrameNameNotExistTest() {
        // use this object to manipulate the page
        Action actions = this.actions.get();
        // use this object to verify the page looks as expected
        Assert asserts = this.asserts.get();
        // perform some actions
        asserts.checkElementNotDisplayed(Locator.ID, "message");
        actions.selectFrame("some_non_existent_frame");
        // verify 1 issue
        finish(1);
    }

    @Test(groups = { "integration", "actions", "switch", "frame",
            "virtual" }, description = "An integration test to check the frame method")
    public void selectFrameTest() {
        // use this object to manipulate the page
        Action actions = this.actions.get();
        // use this object to verify the page looks as expected
        Assert asserts = this.asserts.get();
        // perform some actions
        asserts.checkElementNotDisplayed(Locator.ID, "message");
        actions.selectFrame(Locator.ID, "some_frame");
        asserts.checkElementDisplayed(Locator.ID, "message");
        // verify no issues
        finish();
    }

    @Test(groups = { "integration", "actions", "switch", "frame",
            "virtual" }, description = "An integration negative test to check the frame method")
    public void selectFrameNotExistTest() {
        // use this object to manipulate the page
        Action actions = this.actions.get();
        // perform some actions
        actions.selectFrame(new Element(Locator.ID, "non-existent-element"));
        // verify 2 issues
        finish(2);
    }

    @Test(groups = { "integration", "actions", "switch", "frame",
            "virtual" }, description = "An integration negative test to check the frame method")
    public void selectFrameNotVisibleTest() {
        // use this object to manipulate the page
        Action actions = this.actions.get();
        // perform some actions
        actions.selectFrame(new Element(Locator.ID, "some_other_frame"));
        // verify 2 issues
        finish(2);
    }

    @Test(groups = { "integration", "actions", "switch", "frame",
            "virtual" }, description = "An integration negative test to check the frame method")
    public void selectFrameNotFrameTest() {
        // use this object to manipulate the page
        Action actions = this.actions.get();
        // perform some actions
        actions.selectFrame(new Element(Locator.ID, "scroll_button"));
        // verify no issues
        finish(1);
    }

    @Test(groups = { "integration", "actions", "switch",
            "tab" }, description = "An integration test to check the openTab method")
    public void openTabTest() {
        // use this object to manipulate the page
        Action actions = this.actions.get();
        // use this object to verify the page looks as expected
        Assert asserts = this.asserts.get();
        // perform some actions
        actions.page().openTab("https://www.google.com/");
        asserts.compareURL("https://www.google.com/");
        // verify no issues
        finish();
    }

    @Test(groups = { "integration", "actions", "switch",
            "tab" }, description = "An integration test to check the openTab method")
    public void openEmptyTabTest() {
        // use this object to manipulate the page
        Action actions = this.actions.get();
        // use this object to verify the page looks as expected
        Assert asserts = this.asserts.get();
        // perform some actions
        actions.page().openTab();
        asserts.compareURL(getTestSite());
        // verify no issues
        finish(1);
    }

    @Test(groups = { "integration", "actions", "switch",
            "tab" }, description = "An integration test to check the switchTab method")
    public void switchTabTest() {
        // use this object to manipulate the page
        Action actions = this.actions.get();
        // use this object to verify the page looks as expected
        Assert asserts = this.asserts.get();
        // perform some actions
        actions.page().openTab("https://www.google.com/");
        actions.page().switchNextTab();
        String site = getTestSite().endsWith("/") ? getTestSite() : getTestSite() + "/";
        asserts.compareURL(site);
        // verify no issues
        finish();
    }

    @Test(groups = { "integration", "actions", "switch",
            "tab" }, description = "An integration test to check the closeTab method")
    public void closeTabTest() {
        // use this object to manipulate the page
        Action actions = this.actions.get();
        // use this object to verify the page looks as expected
        Assert asserts = this.asserts.get();
        // perform some actions
        actions.page().openTab("https://www.google.com/");
        actions.page().closeTab();
        String site = getTestSite().endsWith("/") ? getTestSite() : getTestSite() + "/";
        asserts.compareURL(site);
        // verify no issues
        finish();
    }

    @Test(groups = { "integration", "actions", "switch",
            "tab" }, description = "An integration test to check the closeTab method")
    public void closeFirstTabTest() {
        // use this object to manipulate the page
        Action actions = this.actions.get();
        // use this object to verify the page looks as expected
        Assert asserts = this.asserts.get();
        // perform some actions
        actions.page().openTab("https://www.google.com/");
        actions.page().switchPreviousTab();
        actions.page().closeTab();
        asserts.compareURL("https://www.google.com/");
        // verify no issues
        finish();
    }

    @Test(groups = { "integration", "actions", "switch",
            "window" }, description = "An integration test to check the switch method")
    public void switchToNewWindowTest() {
        // use this object to manipulate the page
        Action actions = this.actions.get();
        // use this object to verify the page looks as expected
        Assert asserts = this.asserts.get();
        // perform some actions
        actions.click(Locator.ID, "new_window");
        actions.page().switchToNewWindow();
        asserts.checkTextVisible("You're on the next page");
        // verify no issues
        finish();
    }

    @Test(groups = { "integration", "actions", "switch",
            "window" }, description = "An integration test to check the switch method")
    public void switchToParentWindowTest() {
        // use this object to manipulate the page
        Action actions = this.actions.get();
        // use this object to verify the page looks as expected
        Assert asserts = this.asserts.get();
        // perform some actions
        actions.click(new Element(Locator.ID, "new_window"));
        actions.page().switchToNewWindow();
        asserts.checkTextVisible("You're on the next page");
        actions.page().switchToParentWindow();
        asserts.checkTextNotVisible("You're on the next page");
        // verify no issues
        finish();
    }

    @Test(groups = { "integration", "actions", "switch",
            "window" }, description = "An integration test to check the switch method")
    public void closeCurrentWindowTest() {
        // use this object to manipulate the page
        Action actions = this.actions.get();
        // use this object to verify the page looks as expected
        Assert asserts = this.asserts.get();
        // perform some actions
        actions.click(new Element(Locator.ID, "new_window"));
        actions.page().switchToNewWindow();
        asserts.checkTextVisible("You're on the next page");
        actions.page().closeCurrentWindow();
        asserts.checkTextNotVisible("You're on the next page");
        // verify no issues
        finish();
    }

    @Test(groups = { "integration", "actions", "switch",
            "window" }, description = "An integration test to check the switch method")
    public void closeCurrentWindowNoWindowTest() {
        // use this object to manipulate the page
        Action actions = this.actions.get();
        // use this object to verify the page looks as expected
        Assert asserts = this.asserts.get();
        // perform some actions
        actions.click(new Element(Locator.ID, "new_window"));
        actions.page().switchToNewWindow();
        asserts.checkTextVisible("You're on the next page");
        actions.page().closeCurrentWindow();
        actions.page().closeCurrentWindow();
        // verify 1 issue
        finish(1);
    }
}