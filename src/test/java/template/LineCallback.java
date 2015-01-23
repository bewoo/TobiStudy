package template;

/**
 * Created by Woo on 2015-01-24.
 */
public interface LineCallback<T> {
    T doSomethingWithLine(String line, T value);
}