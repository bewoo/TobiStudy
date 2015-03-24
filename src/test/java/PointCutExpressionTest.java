import learning.pointcut.Target;
import org.junit.Test;

/**
 * Created by Woo on 2015-03-24.
 */
public class PointCutExpressionTest {

    @Test
    public void showSignature() throws Exception {
        System.out.println(Target.class.getMethod("minus", int.class, int.class));
    }
}
