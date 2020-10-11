package moneytracker.transaction;

import moneytracker.exception.MoneyTrackerException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionTest {

    private Transaction testExpense1;

    {
        try {
            testExpense1 = new Expense(100.0, "Expensive dinner",
                    "2020-11-25", "FOOD");
        } catch (MoneyTrackerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAmount() {
        assertEquals("100.0", testExpense1.getAmount());
    }

    @Test
    void getDescription() {
        assertEquals("Expensive dinner", testExpense1.getDescription());
    }

    @Test
    void getDate() {
        assertEquals("2020-11-25", testExpense1.getDate());
    }

}