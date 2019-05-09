
package com.gitlab.svavassori.lastminutetest;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import org.assertj.core.api.BDDSoftAssertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author sergio
 */
class ProductTest {
    
    @Test
    void shouldReturnFields() {
        
        String name = "product name";
        int price = 4455;
        Origin origin = Origin.IMPORTED;
        Product product = new Product(name, price, origin);
        
        BDDSoftAssertions soft = new BDDSoftAssertions();
        soft.then(product.getName()).as("name").isEqualTo(name);
        soft.then(product.getPrice()).as("price").isEqualTo(price);
        soft.then(product.getOrigin()).as("origin").isEqualTo(origin);
        soft.assertAll();
    }
    
    @Test
    void shouldNotAcceptNullName() {
        
        assertThatNullPointerException()
                    .isThrownBy(() -> new Product(null, 0, Origin.IMPORTED));
    }
    
    @Test
    void shouldNotAcceptNegativePrice() {
        
        assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Product("name", -1, Origin.IMPORTED));
    }
    
    @Test
    void shouldNotAcceptNullOrigin() {
        
        assertThatNullPointerException().isThrownBy(() -> new Product("name", 0, null));
    }
}
