
package com.gitlab.svavassori.lastminutetest;

import static java.util.Objects.requireNonNull;

/**
 * This class represents a specific item to be bought.
 * 
 * @author sergio
 */
public class Item {
    
    private Product product;
    private int quantity;
    private Category category;
    
    /**
     * Construct an immutable instance of {@link Item} with the given product,
     * quantity and category.
     * 
     * @param product the product the item refers to
     * @param quantity the quantity of the product
     * @param category the category of the product
     * 
     * @throws NullPointerException if {@code product} or {@code category} are null
     * @throws IllegalArgumentException if {@code quantity} is negative
     */
    public Item(Product product, int quantity, Category category) {
        
        this.product = requireNonNull(product);
        this.quantity = requireNonNegative(quantity);
        this.category = requireNonNull(category);
    }
    
    private int requireNonNegative(int quantity) {
        
        if (quantity < 0) {
            
            throw new IllegalArgumentException("Quantity cannot be negative: " + quantity);
        }
        
        return quantity;
    }
        
    /**
     * @return true if the item is imported, false otherwise.
     */
    public boolean isImported() {
        
        return product.getOrigin() == Origin.IMPORTED;
    }
    
    /**
     * @return the product
     */
    public Product getProduct() {
        
        return product;
    }
    
    /**
     * @return the quantity
     */
    public int getQuantity() {
        
        return quantity;
    }
    
    /**
     * @return the category
     */
    public Category getCategory() {
        
        return category;
    }
}
