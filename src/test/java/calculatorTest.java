import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class calculatorTest {

    calculator calc;

    @Test
    public void testCalc() {
        assertEquals(5, calc.infixCalculator("2 + 3"));
    }

}
