
package com.gitlab.svavassori.lastminutetest;

import static com.gitlab.svavassori.lastminutetest.Category.BOOKS;
import static com.gitlab.svavassori.lastminutetest.Category.FOOD;
import static com.gitlab.svavassori.lastminutetest.Category.OTHER;
import static com.gitlab.svavassori.lastminutetest.Origin.NOT_IMPORTED;

import com.gitlab.svavassori.lastminutetest.tax.BasicTaxCondition;
import com.gitlab.svavassori.lastminutetest.tax.Tax;

import java.util.List;

/**
 *
 * @author sergio
 */
public class Main {
    
    public static void main(String[] args) {
        
        List<Tax> taxes = List.of(new Tax(10, new BasicTaxCondition()),
                                  new Tax(5, Item::isImported));
        
        Product book = new Product("book", 1249, NOT_IMPORTED);
        Product cd = new Product("Music CD", 1499, NOT_IMPORTED);
        Product chocolateBar = new Product("Chocolate Bar", 85, NOT_IMPORTED);
        
        List<Item> items = List.of(new Item(book, 1, BOOKS),
                                   new Item(cd, 1, OTHER),
                                   new Item(chocolateBar, 1, FOOD));
        
        int taxed = items.stream()
                       .flatMapToInt(item -> taxes.stream().mapToInt(tax -> {
                        
                           System.out.println(item.getProduct().getName() + ", " + tax.tax(item));
                           return tax.tax(item);
                       }))
                       .sum();
        print(taxed);
        print(Tax.roundToNearest(taxed));
    }
    
    public static void print(double price) {
        
        System.out.println(String.format("%.2f", price / 100));
    }
}
