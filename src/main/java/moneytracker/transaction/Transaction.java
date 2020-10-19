package moneytracker.transaction;

import moneytracker.exception.MoneyTrackerException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Represents a transaction that a user has made. A <code>Transaction</code> object has an amount,
 * a description and a date. This class defines the common behaviours that can be inherited by
 * subclasses with specific implementation of a transaction.
 */
public abstract class Transaction {
    private double amount;
    private String description;
    private LocalDate date;


    /**
     * Initializes a <code>Transaction</code> object.
     *
     * @param amount Amount of money in a <code>Transaction</code>.
     * @param description Description of a <code>Transaction</code>.
     * @param date Date of a <code>Transaction</code>.
     * @throws MoneyTrackerException If date format is invalid.
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
     * @param amount Amount of money in a <code>Transaction</code>.
     * @param description Description of a <code>Transaction</code>.
     */
    public Transaction(double amount, String description) {
        this.amount = amount;
        this.description = description;
        this.date = LocalDate.now();
    }

    /**
     * Gets the amount of a <code>Transaction</code>.
     *
     * @return Amount of a <code>Transaction</code>.
     */
    public String getAmount() {
        return String.valueOf(amount);
    }


    /**
     * Gets the typename of a <code>Transaction</code> object.
     *
     * @param description Description of a transaction.
     * @return typename of a <code>Transaction</code> object.
     */
    public String getTypeName(String description) {
        return description.split(" ")[1];
    }

    /**
     * Sets the amount of a <code>Transaction</code>.
     *
     * @param amount Amount of money in a <code>Transaction</code>.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Gets the description of a <code>Transaction</code>.
     *
     * @return Description of a <code>Transaction</code>.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the Description of a <code>Transaction</code>.
     *
     * @param description Description of a <code>Transaction</code>.
     * @throws MoneyTrackerException If date format is invalid.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the date of a <code>Transaction</code>.
     *
     * @return Date of a <code>Transaction</code>.
     */
    public String getDate() {
        return date.toString();
    }

    public LocalDate getLocalDate() {
        return date;
    }

    public double getAmountNumber() {
        return amount;
    }
    /**
     * Sets the date of a <code>Transaction</code>.
     *
     * @param date Date of a <code>Transaction</code>.
     * @throws MoneyTrackerException If date format is invalid.
     */

    public void setDate(String date) throws MoneyTrackerException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.date = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new MoneyTrackerException("Date should be in yyyy-MM-dd format. E.g. 2020-12-25");
        }
    }

    public String setMonth() {
        DateTimeFormatter formattedMonth = DateTimeFormatter.ofPattern("yyyy-MM", Locale.ENGLISH);
        return formattedMonth.format(this.date);
    }

    /**
     * Gets the String representation of a <code>Transaction</code>.
     */
    @Override
    public String toString() {
        String output = "$" + String.format("%.2f", amount) + " on "
                + date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        if (description.isEmpty()) {
            return output;
        } else {
            return output + " (" + description + ")";
        }
    }
}