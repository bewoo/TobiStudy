package springbook.user.sqlservice;

/**
 * Created by Woo on 2015-04-13.
 */
public class SqlRetrievalFailureException extends RuntimeException {
    public SqlRetrievalFailureException(String message) {
        super(message);
    }

    public SqlRetrievalFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqlRetrievalFailureException(Exception e) {

    }
}
