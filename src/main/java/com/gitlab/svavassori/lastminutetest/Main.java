
package com.gitlab.svavassori.lastminutetest;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author sergio
 */
public class Main {

    public static Item parseItem(String line) {
        
        Matcher matcher = Pattern.compile("(?<quantity>\\d+) (?<origin>imported )?"
                                        + "(?<name>[\\w ]+): (?<price>\\d+\\.\\d+)")
                                 .matcher(line);
        
        if (matcher.matches()) {
            
            int quantity = Integer.parseInt(matcher.group("quantity"));
            Origin origin = "imported".equals(matcher.group("origin")) ? Origin.IMPORTED : Origin.NOT_IMPORTED;
            String name = matcher.group("name");
            int price = Integer.parseInt(matcher.group("price").replace(".", ""));
            Category category = Category.findCategory(name);
            
            return new Item(new Product(name, price, origin), quantity, category);
        }
        
        throw new IllegalArgumentException("Unable to parse: " + line);
    }
    
    
    public static void main(String[] args) {
        
        Bill bill = new Bill();
        List<Item> items = List.of();
        String billed = bill.printBill(items);
        System.out.println(billed);
    }
}
