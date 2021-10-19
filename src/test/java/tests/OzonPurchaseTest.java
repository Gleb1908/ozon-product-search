package tests;

import baseTestClass.BaseTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.Listener;

@ExtendWith(Listener.class)
public class OzonPurchaseTest extends BaseTest {

    @Test
    public void ozonPurchaseTest() {

        app.getHomePage()
//                .productSearch("беспроводные наушники")
                .productSearch("iphone")
//                .selectNecessaryFilters()
//                .selectFromOrTo("Цена", "to", "100000")
//                .selectFromAndTo("Цена", "80000", "100000")
//                .checkBoxField("Высокий рейтинг", "true")
//                .chooseProductBrand("Бренды", "Beats")
//                .chooseProductBrand("Бренды","Samsung")
//                .chooseProductBrand("Бренды","Xiaomi")
//                .finishFilterSelection()
//                .checkBoxField("Высокий рейтинг","true")
                .checkProductAvailable(15)
                .openShoppingBasketPage()
                .checkingAddedProducts()
                .deleteAllProducts()
                .lineToString();
    }

}
