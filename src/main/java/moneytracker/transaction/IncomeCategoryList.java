package moneytracker.transaction;

import moneytracker.exception.MoneyTrackerException;

import java.util.ArrayList;

public class IncomeCategoryList {
    private ArrayList<Income> incomeCategoryList;

    /**
     * Initializes an empty <code>IncomeCategoryList</code> object.
     */
    public IncomeCategoryList() {
    }


    /**
     * Initializes a <code>IncomeCategoryList</code> object.
     *
     * @param incomeCategory List of <code>Expense</code> objects.
     */
    public IncomeCategoryList(ArrayList<Income> incomeCategory) {
        this.incomeCategoryList = incomeCategory;
    }

    /**
     * Get <code>incomeCategory</code> object from the <code>IncomeCategoryList</code>.
     *
     * @param index Index of incomeCategory in the <code>IncomeCategoryList</code>.
     * @return <code>incomeCategory</code> object.
     */
    public Income getIncomeCategory(int index) {
        return incomeCategoryList.get(index);
    }

    /**
     * Add a incomeCategory to the <code>IncomeCategoryList</code> object.
     *
     * @param i <code>incomeCategory</code> object.
     */
    public void addIncomeCategory(Income i) {
        incomeCategoryList.add(i);
    }

    /**
     * Remove a <code>incomeCategory</code> object from the <code>IncomeCategoryList</code>.
     *
     * @param index Index of a <code>incomeCategory</code> in <code>IncomeCategoryList</code>.
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
