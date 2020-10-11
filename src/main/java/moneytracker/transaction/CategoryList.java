package moneytracker.transaction;

import moneytracker.exception.MoneyTrackerException;

import java.util.ArrayList;

public class CategoryList {
    private ArrayList<Category> categoryList = new ArrayList<>();

    /**
     * Initializes an empty <code>CategoryList</code> object.
     */
    public CategoryList() {
    }

    /**
     * Initializes a <code>CategoryList</code> object.
     *
     * @param categories List of categories.
     */
    public CategoryList(ArrayList<Category> categories) {
        this.categoryList = categories;
    }

    /**
     * Get incomeCategory from the IncomeCategoryList.
     *
     * @param index Index of an income category in the <code>IncomeCategoryList</code>.
     * @return An income category.
     */
    public Category getCategory(int index) {
        return categoryList.get(index);
    }

    public int getSize() {
        return categoryList.size();
    }

    /**
     * Add a category to the <code>CategoryList</code>.
     *
     * @param category A category to be added.
     */
    public void addCategory(Category category) {
        categoryList.add(category);
    }

    /**
     * Remove an category from the <code>CategoryList</code>.
     *
     * @param index Index of an category in <code>CategoryList</code>.
     * @throws MoneyTrackerException If index is invalid.
     */
    public void removeCategory(int index) throws MoneyTrackerException {
        try {
            categoryList.remove(categoryList.get(index));
        } catch (IndexOutOfBoundsException e) {
            throw new MoneyTrackerException("Invalid index");
        }
    }
}
