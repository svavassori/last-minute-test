
package tax;

import static org.assertj.core.api.BDDAssertions.then;

import com.gitlab.svavassori.lastminutetest.Category;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 *
 * @author sergio
 */
class CategoryTest {
    
    @ParameterizedTest
    @MethodSource("createCategories")
    void shouldReturnAssociatedCategory(String line, Category expected) {
        
        then(Category.findCategory(line)).isEqualTo(expected);
    }
    
    static Stream<Arguments> createCategories() {
        
        return Stream.of(Arguments.of("chocolate boxes", Category.FOOD),
                         Arguments.of("imported books", Category.BOOKS),
                         Arguments.of("headache pills", Category.MEDICAL),
                         Arguments.of("perfumes", Category.OTHER)
                );
    }
}
