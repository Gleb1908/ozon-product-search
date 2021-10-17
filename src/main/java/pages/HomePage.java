package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {


    @FindBy(xpath = "//div[@class='f9j4']/input[@name]")
    private WebElement searchLine;


    /**
     * Поиск товара в строке поиска
     * @param product - имя товара
     * @return ResultsPage
     */
    public SearchingResultsPage productSearch(String product) {
        waitUtilElementToBeClickable(searchLine);
        searchLine.click();
        searchLine.clear();
        searchLine.sendKeys(product);
        Assertions.assertEquals(searchLine.getAttribute("value"),product,"Введенный текст не совпадает");
        searchLine.sendKeys(Keys.ENTER);
        pageManager.getSearchingResultsPage().checkOpenSearchingResultPage(product);
        return pageManager.getSearchingResultsPage();
    }

}
