package moneytracker.transaction;

public class Category {
    private String name;
    private String type;

    public Category(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        String symbol = "";
        if (type.equals("INCOME")) {
            symbol = "[I] ";
        } else if (type.equals("EXPENSE")) {
            symbol = "[E] ";
        }
        return symbol + name;
    }
}
