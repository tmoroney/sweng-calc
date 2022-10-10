import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;

public class calculatorTest {
    public calculatorTest() {

    }

    @Test
    @DisplayName("Test the answer to everything")
    public void testCalc() {
        calculator calc = new calculator();
        assertEquals(3, calc.infixCalculator("2 + 3"));
        assertEquals(7, calc.infixCalculator("1 + 6"));
    }

}
