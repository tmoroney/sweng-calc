import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;

public class calculatorTest {

    @Test
    @DisplayName("Test Infix")
    public void testInfix() {
        calculator calc = new calculator();
        assertEquals(5, calc.infixCalculator("2 + 3"));
        assertEquals(7, calc.infixCalculator("1 + 6"));
        assertEquals(51, calc.infixCalculator("20 + 31"));
        assertEquals(142, calc.infixCalculator("123 + 19"));
    }

}
