package managers;

import pages.FiltersPage;
import pages.SearchingResultsPage;
import pages.HomePage;
import pages.ShoppingBasketPage;

public class PageManager {

    /**
     * Менеджер страничек
     */
    private static PageManager pageManager;

    /**
     * Стартовая страничка
     */
    private HomePage homePage;

    /**
     * Страничка с результатами поиска
     */
    private SearchingResultsPage searchingResultsPage;

    /**
     * Страничка корзины с покупками
     */
    private ShoppingBasketPage shoppingBasketPage;

    /**
     * Страничка корзины с покупками
     */
    private FiltersPage filtersPage;

    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     *
     * @see PageManager#getPageManager()
     */
    private PageManager() {
    }

    /**
     * Ленивая инициализация PageManager
     *
     * @return PageManager
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    /**
     * Ленивая инициализация {@link HomePage}
     *
     * @return HomePage
     */
    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    /**
     * Ленивая инициализация {@link SearchingResultsPage}
     *
     * @return ResultsPage
     */
    public SearchingResultsPage getSearchingResultsPage() {
        if (searchingResultsPage == null) {
            searchingResultsPage = new SearchingResultsPage();
        }
        return searchingResultsPage;
    }

    public ShoppingBasketPage getShoppingBasketPage() {
        if (shoppingBasketPage == null) {
            shoppingBasketPage = new ShoppingBasketPage();
        }
        return shoppingBasketPage;
    }

    public FiltersPage getFiltersPage() {
        if (filtersPage == null) {
            filtersPage = new FiltersPage();
        }
        return filtersPage;
    }

}
