import org.junit.Test;
import template.Calculator;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

/**
 * Created by Woo on 2015-01-22.
 */
public class CalcSumTest {

    @Test
    public void sumOfNumbers() throws IOException {
        Calculator calculator = new Calculator();
        int sum = calculator.calcSum(getClass().getResource("numbers.txt").getPath());
        assertThat(sum,is(55));
    }
}
