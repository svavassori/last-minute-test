
package com.gitlab.svavassori.lastminutetest;

import java.util.Objects;

/**
 * This class represents an Item with its associated taxes.
 * 
 * @author sergio
 */
public class TaxedItem {
    
    private Item item;
    private int taxes;
    
    /**
     * Creates an immutable instance of this class using the given item and taxes.
     * 
     * @param item the item to be taxed
     * @param taxes the taxes for the item
     */
    public TaxedItem(Item item, int taxes) {
        
        this.item = Objects.requireNonNull(item);
        this.taxes = taxes;
    }
    
    /**
     * @return the item
     */
    public Item getItem() {
        
        return item;
    }
    
    /**
     * @return the taxes
     */
    public int getTaxes() {
        
        return taxes;
    }
    
    /**
     * @return the price including taxes
     */
    public int getTaxedPrice() {
        
        return item.getProduct().getPrice() + taxes;
    }
}
