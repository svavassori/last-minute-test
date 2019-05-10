
package com.gitlab.svavassori.lastminutetest;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.gitlab.svavassori.lastminutetest.tax.Taxes;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 *
 * @author sergio
 */
class TaxesTest {
    
    @ParameterizedTest
    @CsvSource({"100,100", "101,105", "104,105", "105,105", "106,110", "108,110"})
    void shouldCeil(int price, int rounded) {
        
        then(Taxes.ceilToTwentieth(price)).isEqualTo(rounded);
    }
    
    @ParameterizedTest
    @MethodSource("createItems")
    void shouldCalculateTaxes(Item item, int expTaxes) {
        
        int taxes = new Taxes().calculateTaxes(item);
        
        then(taxes).isEqualTo(expTaxes);
    }
    
    static Stream<Arguments> createItems() {
        
        return Stream.of(
                    Arguments.of(mockItem(false, Category.OTHER), 10),
                    Arguments.of(mockItem(false, Category.BOOKS), 0),
                    Arguments.of(mockItem(true, Category.OTHER), 15),
                    Arguments.of(mockItem(true, Category.BOOKS), 5)
                );
    }
    
    private static Item mockItem(boolean isImported, Category category) {
        
        int price = 100;
        Product product = mock(Product.class);
        given(product.getPrice()).willReturn(price);
        
        Item item = mock(Item.class);
        given(item.getProduct()).willReturn(product);
        given(item.getQuantity()).willReturn(1);
        given(item.isImported()).willReturn(isImported);
        given(item.getCategory()).willReturn(category);
        
        
        return item;
    }
}
