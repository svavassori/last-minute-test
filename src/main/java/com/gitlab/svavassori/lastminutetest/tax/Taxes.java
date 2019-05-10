
package com.gitlab.svavassori.lastminutetest.tax;

import com.gitlab.svavassori.lastminutetest.Item;

import java.util.List;

/**
 * This class calculates the applied taxes for the given item.
 * 
 * @author sergio
 */
public class Taxes {
    
    private List<Tax> taxes;
    
    public Taxes() {
        
        taxes = List.of(new Tax(10, new BasicTaxCondition()),
                        new Tax(5, Item::isImported));
    }
    
    /**
     * Returns the total taxes for the given items.
     * 
     * @param item the item to calculate taxes for
     * @return the total taxes
     */
    public int calculateTaxes(Item item) {
        
        return ceilToTwentieth(taxes.stream().mapToInt(tax -> tax.tax(item)).sum());
    }
    
    /**
     * Rounds up (ceiling) the given price (in cents) to the nearest 0.05
     * (so nearest 5).
     * 
     * @param price the price to round, in cents
     * @return the rounded price, in cents
     */
    public static int ceilToTwentieth(int price) {
        
        return (int) (Math.ceil(price / 5.0) * 5);
    }
}
