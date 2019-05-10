
package com.gitlab.svavassori.lastminutetest.tax;

import static com.gitlab.svavassori.lastminutetest.Category.BOOKS;
import static com.gitlab.svavassori.lastminutetest.Category.FOOD;
import static com.gitlab.svavassori.lastminutetest.Category.MEDICAL;

import com.gitlab.svavassori.lastminutetest.Category;
import com.gitlab.svavassori.lastminutetest.Item;

import java.util.EnumSet;
import java.util.function.Predicate;

/**
 *
 * @author sergio
 */
public class BasicTaxCondition implements Predicate<Item> {

    private EnumSet<Category> categoriesToTax;
    
    public BasicTaxCondition() {
        
        EnumSet<Category> excluded = EnumSet.of(BOOKS, FOOD, MEDICAL);
        categoriesToTax = EnumSet.complementOf(excluded);
    }
    
    @Override
    public boolean test(Item item) {
        
        return categoriesToTax.contains(item.getCategory());
    }
}
