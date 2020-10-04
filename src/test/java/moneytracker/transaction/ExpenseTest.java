package moneytracker.transaction;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.transaction.Expense;
import moneytracker.transaction.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit tests for Expense class.
 */
public class ExpenseTest {
    @Test
    public void testToString() throws MoneyTrackerException {
        Transaction testExpense = new Expense(58.75, "Expensive dinner",
                "2020-12-25", "FOOD");
        assertEquals("[E] FOOD $58.75 on 25 Dec 2020 (Expensive dinner)", testExpense.toString());
    }
}
