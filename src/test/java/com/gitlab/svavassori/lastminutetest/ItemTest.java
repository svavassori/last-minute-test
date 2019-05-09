
package com.gitlab.svavassori.lastminutetest;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import org.assertj.core.api.BDDSoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author sergio
 */
class ItemTest {
    
    private Product product;
    private int quantity;
    private Category category;
    private Item item;
    
    @BeforeEach
    void setUp() {
        
        product = mock(Product.class);
        quantity = 5;
        category = Category.OTHER;
        
        item = new Item(product, quantity, category);
    }
    
    @Test
    void shouldReturnFields() {
        
        BDDSoftAssertions soft = new BDDSoftAssertions();
        soft.then(item.getProduct()).as("product").isEqualTo(product);
        soft.then(item.getQuantity()).as("quantity").isEqualTo(quantity);
        soft.then(item.getCategory()).as("category").isEqualTo(category);
        soft.assertAll();
    }
    
    @Test
    void shouldReturnImportedProduct() {
        
        given(product.getOrigin()).willReturn(Origin.IMPORTED);
        
        then(item.isImported()).isTrue();
    }
    
    @Test
    void shouldReturnNotImportedProduct() {
        
        given(product.getOrigin()).willReturn(Origin.NOT_IMPORTED);
        
        then(item.isImported()).isFalse();
    }
    
    @Test
    void shouldNotAcceptNullProduct() {
        
        assertThatNullPointerException()
                    .isThrownBy(() -> new Item(null, 1, Category.FOOD));
    }
    
    @Test
    void shouldNotAcceptNegativeQuantity() {
        
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Item(mock(Product.class), -1, Category.BOOKS));
    }
    
    @Test
    void shouldNotAcceptNullCategory() {
        
        assertThatNullPointerException()
                    .isThrownBy(() -> new Item(mock(Product.class), 0, null));
    }
}
