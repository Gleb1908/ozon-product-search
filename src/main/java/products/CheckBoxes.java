package products;

public enum CheckBoxes {

    CHECK ("true"),
    UNCHECK ("false"),
    MONTHLY_CAPITALIZATION ("Ежемесячная капитализация"),
    PARTIAL_WITHDRAWAL ("Частичное снятие");

    private String title;

    CheckBoxes(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
