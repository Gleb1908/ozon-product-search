package pages;

import managers.DriverManager;
import managers.PageManager;
import managers.TestPropManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import products.Product;

import java.util.ArrayList;


public class BasePage {

    protected static ArrayList<Product> products = new ArrayList<>();

    /**
     * Менеджер WebDriver
     */
    protected final DriverManager driverManager = DriverManager.getDriverManager();

    /**
     * Менеджер страничек
     */
    protected PageManager pageManager = PageManager.getPageManager();


    /**
     * Объект явного ожидания
     * При применении будет ожидать заданного состояния 7 секунд с интервалом в 1 секунду
     */
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 7, 1000);


    /**
     * Менеджер properties
     */
    protected final TestPropManager props = TestPropManager.getTestPropManager();


    /**
     * Конструктор позволяющий инициализировать все странички и их элементы
     */
    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }


    /**
     * Явное ожидание состояния clickable элемента
     *
     * @param element - веб-элемент который требует проверки clickable
     * @return WebElement - возвращаем тот же веб элемент, что был передан в функцию
     */
    protected WebElement waitUtilElementToBeClickable(WebElement element) {
        return wait
                .withMessage("По элементу '" + element.getText() + "' невозможно кликнуть")
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Явное ожидание того что элемент станет видимым
     *
     * @param element - веб элемент который мы ожидаем что будет виден на странице
     */
    protected WebElement waitUtilElementToBeVisible(WebElement element) {
        return wait
                .withMessage("Элемента '" + element.getText() + "' нет на странице")
                .until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Явное ожидание, что в элементе содержится текст
     * @param element - веб элемент текст которого мы ожидаем что будет виден на странице
     * @param text - текс для проверки
     * @return boolean
     */
    protected boolean waitUtilTextToBePresent(WebElement element, String text) {
        try {
            wait
                    .withMessage("Элемента '" + element.getText() + "' с текстом '" + text + "' нет на странице")
                    .until(ExpectedConditions.textToBePresentInElement(element, text));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    protected boolean isElementPresent (String element) {
        try {
            driverManager.getDriver().findElement(By.xpath(element));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException ignored) {
        }
    }

    public WebElement getElement (String XPathLoc) {
        WebElement element = driverManager.getDriver().findElement(By.xpath(XPathLoc));
        return element;
    }

}
