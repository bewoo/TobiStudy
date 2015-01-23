package template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Woo on 2015-01-22.
 */
public class Calculator {
    public Integer calcSum(String filepath) throws IOException {
        LineCallback2<Integer> callback = new LineCallback2<Integer>() {
            @Override
            public Integer doSomethingWithLine(String line, Integer value) {
                return value+Integer.valueOf(line);
            }
        };
        return lineReadTemplate(filepath, callback, 0);
    }

    public Integer calcMultiply(String filepath) throws IOException {
        LineCallback2<Integer> callback = new LineCallback2<Integer>() {
            @Override
            public Integer doSomethingWithLine(String line, Integer value) {
                return value*Integer.valueOf(line);
            }
        };
        return lineReadTemplate(filepath, callback, 1);
    }

    public String concatenate(String filepath) throws IOException {
        LineCallback2<String> concatenateCallback = new LineCallback2<String>() {
            @Override
            public String doSomethingWithLine(String line, String value) {
                return value + line;
            }
        };
        return lineReadTemplate(filepath, concatenateCallback, "");
    }

    public <T> T lineReadTemplate(String filepath, LineCallback2<T> callback, T initVal) throws IOException{
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filepath));
            T res = initVal;
            String line = null;
            while((line=br.readLine()) != null) {
                res = callback.doSomethingWithLine(line, res);
            }
            return res;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if(br != null) {
                try { br.close(); }
                catch (IOException e) { e.printStackTrace(); }
            }
        }
    }
}
