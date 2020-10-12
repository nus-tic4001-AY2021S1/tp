package moneytracker.transaction;

public class Category {
    private String name;
    private String type;

    /**
     * Initializes a <code>Category</code> object.
     *
     * @param name categories' name
     * @param type categories' type INCOME OR EXPENSE
     */
    public Category(String name, String type) {
        assert type.equals("INCOME") || type.equals("EXPENSE") : "type should be INCOME or EXPENSE";
        this.name = name;
        this.type = type;
    }

    /**
     * Get the category name of a <code>category</code> object.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Get the category type of a <code>category</code> object.
     *
     * @return
     */
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
