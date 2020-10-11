package moneytracker.transaction;

import moneytracker.exception.MoneyTrackerException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit tests for Income class.
 */
public class IncomeTest {
    @Test
    public void testToString() throws MoneyTrackerException {
        Transaction testIncome = new Income(5000, "$1000 bonus!",
                "2020-12-25", "SALARY");
        assertEquals("[I] SALARY $5000.00 on 25 Dec 2020 ($1000 bonus!)", testIncome.toString());
    }
}
