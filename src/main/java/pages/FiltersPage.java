package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FiltersPage extends BasePage {

    @FindBy(xpath = "//h3")
    private WebElement checkCategoryName;

    public FiltersPage checkOpenFiltersPage() {
        Assertions.assertEquals("Все фильтры",
                checkCategoryName
                        .getText()
                        .trim(),
                "Заголовок отсутствует или не соответствует требуемому");
        return this;
    }

    public FiltersPage selectFromOrTo(String nameOfField, String fromOrTo, String value) {
        String nameOfFieldLoc = "//div[@class='b6g6']//div[@class='f-subtitle--bold']//span[contains(text(),'" + nameOfField + "')]";
        WebElement nameOfFieldElement = getElement(nameOfFieldLoc);
        nameOfFieldElement.click();
        String fromOrToField = "//div[@class='b6g6']//div[@class='f-subtitle--bold']//span[contains(text(),'" + nameOfField + "')]/../../../..//input[contains(@qa-id,'" + fromOrTo + "')]";
        WebElement fromOrToElement = getElement(fromOrToField);
        fromOrToElement.click();
        fromOrToElement.sendKeys(Keys.CONTROL, "a", Keys.DELETE);
        fromOrToElement.sendKeys(value);
        Assertions.assertEquals(fromOrToElement.getAttribute("value"),value,"Введенный текст не совпадает");
        fromOrToElement.sendKeys(Keys.ENTER);
        nameOfFieldElement.click();
        return this;
    }

    public FiltersPage selectFromAndTo(String nameOfField, String fromValue, String toValue) {
        String nameOfFieldLoc = "//div[@class='b6g6']//div[@class='f-subtitle--bold']//span[contains(text(),'" + nameOfField + "')]";
        WebElement nameOfFieldElement = getElement(nameOfFieldLoc);
        nameOfFieldElement.click();
        String fromField = "//div[@class='b6g6']//div[@class='f-subtitle--bold']//span[contains(text(),'" + nameOfField + "')]/../../../..//input[contains(@qa-id,'from')]";
        WebElement fromElement = getElement(fromField);
        fromElement.click();
        fromElement.sendKeys(Keys.CONTROL, "a", Keys.DELETE);
        fromElement.sendKeys(fromValue);
        Assertions.assertEquals(fromElement.getAttribute("value"),fromValue,"Введенный текст не совпадает");
        fromElement.sendKeys(Keys.ENTER);
        sleep(1);
        String ToField = "//div[@class='b6g6']//div[@class='f-subtitle--bold']//span[contains(text(),'" + nameOfField + "')]/../../../..//input[contains(@qa-id,'to')]";
        WebElement ToElement = getElement(ToField);
        ToElement.click();
        ToElement.sendKeys(Keys.CONTROL, "a", Keys.DELETE);
        ToElement.sendKeys(toValue);
        Assertions.assertEquals(ToElement.getAttribute("value"),toValue,"Введенный текст не совпадает");
        ToElement.sendKeys(Keys.ENTER);
        nameOfFieldElement.click();
        return this;
    }


    public FiltersPage checkBoxField (String nameOfField, String trueOrFalse) {
        String nameOfFieldLoc = "//div[@class='b6g6']//div[@class='f-subtitle--bold']//span[contains(text(),'" + nameOfField + "')]";
        String checkBoxCheckedLoc = "//div[@class='b6g6']//div[@class='f-subtitle--bold']//span[contains(text(),'" + nameOfField + "')]/../../..//label[contains(@class,'OVK5')]";
        if (! String.valueOf(isElementPresent(checkBoxCheckedLoc)).equalsIgnoreCase(trueOrFalse)) {
            driverManager.getDriver().findElement(By.xpath(nameOfFieldLoc)).click();
        }
        return this;
    }

    public FiltersPage chooseProductBrand (String nameOfField, String brandName) {

        String nameOfFieldLoc = "//div[@class='b6g6']//div[@class='f-subtitle--bold']//span[contains(text(),'" + nameOfField + "')]";
        WebElement nameOfFieldElement = getElement(nameOfFieldLoc);
        nameOfFieldElement.click();
        String viewAllLoc = "//div[@class='b6g6']//div[@class='f-subtitle--bold']//span[contains(text(),'" + nameOfField + "')]/../../../..//span[@class='show']";
        WebElement viewAllElement = getElement(viewAllLoc);

        String searchLineLoc = "//div[@class='b6g6']//div[@class='f-subtitle--bold']//span[contains(text(),'" + nameOfField + "')]/../../../..//input[@class='_16XE hlVC']";

        if (! isElementPresent(searchLineLoc)) {
            viewAllElement.click();
        }

        WebElement searchLineElement = getElement(searchLineLoc);
        searchLineElement.click();
        searchLineElement.sendKeys(Keys.CONTROL, "a", Keys.DELETE);
        searchLineElement.sendKeys(brandName);
        Assertions.assertEquals(searchLineElement.getAttribute("value"),brandName,"Введенный текст не совпадает");
        searchLineElement.sendKeys(Keys.ENTER);

        String searchingProductLoc = "//div[@class='vue-portal-target']//span[text()='" + brandName + "']";
        WebElement searchingProductElement = getElement(searchingProductLoc);
        searchingProductElement.click();
        sleep(1);
        nameOfFieldElement.click();
        return this;
    }


    public SearchingResultsPage finishFilterSelection () {
        sleep(1);
        driverManager.getDriver().findElement(By.xpath("//div[@class='_29hd _25gr']//div[@class='kxa6']")).click();
        return pageManager.getSearchingResultsPage();
    }

}
