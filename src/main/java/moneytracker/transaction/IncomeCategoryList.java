package moneytracker.transaction;

import moneytracker.exception.MoneyTrackerException;

import java.util.ArrayList;

public class IncomeCategoryList {
    private ArrayList<String> incomeCategoryList = new ArrayList<>();

    /**
     * Initializes an empty IncomeCategoryList.
     */
    public IncomeCategoryList() {
    }


    /**
     * Initializes a IncomeCategoryList object.
     *
     * @param incomeCategories List of incomeCategoryList objects.
     */
    public IncomeCategoryList(ArrayList<String> incomeCategories) {
        this.incomeCategoryList = incomeCategories;
    }

    /**
     * Get incomeCategory  from the IncomeCategoryList.
     *
     * @param index Index of incomeCategory in the IncomeCategoryList.
     * @return Income category.
     */
    public String getIncomeCategory(int index) {
        return incomeCategoryList.get(index);
    }

    public int getSize() {
        return incomeCategoryList.size();
    }

    /**
     * Add a incomeCategory to the IncomeCategoryList object.
     *
     * @param incomeCategory incomeCategory.
     */
    public void addIncomeCategory(String incomeCategory) {
        incomeCategoryList.add(incomeCategory);
    }

    /**
     * Remove a incomeCategory from the IncomeCategoryList.
     *
     * @param index Index of a incomeCategory in IncomeCategoryList.
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
