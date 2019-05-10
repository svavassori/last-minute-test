
package com.gitlab.svavassori.lastminutetest;

import static java.lang.String.format;
import static java.util.Locale.ENGLISH;
import static java.util.stream.Collectors.joining;

import java.util.List;

/**
 * This class prints the content of {@link TaxedItem}.
 * 
 * @author sergio
 */
public class Printer {
    
    public String printBill(List<TaxedItem> items) {
        
        int totalTaxes = items.stream().mapToInt(TaxedItem::getTaxes).sum();
        int total = items.stream().mapToInt(TaxedItem::getTaxedPrice).sum();
                
        StringBuffer writer = new StringBuffer();
        writer.append(items.stream().map(this::printItem).collect(joining("\n")))
              .append("\n")
              .append(format(ENGLISH, "Sales Taxes: %.2f\n", totalTaxes / 100.0D))
              .append(format(ENGLISH, "Total: %.2f\n", total / 100.0D));
        
        return writer.toString();
    }

    public String printItem(TaxedItem taxedItem) {
        
        Item item = taxedItem.getItem();
        Product product = item.getProduct();
        double taxedPrice = taxedItem.getTaxedPrice() / 100.0D;
        String imported = item.isImported() ? "imported " : "";
        
        return format(ENGLISH, "%d %s%s: %.2f", item.getQuantity(),
                                                imported,
                                                product.getName(),
                                                taxedPrice);
    }
}
