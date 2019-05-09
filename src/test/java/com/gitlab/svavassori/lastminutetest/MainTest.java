
package com.gitlab.svavassori.lastminutetest;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
        
        Bill bill = new Bill();
        String result = bill.printBill(items);
        
        then(result).isEqualTo(expected);
    }
    
    static Stream<?> createItems() throws IOException {
        
        return Stream.of(
                Arguments.of(createItemList("input1.txt"), readFile("output1.txt")),
                Arguments.of(createItemList("input2.txt"), readFile("output2.txt")),
                Arguments.of(createItemList("input3.txt"), readFile("output3.txt"))
                );
    }
    
    private static List<Item> createItemList(String filename) throws IOException {
        
        Path path = Paths.get(DIR + filename);
        
        return Files.readAllLines(path).stream().map(Main::parseItem).collect(toList());
    }
    
    private static String readFile(String filename) throws IOException {
        
        return Files.readString(Paths.get(DIR + filename));
    }
}
