import org.junit.Test;
import proxy.Hello;
import proxy.HelloTarget;
import proxy.UppercaseHandler;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Woo on 2015-03-09.
 */
public class ReflectionTest {

    @Test
    public void invokeMethod() throws Exception {
        String name = "Spring";

        //length
        assertThat(name.length(),is(6));

        Method lengthMethod = name.getClass().getMethod("length");
        assertThat((Integer)lengthMethod.invoke(name),is(6));

        //charAt
        assertThat(name.charAt(0) , is('S'));

        Method charAtMethod = String.class.getMethod("charAt", int.class);
        assertThat((Character)charAtMethod.invoke(name, 0),is('S'));
    }

    @Test
    public void simpleProxy() {
        Hello proxiedHello = (Hello) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{Hello.class}, new UppercaseHandler(new HelloTarget()));
        assertThat(proxiedHello.sayHello("Toby"), is("HELLO TOBY"));
        assertThat(proxiedHello.sayHi("Toby"), is("HI TOBY"));
        assertThat(proxiedHello.sayThankyou("Toby"), is("THANK YOU TOBY"));

    }
}
