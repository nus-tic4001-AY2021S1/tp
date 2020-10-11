package moneytracker.transaction;

import moneytracker.exception.MoneyTrackerException;

import java.util.ArrayList;

public class ExpenseCategoryList {
    private ArrayList<String> expenseCategoryList = new ArrayList<>();

    /**
     * Initializes an empty <code>ExpenseCategoryList</code> object.
     */
    public ExpenseCategoryList() {
    }

    /**
     * Initializes a <code>ExpenseCategoryList</code> object.
     *
     * @param expenseCategories List of <code>Expense</code> objects.
     */
    public ExpenseCategoryList(ArrayList<String> expenseCategories) {
        this.expenseCategoryList = expenseCategories;
    }

    /**
     * Get <code>ExpenseCategory</code> object from the <code>ExpenseCategoryList</code>.
     *
     * @param index Index of ExpenseCategory in the <code>ExpenseCategoryList</code>.
     * @return <code>ExpenseCategory</code> object.
     */
    public String getExpenseCategory(int index) {
        return expenseCategoryList.get(index);
    }

    public int getSize() {
        return expenseCategoryList.size();
    }

    /**
     * Add a ExpenseCategory to the <code>ExpenseCategoryList</code> object.
     *
     * @param expenseCategory <code>ExpenseCategory</code> object.
     */
    public void addExpenseCategory(String expenseCategory) {
        expenseCategoryList.add(expenseCategory);
    }

    /**
     * Remove a <code>ExpenseCategory</code> object from the <code>ExpenseCategoryList</code>.
     *
     * @param index Index of a <code>ExpenseCategory</code> in <code>ExpenseCategoryList</code>.
     * @throws MoneyTrackerException If index is invalid.
     */
    public void removeExpenseCategory(int index) throws MoneyTrackerException {
        try {
            expenseCategoryList.remove(expenseCategoryList.get(index));
        } catch (IndexOutOfBoundsException e) {
            throw new MoneyTrackerException("Invalid index");
        }
    }
}
