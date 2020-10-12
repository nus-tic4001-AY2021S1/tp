package moneytracker.transaction;

import moneytracker.exception.MoneyTrackerException;

import java.util.ArrayList;

/**
 * Manages the in-memory category list. It contains an ArrayList that stores
 * individual <code>Category</code> objects and methods to perform operations
 * such as adding and deleting categories.
 */
public class CategoryList {
    private ArrayList<Category> categoryList = new ArrayList<>();
    private final ArrayList<Integer> searchResultIndexes = new ArrayList<>();
    private boolean isInitialized = false;

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
     * Gets a <code>Category</code> from the <code>CategoryList</code>.
     *
     * @param index Index of an <code>Category</code> in the <code>CategoryList</code>.
     * @return List of categories.
     */
    public Category getCategory(int index) {
        return categoryList.get(index);
    }

    /**
     * Gets the size of a <code>CategoryList</code>.
     * @return Size of a <code>CategoryList</code>.
     */
    public int getSize() {
        return categoryList.size();
    }

    /**
     * Adds a <code>Category</code> to the <code>CategoryList</code>.
     *
     * @param category A <code>Category</code> to be added.
     */
    public void addCategory(Category category) throws MoneyTrackerException {
        if (!checkIfCategoryExists(category.getName(), category.getType())) {
            categoryList.add(category);
        } else {
            throw new MoneyTrackerException("This category already exists.");
        }
    }

    /**
     * Removes a <code>Category</code> from the <code>CategoryList</code>.
     *
     * @param index Index of a <code>Category</code> in <code>CategoryList</code>.
     * @throws MoneyTrackerException If index is invalid.
     */
    public void removeCategory(int index) throws MoneyTrackerException {
        try {
            categoryList.remove(categoryList.get(index));
        } catch (IndexOutOfBoundsException e) {
            throw new MoneyTrackerException("Invalid index");
        }
    }

    /**
     * Checks whether a category already exists in <code>CategoryList</code>.
     *
     * @param name Name of the category.
     * @param type Type of the category.
     */
    public boolean checkIfCategoryExists(String name, String type) {
        for (Category category : categoryList) {
            if (name.equals(category.getName()) && type.equals(category.getType())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the status of whether the listcat command has been used.
     */
    public boolean getIsInitialized() {
        return isInitialized;
    }

    /**
     * Sets the status that the listcat command has just been used.
     */
    public void setIsInitialized(boolean isInitialized) {
        this.isInitialized = isInitialized;
    }

    /**
     * Adds index for a particular category listed by the listcat command.
     * @param searchResultIndex Index of category listed by the listcat command.
     */
    public void addSearchResultIndex(int searchResultIndex) {
        searchResultIndexes.add(searchResultIndex);
    }

    /**
     * Clears the indexes from the listcat command.
     */
    public void clearSearchResultIndexes() {
        searchResultIndexes.clear();
    }

    /**
     * Gets the indexes from the listcat command.
     */
    public ArrayList<Integer> getSearchResultIndexes() {
        return searchResultIndexes;
    }

    /**
     * Gets the index for a particular category listed by the listcat command.
     */
    public int getSearchResultIndex(int resultIndex) {
        return searchResultIndexes.get(resultIndex);
    }
}