package unit;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.coveros.selenified.exceptions.InvalidBrowserException;
import com.coveros.selenified.selenium.Selenium.Browser;
import com.coveros.selenified.tools.Selenified;

public class SelenifiedTest extends Selenified {

    private String setAppURL = null;
    private String setBrowser = null;
    private String setHub = null;
    private String setProxy = null;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() throws InvalidBrowserException {
        // add some extra capabilities
        extraCapabilities = new DesiredCapabilities();
        extraCapabilities.setCapability("ignoreProtectedModeSettings", true);
        extraCapabilities.setCapability("unexpectedAlertBehaviour", "ignore");
        // save the passed in information
        if (System.getProperty("appURL") != null) {
            setAppURL = System.getProperty("appURL");
            System.clearProperty("appURL");
        }
        if (System.getProperty("browser") != null) {
            setBrowser = System.getProperty("browser");
            System.clearProperty("browser");
        }
        if (System.getProperty("hub") != null) {
            setHub = System.getProperty("hub");
            System.clearProperty("hub");
        }
        if (System.getProperty("proxy") != null) {
            setProxy = System.getProperty("proxy");
            System.clearProperty("proxy");
        }
        super.beforeSuite();
    }

    @AfterClass
    public void restoreBrowser() {
        if (setAppURL != null) {
            System.setProperty("appURL", setAppURL);
        }
        if (setBrowser != null) {
            System.setProperty("browser", setBrowser);
        }
        if (setHub != null) {
            System.setProperty("hub", setHub);
        }
        if (setProxy != null) {
            System.setProperty("proxy", setProxy);
        }
    }

    @BeforeMethod
    public void clearBrowser() {
        System.clearProperty("appURL");
        System.clearProperty("browser");
        System.clearProperty("hub");
        System.clearProperty("proxy");
    }

    @Override
    public void startTest(Object[] dataProvider, Method method, ITestContext test, ITestResult result) {
        // do nothing
    }

    @Override
    public void endTest(Object[] dataProvider, Method method, ITestContext test, ITestResult result) {
        // do nothing
    }

    @Test
    public void initializeSystemTest(ITestContext context) {
        Selenified.initializeSystem();
        Assert.assertEquals(System.getProperty("browser"), "HTMLUNIT");
        Assert.assertNull(getTestSite(this, context));

        System.setProperty("browser", "Chrome");
        System.setProperty("appURL", "http://www.yahoo.com");
        Selenified.initializeSystem();
        Assert.assertEquals(System.getProperty("browser"), "Chrome");
        Assert.assertEquals(getTestSite(this, context), "http://www.yahoo.com");
    }

    @Test(expectedExceptions = InvalidBrowserException.class)
    public void setupTestParametersBadBrowserTest() throws InvalidBrowserException {
        System.setProperty("browser", "BadBrowser");
        Selenified.setupTestParameters();
    }

    @Test
    public void setupTestParametersSingleBrowserTest() throws InvalidBrowserException {
        List<Browser> expectedBrowser = new ArrayList<Browser>();
        expectedBrowser.add(Browser.CHROME);
        System.setProperty("browser", "CHROME");

        Selenified.setupTestParameters();
        Assert.assertEquals(Selenified.browsers, expectedBrowser);
    }

    @Test
    public void setupTestParametersMultipleBrowserTest() throws InvalidBrowserException {
        List<Browser> expectedBrowser = new ArrayList<Browser>();
        expectedBrowser.add(Browser.CHROME);
        expectedBrowser.add(Browser.EDGE);
        System.setProperty("browser", "CHROME,EDGE");

        Selenified.setupTestParameters();
        Assert.assertEquals(Selenified.browsers, expectedBrowser);
    }

    @Test
    public void extraCapabilitiesTest() {
        DesiredCapabilities capability = capabilities.get(0);
        Assert.assertTrue((boolean) capability.getCapability("ignoreProtectedModeSettings"));
        Assert.assertEquals(capability.getCapability("unexpectedAlertBehaviour"), "ignore");
    }

    @Test
    public void siteTest(ITestContext context) {
        setTestSite(this, context, "yahoo");
        Assert.assertEquals(getTestSite(this, context), "yahoo");
        System.setProperty("appURL", "http://www.yahoo.com");
        setTestSite(this, context, "google");
        Assert.assertEquals(getTestSite(this, context), "http://www.yahoo.com");
    }

    @Test
    public void versionTest() {
        setVersion("1.0.0");
        Assert.assertEquals(getVersion(), "1.0.0");
    }

    @Test
    public void authorTest() {
        setAuthor("Max");
        Assert.assertEquals(getAuthor(), "Max");
    }

    @Test
    public void checkPassedInUser() {
        System.setProperty("SERVICES_USER", "hello");
        initializeSystem();
        Assert.assertEquals(getServicesUser(), "");
    }

    @Test
    public void checkPassedInPass() {
        System.setProperty("SERVICES_PASS", "world");
        initializeSystem();
        Assert.assertEquals(getServicesPass(), "");
    }
}