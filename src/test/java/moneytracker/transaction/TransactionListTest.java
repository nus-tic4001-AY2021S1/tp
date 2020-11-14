package moneytracker.transaction;

import moneytracker.exception.MoneyTrackerException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


class TransactionListTest {
    TransactionList transactions = new TransactionList();


    /**
     * Tests the removeTransaction method.
     */
    @Test
    void removeTransaction() throws MoneyTrackerException {
        CategoryList categories = new CategoryList();
        Category testCategory = new Category("SALARY", "INCOME");
        Category testCategory2 = new Category("FOOD", "EXPENSE");
        categories.addCategory(testCategory);
        categories.addCategory(testCategory2);
        Transaction testIncome = new Income(5000, "$1000 bonus!",
                "2020-12-25", "SALARY");
        transactions.addTransaction(testIncome,  categories);


        String errorMessage = "";
        try {
            transactions.removeTransaction(4);
        } catch (MoneyTrackerException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Invalid index", errorMessage);

    }

    /**
     * Tests the getSize method.
     */
    @Test
    void getSize() throws MoneyTrackerException {
        CategoryList categories = new CategoryList();
        Category testCategory = new Category("SALARY", "INCOME");
        Category testCategory2 = new Category("FOOD", "EXPENSE");
        categories.addCategory(testCategory);
        categories.addCategory(testCategory2);
        Transaction testIncome = new Income(5000, "$1000 bonus!",
                "2020-12-25", "SALARY");
        transactions.addTransaction(testIncome,  categories);

        int size = transactions.getSize();
        assertEquals(1,size);
    }



    /**
     * Tests the clearTransactions method.
     */
    @Test
    void clearTransactions() throws MoneyTrackerException {
        CategoryList categories = new CategoryList();
        Category testCategory = new Category("SALARY", "INCOME");
        Category testCategory2 = new Category("FOOD", "EXPENSE");
        categories.addCategory(testCategory);
        categories.addCategory(testCategory2);
        Transaction testIncome = new Income(5000, "$1000 bonus!",
                "2020-12-25", "SALARY");
        transactions.addTransaction(testIncome,  categories);


        transactions.clearTransactions();
        int size = transactions.getSize();

        assertEquals(0,size);

    }

    /**
     * Tests the getTransactions method.
     */
    @Test
    void getTransactions() throws MoneyTrackerException {
        CategoryList categories = new CategoryList();
        Category testCategory = new Category("SALARY", "INCOME");
        Category testCategory2 = new Category("FOOD", "EXPENSE");
        categories.addCategory(testCategory);
        categories.addCategory(testCategory2);
        Transaction testIncome = new Income(5000, "$1000 bonus!",
                "2020-12-25", "SALARY");
        transactions.addTransaction(testIncome,  categories);

        assertEquals("[[I] SALARY $5000.00 on 25 Dec 2020 ($1000 bonus!)]", transactions.getTransactions().toString());

    }

}