package products;

public class Product {

    private String name;
    private int price;
    private boolean buyFlag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean getBuyFlag() {
        return buyFlag;
    }

    public void setBuyFlag(boolean buyFlag) {
        this.buyFlag = buyFlag;
    }

    public Product(String searchedName, int price, boolean buyFlag) {
        this.name = searchedName;
        this.price = price;
    }

    public void lineToString () {
        System.out.println(
                "Наименование: " + getName()
                + ". Стоимость: " + getPrice()
        );
    }

}
