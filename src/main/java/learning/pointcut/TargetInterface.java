package learning.pointcut;

/**
 * Created by Woo on 2015-03-24.
 */
public interface TargetInterface {
    void hello();
    void hello(String a);
    int minus(int a, int b) throws RuntimeException;
    int plus(int a, int b);
}
