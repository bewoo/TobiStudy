package springbook.user.sqlservice;

/**
 * Created by Woo on 2015-04-13.
 */
public interface SqlService {
    String getSql(String key) throws SqlRetrievalFailureException;
}
