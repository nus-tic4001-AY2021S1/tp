package moneytracker.transaction;

import moneytracker.exception.MoneyTrackerException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * JUnit tests for CategoryList class.
 */
public class CategoryListTest {
    /**
     * Tests the exception handling for duplicated <code>Category</code> objects.
     */
    @Test
    public void testAddDuplicateCategory() {
        CategoryList categories = new CategoryList();
        Category testCategory = new Category("SALARY", "INCOME");
        Category testCategory2 = new Category("SALARY", "INCOME");
        String errorMessage = "";
        try {
            categories.addCategory(testCategory);
            categories.addCategory(testCategory2);
        } catch (MoneyTrackerException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("This category already exists.", errorMessage);
    }

    /**
     * Tests the checkIfCategoryExists method.
     */
    @Test
    public void testCheckIfCategoryExists() throws MoneyTrackerException {
        CategoryList categories = new CategoryList();
        Category testCategory = new Category("SALARY", "INCOME");
        Category testCategory2 = new Category("FOOD", "EXPENSE");
        categories.addCategory(testCategory);
        categories.addCategory(testCategory2);
        assertTrue(categories.checkIfCategoryExists("SALARY", "INCOME"));
        assertTrue(categories.checkIfCategoryExists("FOOD", "EXPENSE"));
        assertFalse(categories.checkIfCategoryExists("WEIRD", "ITEM"));
        assertFalse(categories.checkIfCategoryExists("SALARY", "EXPENSE"));
        assertFalse(categories.checkIfCategoryExists("FOOD", "INCOME"));
    }
}