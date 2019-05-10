
package com.gitlab.svavassori.lastminutetest;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.BDDAssertions.then;

import com.gitlab.svavassori.lastminutetest.tax.Taxes;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 *
 * @author sergio
 */
class MainTest {

    private static final String DIR = "src/test/resources/";

    @ParameterizedTest
    @MethodSource("createItems")
    void shouldPrintBill(List<Item> items, String expected) {

        Taxes taxes = new Taxes();
        List<TaxedItem> taxedItems = items.stream()
                    .map(item -> new TaxedItem(item, taxes.calculateTaxes(item)))
                    .collect(toList());
        Printer printer = new Printer();
        String result = printer.printBill(taxedItems);

        then(result).isEqualTo(expected);
    }

    static Stream<Arguments> createItems() throws IOException {

        return Stream.of(
                Arguments.of(createItemList("input1.txt"), readFile("output1.txt")),
                Arguments.of(createItemList("input2.txt"), readFile("output2.txt")),
                Arguments.of(createItemList("input3.txt"), readFile("output3.txt"))
               );
    }

    private static List<Item> createItemList(String filename) throws IOException {

        Path path = Paths.get(DIR + filename);

        return Files.readAllLines(path).stream().map(MainTest::parseItem).collect(toList());
    }

    private static String readFile(String filename) throws IOException {

        return Files.readString(Paths.get(DIR + filename));
    }
    
    private static Item parseItem(String line) {
        
        Matcher matcher = Pattern.compile("(?<quantity>\\d+) (?<origin>imported )?"
                                        + "(?<name>[\\w ]+) at (?<price>\\d+\\.\\d+)")
                                 .matcher(line);
        
        if (matcher.matches()) {
            
            int quantity = Integer.parseInt(matcher.group("quantity"));
            Origin origin = matcher.group("origin") == null ? Origin.NOT_IMPORTED : Origin.IMPORTED;
            String name = matcher.group("name");
            int price = Integer.parseInt(matcher.group("price").replace(".", ""));
            Category category = Category.findCategory(name);
            
            return new Item(new Product(name, price, origin), quantity, category);
        }
        
        throw new IllegalArgumentException("Unable to parse: " + line);
    }
}
