package selenium.pages;


import com.epam.healenium.SelfHealingDriver;
import com.epam.healenium.annotation.DisableHealing;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageWithFindBy extends BasePage {

    @FindBy(id = "markup-generation-button")
    WebElement generateMarkupBtnId;

    @FindBy(xpath = "//button[contains(@class,'default-btn')]")
    WebElement testButton;

    @FindBy(id = "for-invisible-test")
    WebElement buttonForInvisible;

    @FindBy(id = "field-parent")
    WebElement fieldParent;

    public MainPageWithFindBy(SelfHealingDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Open Main page")
    public MainPageWithFindBy open() {
        driver.get(mainPageUrl);
        return this;
    }

    @Step("Generate markup")
    public MainPageWithFindBy generateMarkup() {
        generateMarkupBtnId.click();
        return this;
    }

    @Step("Click test button")
    public MainPageWithFindBy clickTestButton() {
        testButton.click();
        return this;
    }

    public MainPageWithFindBy clickButtonForInvisible() {
        buttonForInvisible.click();
        return this;
    }

    public boolean checkThatButtonInvisible() {
        try {
            new WebDriverWait(driver, 1)
                    .until(ExpectedConditions.invisibilityOf(buttonForInvisible));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public MainPageWithFindBy hoverOnTestButton() {
        Actions action = new Actions(driver);
        action.moveToElement(testButton).perform();
        return this;
    }

    public String takeValueFromSecondButton() {
        return fieldParent
                .findElement(By.xpath(".//input"))
                .getAttribute("Value");
    }

    public String getAttributeFromTestButton(String attribute) {
        return testButton.getAttribute(attribute);
    }

    @DisableHealing
    public boolean checkLocatorTestButtonDontHealing() {
        try {
            testButton.click();
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

}
