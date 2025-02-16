package seleniumrp.tests;

import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import selenium.pages.MainPage;
import selenium.pages.MainPageWithFindBy;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class MarkupTest extends BaseTest {

    @Test
    @Attributes(attributes = { @Attribute(key = "healing", value = "true") })
    @DisplayName("Button click with FindBy annotation")
    public void testButtonClickWithFindByAnnotationPage() {
        MainPageWithFindBy mainPage = new MainPageWithFindBy(driver);
        //find test button
        mainPage.open().clickTestButton();
        //confirm Alert
        mainPage.confirmAlert();
        //take a screenshot
        screenshot();

        for (int i = 0; i <= 2; i++) {
            mainPage
                    .generateMarkup() //regenerate Markup
                    .clickTestButton(); //find test button again
            mainPage.confirmAlert();  //confirm Alert again
        }
    }

    @Test
    @Attributes(attributes = { @Attribute(key = "healing", value = "true") })
    @DisplayName("Button click with findElement annotation")
    public void testButtonClickWithFindElementPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .clickTestButton();
        mainPage.confirmAlert();
        for (int i = 0; i <= 2; i++) {
            mainPage
                    .generateMarkup()
                    .clickTestButton();
            mainPage.confirmAlert();
        }
    }

    @Test
    @Attributes(attributes = { @Attribute(key = "healing", value = "false") })
    @DisplayName("Button click with disable healing")
    public void testButtonClickWithDisableHealing() {
        MainPageWithFindBy mainPage = new MainPageWithFindBy(driver);
        mainPage.open()
                .clickTestButton()
                .confirmAlert();
        boolean result = mainPage
                .generateMarkup() //regenerate Markup
                .checkLocatorTestButtonDontHealing(); //find test button again
        assertTrue(result, "The locator was not healed");
    }

    @Test
    @Attributes(attributes = { @Attribute(key = "healing", value = "true") })
    @DisplayName("Select checkboxes with findElements annotation")
    public void testSelectCheckboxes() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        for (int j = 0; j <= 2; j++) {
            mainPage.generateMarkup();
            if (mainPage.displayedText()) {
                for (int i = 0; i <= 5; i++) {
                    mainPage.selectFirstCheckbox();
                }
                boolean result = mainPage.verifyFirstCheckbox();  //should be healed
                assertTrue(result, "Locator for checkbox with findElements has been healed");
            }
        }
    }

    @Test
    @Attributes(attributes = { @Attribute(key = "healing", value = "true") })
    @DisplayName("Button click with find element by id")
    public void testButtonClickWithId() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .clickTestButton();
        mainPage.confirmAlert();
        for (int i = 0; i <= 2; i++) {
            mainPage
                    .generateMarkup()
                    .clickTestGeneratedButton();  //should be healed
        }
    }
}
