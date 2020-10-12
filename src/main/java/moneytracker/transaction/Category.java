package moneytracker.transaction;

/**
 * Represents a category for grouping incomes and expenses.
 * A <code>Category</code> object has a name and type indicating whether
 * it is an income or expense.
 */
public class Category {
    private final String name;
    private final String type;

    /**
     * Initializes a <code>Category</code> object.
     *
     * @param name Name of a <code>Category</code>.
     * @param type Type of a <code>Category</code>, either INCOME or EXPENSE.
     */
    public Category(String name, String type) {
        assert type.equals("INCOME") || type.equals("EXPENSE") : "type should be INCOME or EXPENSE";
        this.name = name;
        this.type = type;
    }

    /**
     * Gets the name of a <code>Category</code>.
     *
     * @return Name of a <code>Category</code>
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the type of a <code>Category</code>.
     *
     * @return Type of a <code>Category</code>.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the String representation of a <code>Category</code>.
     */
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
