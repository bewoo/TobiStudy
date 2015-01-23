import org.junit.Before;
import org.junit.Test;
import template.Calculator;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

/**
 * Created by Woo on 2015-01-22.
 */
public class CalcSumTest {

    Calculator calculator = null;
    String filePath = null;

    @Before
    public void setUp() {
        calculator = new Calculator();
        filePath = getClass().getResource("numbers.txt").getPath();
    }

    @Test
    public void sumOfNumbers() throws IOException {
        int sum = calculator.calcSum(filePath);
        assertThat(sum,is(15));
    }

    @Test
    public void multiplyOfNumbers() throws IOException {
        int result = calculator.calcMultiply(filePath);
        assertThat(result,is(120));
    }

    @Test
    public void concatenateStrings() throws IOException {
        assertThat(calculator.concatenate(filePath) , is("12345"));
    }
}
