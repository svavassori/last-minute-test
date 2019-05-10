
package com.gitlab.svavassori.lastminutetest;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import org.assertj.core.api.BDDSoftAssertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author sergio
 */
class TaxedItemTest {
    
    @Test
    void shouldReturnFields() {
        
        Item item = mock(Item.class);
        int taxes = 22;
        
        TaxedItem taxed = new TaxedItem(item, taxes);
        
        BDDSoftAssertions soft = new BDDSoftAssertions();
        soft.then(taxed.getItem()).as("item").isEqualTo(item);
        soft.then(taxed.getTaxes()).as("taxes").isEqualTo(taxes);
        soft.assertAll();
    }
    
    @Test
    void shouldReturnTaxedPrice() {
        
        int price = 1122;
        int taxes = 22;
        Product product = mock(Product.class);
        Item item = mock(Item.class);
        given(item.getProduct()).willReturn(product);
        given(product.getPrice()).willReturn(price);
        
        
        TaxedItem taxed = new TaxedItem(item, taxes);
        
        then(taxed.getTaxedPrice()).isEqualTo(price + taxes);
    }
    
    @Test
    void shouldNotAcceptNullItem() {
        
        assertThatNullPointerException().isThrownBy(() -> new TaxedItem(null, 0));
    }
}
