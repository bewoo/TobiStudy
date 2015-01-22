package template;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Woo on 2015-01-22.
 */
public interface BufferedReaderCallBack {
    Integer doSomethingWithReader(BufferedReader br) throws IOException;
}
