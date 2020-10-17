package moneytracker.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.transaction.Category;
import moneytracker.transaction.Transaction;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests for Parser class.
 */
class ParserTest {
    /**
     * Tests the createIncomeCategory method with valid input.
     */
    @Test
    public void testCreateIncomeCategory() throws MoneyTrackerException {
        Category testCategory = Parser.createIncomeCategory("addcati salary");
        assertEquals("[I] SALARY", testCategory.toString());
    }

    /**
     * Tests the createIncomeCategory method with incomplete input.
     */
    @Test
    public void testCreateIncomeCategoryWithMissingValue() {
        String errorMessage = "";
        try {
            Parser.createIncomeCategory("addcati");
        } catch (MoneyTrackerException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("The income category name is missing.", errorMessage);
    }

    /**
     * Tests the createExpenseCategory method with valid input.
     */
    @Test
    public void testCreateExpenseCategory() throws MoneyTrackerException {
        Category testCategory = Parser.createExpenseCategory("addcate food");
        assertEquals("[E] FOOD", testCategory.toString());
    }

    /**
     * Tests the createExpenseCategory method with incomplete input.
     */
    @Test
    public void testCreateExpenseCategoryWithMissingValue() {
        String errorMessage = "";
        try {
            Parser.createExpenseCategory("addcate");
        } catch (MoneyTrackerException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("The expense category name is missing.", errorMessage);
    }

    /**
     * Tests the createIncome method with valid input.
     */
    @Test
    public void testCreateIncome() throws MoneyTrackerException {
        Transaction testIncome = Parser.createIncome("addi /a5000 /cSalary /d2020-12-25 /e$1000 bonus!");
        assertEquals("[I] SALARY $5000.00 on 25 Dec 2020 ($1000 bonus!)", testIncome.toString());
    }

    /**
     * Tests the createIncome method with incomplete input.
     */
    @Test
    public void testCreateIncomeWithMissingValue() {
        String errorMessage = "";
        try {
            Parser.createIncome("addi /cSalary /d2020-12-25 /e$1000 bonus!");
        } catch (MoneyTrackerException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("The amount parameter is missing.", errorMessage);

        errorMessage = "";
        try {
            Parser.createIncome("addi /a5000 /d2020-12-25 /e$1000 bonus!");
        } catch (MoneyTrackerException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("The income category parameter is missing.", errorMessage);
    }

    /**
     * Tests the createIncome method with invalid input.
     */
    @Test
    public void testCreateIncomeWithInvalidValue() {
        String errorMessage = "";
        try {
            Parser.createIncome("addi /aWEIRD /cSalary /d2020-12-25 /e$1000 bonus!");
        } catch (MoneyTrackerException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("The amount must be a decimal value. E.g. 30.50", errorMessage);

        errorMessage = "";
        try {
            Parser.createIncome("addi /a5000 /cSalary /d20201225 /e$1000 bonus!");
        } catch (MoneyTrackerException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Date should be in yyyy-MM-dd format. E.g. 2020-12-25", errorMessage);
    }

    /**
     * Tests the createExpense method with valid input.
     */
    @Test
    public void testCreateExpense() throws MoneyTrackerException {
        Transaction testExpense =
                Parser.createExpense("adde /a58.75 /cfood /d2020-12-25 /eExpensive dinner!");
        assertEquals("[E] FOOD $58.75 on 25 Dec 2020 (Expensive dinner!)", testExpense.toString());
    }

    /**
     * Tests the createExpense method with incomplete input.
     */
    @Test
    public void testCreateExpenseWithMissingValue() {
        String errorMessage = "";
        try {
            Parser.createExpense("adde /cfood /d2020-12-25 /eExpensive dinner!");
        } catch (MoneyTrackerException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("The amount parameter is missing.", errorMessage);

        errorMessage = "";
        try {
            Parser.createExpense("adde /a58.75 /d2020-12-25 /eExpensive dinner!");
        } catch (MoneyTrackerException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("The expense category parameter is missing.", errorMessage);
    }

    /**
     * Tests the createIncome method with invalid input.
     */
    @Test
    public void testCreateExpenseWithInvalidValue() {
        String errorMessage = "";
        try {
            Parser.createExpense("adde /aWEIRD /cfood /d2020-12-25 /eExpensive dinner!");
        } catch (MoneyTrackerException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("The amount must be a decimal value. E.g. 30.50", errorMessage);

        errorMessage = "";
        try {
            Parser.createExpense("adde /a58.75 /cfood /d20201225 /eExpensive dinner!");
        } catch (MoneyTrackerException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Date should be in yyyy-MM-dd format. E.g. 2020-12-25", errorMessage);
    }

    /**
     * Tests the getTransactionIndex method with valid input.
     */
    @Test
    public void testGetTransactionIndex() throws MoneyTrackerException {
        assertEquals(6, Parser.getDeleteIndex("delete 7"));
    }

    /**
     * Tests the getTransactionIndex method with incomplete input.
     */
    @Test
    public void testGetTransactionIndexWithMissingValue() {
        String errorMessage = "";
        try {
            Parser.getDeleteIndex("delete");
        } catch (MoneyTrackerException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("The index is missing.", errorMessage);
    }

    /**
     * Tests the getTransactionIndex method with invalid values.
     */
    @Test
    public void testGetTransactionIndexWithInvalidValue() {
        String errorMessage = "";
        try {
            Parser.getDeleteIndex("delete WEIRD");
        } catch (MoneyTrackerException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("The index is invalid.", errorMessage);
    }

    /**
     * Tests the getEditParameters method with valid input.
     */
    @Test void testGetEditParameters() throws MoneyTrackerException {
        String[] testEditParameters = Parser.getEditParameters("editcat 1 /ndividend");
        assertEquals("1", testEditParameters[0]);
        assertEquals("dividend", testEditParameters[1]);
    }

    /**
     * Tests the getEditParameters method with incomplete input.
     */
    @Test void testGetEditParametersWithMissingValue() {
        String errorMessage = "";
        try {
            Parser.getEditParameters("editcat");
        } catch (MoneyTrackerException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("The index is missing.", errorMessage);
    }
}