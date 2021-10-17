package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import products.Product;

import java.util.List;

public class SearchingResultsPage extends BasePage {

    @FindBy(xpath = "//div[@class='b6r7']//strong")
    private WebElement checkCategoryName;

    @FindBy(xpath = "//div[@class = 'bi3 bi5']")
    private List<WebElement> listProducts;

    @FindBy(xpath = "//div[@qa-id='next']")
    private WebElement buttonNext;



    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     * @return MortgagePage - т.е. остаемся на этой странице
     */
    public SearchingResultsPage checkOpenSearchingResultPage(String product) {
        Assertions.assertEquals(product,
                checkCategoryName.getText(),
                "Заголовок отсутствует или не соответствует требуемому");
        return this;
    }


    /**
     * Проверка наличия определенного товара в результате поиска
     *
     * @return ProductPage
     */
    public SearchingResultsPage checkProductAvailable(int amount) {
        boolean flag = true;
        while (flag) {
            addProduct(amount);
            if (! isElementPresent(buttonNext.getText())) {
                flag = false;
            }
            if (! (products.size()<amount)) {
                break;
            }
            else {
                buttonNext.click();
            }
        }
        return this;
    }

    private void addProduct(int amount) {

        for (WebElement element : listProducts) {
//            Assertions.assertTrue(waitUtilElementToBeVisible(element).isDisplayed(),"Элемент не загрузился");
            if (products.size()<amount) {
                if (listProducts.indexOf(element) % 2 == 0) {
                    try {
                        if (isElementPresent("//div[@class = 'bi3 bi5']//div[@class='_29hd _2JpD']")) {
                            element.findElement(By.xpath(".//div[@class='_29hd _2JpD']")).click();
                            String name = element.findElement(By.xpath(".//a[@class='tile-hover-target bj5']//span[text()]")).getText();
                            String priceStr = element.findElement(By.xpath(".//div[@class='bi8']//span[@class='_2DV4 _17o0 _1v1b']")).getText();
                            int price = Integer.parseInt(priceStr
                                    .replaceAll("[^0-9]", ""));
                            products.add(new Product(name, price, true));
                            Assertions.assertEquals(products.size(),
                                    numberOfItemInTheCart(),
                                    "Количество добавленных товаров не совпадает со значением около значка корзины");
                        }
                    } catch (NoSuchElementException ignored) {
                    }
                }
            } else {
                return;
            }
        }
    }

    private int numberOfItemInTheCart () {
        String numberStr = driverManager.getDriver().findElement(By.xpath(".//span[@class = 'f-caption--bold e3i0']")).getText();
        int number = Integer.parseInt(numberStr
                .replaceAll("\\s+", "")
                .trim());
        return number;
    }

    public SearchingResultsPage checkBoxField (String name, String trueOrFalse) {
        String checkBoxLoc = "//div[@class='g1h7 filter-block']//div[contains(@value,'" + name + "')]//div[@class='_3eqP']";
        String checkBoxCheckedLoc = "//div[@class='g1h7 filter-block']//div[contains(@value,'" + name + "')]//label[contains(@class,'OVK5')]";
        if (! String.valueOf(isElementPresent(checkBoxCheckedLoc)).equalsIgnoreCase(trueOrFalse)) {
            driverManager.getDriver().findElement(By.xpath(checkBoxLoc)).click();
        }
        return this;
    }

    private boolean isElementPresent (String element) {
        try {
            driverManager.getDriver().findElement(By.xpath(element));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public SearchingResultsPage selectValueFromOrTo(String nameOfField, String fromOrTo, String value) {
        String elementLoc = "//div[@class='g1h7 filter-block']//div[contains(text(),'" + nameOfField + "')]/..//input[contains(@qa-id,'range-" + fromOrTo + "')]";
        WebElement element = driverManager.getDriver().findElement(By.xpath(elementLoc));
        element.click();
        element.sendKeys(Keys.CONTROL, "a", Keys.DELETE);
        element.sendKeys(value);
        Assertions.assertEquals(element.getAttribute("value"),value,"Введенный текст не совпадает");
        element.sendKeys(Keys.ENTER);
        return this;
    }

    public SearchingResultsPage sleep() {
        try {
            Thread.sleep(8000);
        } catch (InterruptedException ignored) {
        }
        return this;
    }

    public ShoppingBasketPage openShoppingBasketPage () {
        driverManager.getDriver().findElement(By.xpath("//a[@href='/cart']")).click();
        pageManager.getShoppingBasketPage().checkOpenShoppingBasketPage();
        return pageManager.getShoppingBasketPage();
    }

}