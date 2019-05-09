
package com.gitlab.svavassori.lastminutetest;

import static java.util.stream.Collectors.joining;

import com.gitlab.svavassori.lastminutetest.tax.BasicTaxCondition;
import com.gitlab.svavassori.lastminutetest.tax.Tax;

import java.io.StringWriter;
import java.util.List;

/**
 *
 * @author sergio
 */
public class Bill {
    
    private List<Tax> taxes;
    
    public Bill() {
        
        taxes = List.of(new Tax(10, new BasicTaxCondition()),
                        new Tax(5, Item::isImported));
    }
    
    public String printBill(List<Item> items) {
        
        int price = items.stream().mapToInt(item -> item.getProduct().getPrice()).sum();
        int taxes = calculateTaxes(items);
        int total = price + taxes;
        
        StringWriter writer = new StringWriter();
        writer.append(items.stream().map(Bill::printItem).collect(joining("\n")))
              .append("\n")
              .append(String.format("Sales Taxes: %0.2f\n", taxes / 100.0D))
              .append(String.format("Total: %d\n", total / 100.0D));
        
        return writer.toString();
    }

    public static String printItem(Item item) {
        
        String imported = item.isImported() ? "imported " : "";
        
        return String.format("%d %s%s: %.2f", item.getQuantity(),
                                              imported,
                                              item.getProduct().getName(),
                                              item.getProduct().getPrice() / 100.0D);
    }
    
    /**
     * Returns the total taxes for the given items.
     * 
     * @param items the items to calculate taxes for
     * @return the total taxes
     */
    public int calculateTaxes(List<Item> items) {
        
        return roundToNearest(items.stream()
                                   .flatMapToInt(item -> taxes.stream()
                                               .mapToInt(tax -> tax.tax(item)))
                                   .sum());
    }
    
    /**
     * Rounds the given price (in cents) to the nearest 0.05 (so nearest 5).
     * 
     * @param price the price to round, in cents
     * @return the rounded price, in cents
     */
    private static int roundToNearest(int price) {
        
        return (int) (Math.round(price / 5.0) * 5);
    }
}
