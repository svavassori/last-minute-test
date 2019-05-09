
package com.gitlab.svavassori.lastminutetest.tax;

import com.gitlab.svavassori.lastminutetest.Item;

import java.util.Objects;
import java.util.function.Predicate;


/**
 * This class represents the tax over all imported goods.
 * 
 * @author sergio
 */
public class Tax {
    
    private int rate;
    private Predicate<Item> taxCondition;
    
    /**
     * Construct an immutable instance of Tax with the given rate to be applied
     * to the given categories.
     * 
     * @param rate the tax rate to apply, in percentage
     * @param taxCondition the condition under which apply the tax
     */
    public Tax(int rate, Predicate<Item> taxCondition) {
        
        this.rate = rate;
        this.taxCondition = Objects.requireNonNull(taxCondition);
    }
    
    /**
     * @param item the {@link Item} over which calculate the tax
     * @return the rate applied for the given item, in cents
     */
    public int tax(Item item) {
        
        int taxes = 0;
        
        if (taxCondition.test(item)) {
            // here there could be some rounding problem, not sure if it's
            // needed for this example to take those in account.
            int taxed = item.getProduct().getPrice() * item.getQuantity() * rate;
            taxes = (int) Math.round(taxed / 100.0);
        }
        
        return taxes;
    }
    
    /**
     * Rounds the given price (in cents) to the nearest 0.05 (so nearest 5).
     * 
     * @param price the price to round, in cents
     * @return the rounded price, in cents
     */
    public static int roundToNearest(int price) {
        
        return (int) (Math.round(price / 5.0) * 5);
    }
    
    /**
     * @return the tax rate applied, in percents
     */
    public int rate() {
        
        return rate;
    }
}