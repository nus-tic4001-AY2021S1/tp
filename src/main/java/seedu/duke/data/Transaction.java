package seedu.duke.data;

public class Transaction implements ReadOnlyTransaction {

    private double amount;
    private String description;
    private String date;


    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To display task in String format.
     *
     * @return
     */
    public String toString() {
        return String.format("%s Date:%s Amount:$%.2f", this.getDescription(), this.getDate(), this.getAmount());
    }
}
