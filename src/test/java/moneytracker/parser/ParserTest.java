package moneytracker.parser;


import static org.junit.jupiter.api.Assertions.assertEquals;
import moneytracker.exception.MoneyTrackerException;
import moneytracker.transaction.Category;
import moneytracker.transaction.CategoryList;
import moneytracker.transaction.Transaction;
import moneytracker.transaction.TransactionList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
     * Tests the getEditCategoryParams method with valid input.
     */
    @Test void testGetEditCategoryParams() throws MoneyTrackerException {
        String[] testEditParameters = Parser.getEditCategoryParams("editcat 1 /ndividend");
        assertEquals("1", testEditParameters[0]);
        assertEquals("dividend", testEditParameters[1]);
    }

    /**
     * Tests the getEditCategoryParams method with incomplete input.
     */
    @Test void testGetEditCategoryParamsWithMissingValue() {
        String errorMessage = "";
        try {
            Parser.getEditCategoryParams("editcat");
        } catch (MoneyTrackerException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("The index is missing.", errorMessage);
    }

    /**
     * Tests the getDate method with incorrect date month.
     */
    @Test
    void testGetDateWithWrongMonth() {
        String errorMessage = "";
        try {
            Parser.getDate("2020-13");
        } catch (MoneyTrackerException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Date should be in yyyy-MM format. E.g. 2020-09", errorMessage);
    }

    /**
     * Tests the getDate method with incorrect date format.
     */
    @Test
    void testGetDateWithWrongDateFormat() {
        String errorMessage = "";
        try {
            Parser.getDate("Sep 2020");
        } catch (MoneyTrackerException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Date should be in yyyy-MM format. E.g. 2020-09", errorMessage);
    }

    /**
     * Tests the getDateOfMonth method with input.
     */
    @Test
    void testGetDaysOfMonth() {
        assertEquals(30, Parser.getDaysOfMonth("2020-09"));
        assertEquals(29, Parser.getDaysOfMonth("2020-02"));
        assertEquals(31, Parser.getDaysOfMonth("2020-07"));
        assertEquals(31, Parser.getDaysOfMonth("2020-08"));
    }

    private final TransactionList testTrans = new TransactionList();
    private final CategoryList testCate = new CategoryList();

    /**
     * Tests before init input.
     */
    @BeforeEach
    public void init() throws MoneyTrackerException {
        Category testCate1 = Parser.createIncomeCategory("Salary");
        Category testCate2 = Parser.createExpenseCategory("Food");
        testCate.addCategory(testCate1);
        testCate.addCategory(testCate2);
        Transaction testIncome1 = Parser.createIncome("addi /a1000 /cSalary /d2020-12-25 /eBonus");
        Transaction testIncome2 = Parser.createIncome("addi /a5000 /cSalary /d2020-12-25 /eBonus");
        Transaction testExpense1 = Parser.createExpense("adde /a5.5 /cFood /d2020-12-25 /eFood");
        Transaction testExpense2 = Parser.createExpense("adde /a4.5 /cFood /d2020-12-25 /eFood");
        testTrans.addTransaction(testIncome1,testCate);
        testTrans.addTransaction(testIncome2,testCate);
        testTrans.addTransaction(testExpense1,testCate);
        testTrans.addTransaction(testExpense2,testCate);
    }

    /**
     * Tests the getTotalIncome method with input.
     */
    @Test
    void testGetTotalIncome() {
        assertEquals(6000,Parser.getTotalIncome(testTrans,"2020-12"));
    }


    /**
     * Tests the getTotalExpense method with input.
     */
    @Test
    void testGetTotalExpense() {
        assertEquals(10.0, Parser.getTotalExpense(testTrans,"2020-12"));
    }

    /**
     * Tests the getHighestIncome method with input.
     */
    @Test
    void testGetHighestIncome() {
        assertEquals("[I] SALARY $5000.00 on 25 Dec 2020 (Bonus)",
                Parser.getHighestIncome(testTrans,"2020-12"));
    }

    /**
     * Tests the getHighestExpense method with input.
     */
    @Test
    void testGetHighestExpense() {
        assertEquals("[E] FOOD $5.50 on 25 Dec 2020 (Food)",
                Parser.getHighestExpense(testTrans,"2020-12"));
    }

    /**
     * Tests the getHighestIncome method with empty month input.
     */
    @Test
    void testGetHighestIncomeWithEmptyMonth() {
        assertEquals("  Sorry, Cannot find any Income record in this Month.",
                Parser.getHighestIncome(testTrans,"2020-11"));
    }

    /**
     * Tests the getFrequency method with input.
     */
    @Test
    void testGetFrequency() {
        ArrayList<String> test = new ArrayList<>();
        test.add("Food");
        test.add("Salary");
        test.add("Food");
        Parser.getFrequency(test);

        assertEquals("{Food=2, Salary=1}",Parser.getFrequency(test).toString());
    }

    /**
     * Tests the sortHelper method with input.
     */
    @Test
    void testSortHelper() {
        Map<String, Double> testMap = new HashMap<>();
        testMap.put("Food",5.5);
        testMap.put("Salary",1000.0);
        testMap.put("Drink",5.6);

        assertEquals("{Salary=1000.0, Drink=5.6, Food=5.5}",Parser.sortHelper(testMap).toString());
    }

    /**
     * After test clear test List.
     */
    @AfterEach
    public void destroy() {
        testTrans.clearTransactions();
    }
}