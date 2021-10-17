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
                .productSearch("iphone")
//                .selectValueFromOrTo("Цена", "to", "100000")
//                .checkBoxField("Высокий рейтинг","true")
//                .sleep()
                .checkProductAvailable(3)
                .openShoppingBasketPage()
                .checkingAddedProducts()
                .deleteAllProducts()
                .lineToString();
    }

}
