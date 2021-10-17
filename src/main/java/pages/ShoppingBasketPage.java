package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import products.Product;

import java.util.List;

public class ShoppingBasketPage extends BasePage {

    @FindBy(xpath = "//div[@class='b4k1 b4k2']")
    private WebElement checkCategoryName;

    @FindBy(xpath = "//div[@class='a7m4']//a[@class='a7n3']//span[contains(text(),'')]")
    private List<WebElement> listProducts;


    /**
     * Проверка открытия страницы, путём проверки заголовка страницы
     *
     * @return MortgagePage - т.е. остаемся на этой странице
     */
    public ShoppingBasketPage checkOpenShoppingBasketPage() {

        if (isElementPresent("//div[@class='g5c7']//button[@class='_1-6r']")) {
            driverManager.getDriver().findElement(By.xpath("//div[@class='g5c7']//button[@class='_1-6r']")).click();
        }
        Assertions.assertEquals("Корзина",
                checkCategoryName
                        .getText()
                        .replaceAll("\\s+", ""),
                "Заголовок отсутствует или не соответствует требуемому");
        return this;
    }

    public ShoppingBasketPage checkingAddedProducts() {

        int countBasket = 0;
        for (WebElement element : listProducts) {
            for (Product product: products) {
                if (element.getText().equals(product.getName())) {
                    countBasket++;
                    break;
                }
            }
        }
        Assertions.assertEquals(products.size(),
                countBasket,
                "Количество добавленных товаров не соответствует количеству товаров в корзине");

        Assertions.assertEquals(products.size(),
                listProducts.size(),
                "Количество добавленных товаров не соответствует количеству товаров в корзине");

        Assertions.assertTrue(isElementPresent("//div[@class='a5a3']//span[contains(text(),'" + products.size() + " товара')]"),
                "Текст 'Ваша корзина n товаров' не совпадает с необходимым");

        return this;
    }

    public ShoppingBasketPage deleteAllProducts () {

        driverManager.getDriver().findElement(By.xpath("//span[@class='b4i9 b4j']")).click();
        driverManager.getDriver().findElement(By.xpath("//div[@class='q6 undefined']//button[@class='_1-6r']")).click();
        Assertions.assertEquals("Корзина пуста",
                driverManager
                        .getDriver()
                        .findElement(By.xpath("//h1[@class='b4k8']"))
                        .getText()
                        .trim(),
                "Из корзины не удалились все товары");
        for (Product product: products) {
            product.setBuyFlag(false);
        }
        Assertions.assertTrue(numberProductInTheBasket() == 0,
                "Все элементы массива помечены как 'удаленные'");
        return this;
    }

    private int numberProductInTheBasket () {
        int amount = 0;
        for (Product product: products) {
            if (product.getBuyFlag()) {
                amount++;
            }
        }
        return amount;
    }

    private int numberDeletedProductInTheBasket () {
        int amount = 0;
        for (Product product: products) {
            if (! product.getBuyFlag()) {
                amount++;
            }
        }
        return amount;
    }



    private boolean isElementPresent (String element) {
        try {
            driverManager.getDriver().findElement(By.xpath(element));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void lineToString () {
        int indexMaxPrice = 0;
        int maxPrice = 0;
        for (Product product: products) {
            if (product.getPrice() > maxPrice) {
                maxPrice = product.getPrice();
                indexMaxPrice = products.indexOf(product);
            }
            System.out.println(
                    "Наименование: " + product.getName()
                            + ". Стоимость: " + product.getPrice()
            );
        }
        System.out.println("Товар с наибольшей ценой: " + products.get(indexMaxPrice).getName() + ". Его стоимость: " + products.get(indexMaxPrice).getPrice());
    }

}
