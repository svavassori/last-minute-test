
package tax;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.gitlab.svavassori.lastminutetest.Item;
import com.gitlab.svavassori.lastminutetest.Product;
import com.gitlab.svavassori.lastminutetest.tax.Tax;

import org.junit.jupiter.api.Test;

/**
 *
 * @author sergio
 */
class TaxTest {
    
    @Test
    void shouldNotTax() {
        
        Item item = mock(Item.class);
        Tax tax = new Tax(12, it -> false);
        
        int taxes = tax.tax(item);
        
        then(taxes).isZero();
    }
    
    
    @Test
    void shouldTaxWithRate() {
        
        int price = 500;
        int quantity = 2;
        int rate = 10;
        Item item = mock(Item.class);
        Product product = mock(Product.class);
        given(item.getProduct()).willReturn(product);
        given(item.getQuantity()).willReturn(quantity);
        given(product.getPrice()).willReturn(price);
        
        int taxes = new Tax(rate, it -> true).tax(item);
        
        then(taxes).isEqualTo(price * quantity * rate / 100);
    }
}
