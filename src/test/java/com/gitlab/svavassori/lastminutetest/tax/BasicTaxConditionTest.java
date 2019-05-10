
package com.gitlab.svavassori.lastminutetest.tax;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.gitlab.svavassori.lastminutetest.Category;
import com.gitlab.svavassori.lastminutetest.Item;
import com.gitlab.svavassori.lastminutetest.tax.BasicTaxCondition;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

/**
 *
 * @author sergio
 */
class BasicTaxConditionTest {
    
    
    @ParameterizedTest
    @EnumSource(value = Category.class, names = { "FOOD", "BOOKS", "MEDICAL" })
    void shouldNotTaxFoodBooksAndMedicalGoods(Category category) {
        
        Item item = mock(Item.class);
        given(item.getCategory()).willReturn(category);
        
        BasicTaxCondition condition = new BasicTaxCondition();
        
        then(condition.test(item)).isFalse();
    }
    
    @Test
    void shouldTaxOtherGoods() {
        
        Item item = mock(Item.class);
        given(item.getCategory()).willReturn(Category.OTHER);
        
        BasicTaxCondition condition = new BasicTaxCondition();
        
        then(condition.test(item)).isTrue();
    }
}
