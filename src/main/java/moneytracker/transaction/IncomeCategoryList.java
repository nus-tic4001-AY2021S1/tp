package moneytracker.transaction;

import moneytracker.exception.MoneyTrackerException;

import java.util.ArrayList;

public class IncomeCategoryList {
    private ArrayList<String> incomeCategoryList = new ArrayList<>();

    /**
     * Initializes an empty <code>IncomeCategoryList</code> object.
     */
    public IncomeCategoryList() {
    }

    /**
     * Initializes a <code>IncomeCategoryList</code> object.
     *
     * @param incomeCategories List of income categories.
     */
    public IncomeCategoryList(ArrayList<String> incomeCategories) {
        this.incomeCategoryList = incomeCategories;
    }

    /**
     * Get incomeCategory from the IncomeCategoryList.
     *
     * @param index Index of an income category in the <code>IncomeCategoryList</code>.
     * @return An income category.
     */
    public String getIncomeCategory(int index) {
        return incomeCategoryList.get(index);
    }

    public int getSize() {
        return incomeCategoryList.size();
    }

    /**
     * Add a incomeCategory to the <code>IncomeCategoryList</code>.
     *
     * @param incomeCategory An income category.
     */
    public void addIncomeCategory(String incomeCategory) {
        incomeCategoryList.add(incomeCategory);
    }

    /**
     * Remove an income category from the <code>IncomeCategoryList</code>.
     *
     * @param index Index of an income category in <code>IncomeCategoryList</code>.
     * @throws MoneyTrackerException If index is invalid.
     */
    public void removeIncomeCategory(int index) throws MoneyTrackerException {
        try {
            incomeCategoryList.remove(incomeCategoryList.get(index));
        } catch (IndexOutOfBoundsException e) {
            throw new MoneyTrackerException("Invalid index");
        }
    }
}
