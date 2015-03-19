package learning.proxy;

/**
 * Created by Woo on 2015-03-09.
 */
public class HelloTarget implements Hello{

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

    @Override
    public String sayHi(String name) {
        return "Hi " + name;
    }

    @Override
    public String sayThankyou(String name) {
        return "Thank You " + name;
    }

}
