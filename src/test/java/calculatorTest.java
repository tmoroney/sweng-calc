import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class calculatorTest {

    @Test
    public void testInfix() {
        calculator calc = new calculator();
        assertEquals(5, calc.infixCalculator("2 + 3"));
        assertEquals(7, calc.infixCalculator("1 + 6"));
        assertEquals(51, calc.infixCalculator("20 + 31"));
        assertEquals(142, calc.infixCalculator("123 + 19"));
        assertEquals(-3, calc.infixCalculator("-5 + 2"));
        assertEquals(-3, calc.infixCalculator("2 + -5"));
        assertEquals(2, calc.infixCalculator("5 - 3"));
        assertEquals(13, calc.infixCalculator("8 - -5"));
        assertEquals(-30, calc.infixCalculator("-15 * 2"));
        assertEquals(-60, calc.infixCalculator("3 * -20"));
    }

    @Test
    public void testPostfix() {
        calculator calc = new calculator();
        assertEquals(5, calc.postfixCalculator("2 3 +"));
        assertEquals(7, calc.postfixCalculator("1 6 +"));
        assertEquals(51, calc.postfixCalculator("20 31 +"));
        assertEquals(142, calc.postfixCalculator("123 19 +"));
        assertEquals(-3, calc.infixCalculator("-5 2 +"));
        assertEquals(-3, calc.infixCalculator("2 -5 +"));
        assertEquals(2, calc.infixCalculator("5 3 -"));
        assertEquals(13, calc.infixCalculator("8 -5 -"));
        assertEquals(-30, calc.infixCalculator("-15 2 *"));
        assertEquals(-60, calc.infixCalculator("3 * -20"));
    }

}
