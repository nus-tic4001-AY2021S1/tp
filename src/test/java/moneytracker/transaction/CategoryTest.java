package moneytracker.transaction;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit tests for Category class.
 */
public class CategoryTest {
    /**
     * Tests the toString override for <code>Income</code> object.
     */
    @Test
    public void testIncomeToString() {
        Category testCategory = new Category("SALARY", "INCOME");
        assertEquals("[I] SALARY", testCategory.toString());
    }

    /**
     * Tests the toString override for <code>Expense</code> object.
     */
    @Test
    public void testExpenseToString() {
        Category testCategory = new Category("FOOD", "EXPENSE");
        assertEquals("[E] FOOD", testCategory.toString());
    }
}
