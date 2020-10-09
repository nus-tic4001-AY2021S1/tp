package moneytracker.transaction;

import moneytracker.exception.MoneyTrackerException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a transaction that a user has made. A <code>Transaction</code> object has an amount,
 * a description and a date. This class defines the common behaviours that can be inherited by
 * subclasses with specific implementation of a transaction.
 */
public abstract class Transaction {
    private final double amount;
    private final String description;
    private LocalDate date;

    /**
     * Initializes a <code>Transaction</code> object.
     *
     * @param amount Amount of money in a transaction.
     * @param description Description of a transaction.
     * @param date Date of a transaction.
     */
    public Transaction(double amount, String description, String date) throws MoneyTrackerException {
        this.amount = amount;
        this.description = description;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.date = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new MoneyTrackerException("Date should be in yyyy-MM-dd format. E.g. 2020-12-25");
        }
    }

    /**
     * Initializes a <code>Transaction</code> object.
     *
     * @param amount Amount of money in a transaction.
     * @param description Description of a transaction.
     */
    public Transaction(double amount, String description) {
        this.amount = amount;
        this.description = description;
        this.date = LocalDate.now();
    }

    @Override
    public String toString() {
        return "$" + String.format("%.2f", amount) + " on "
                + date.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                + " (" + description + ")";
    }
}
