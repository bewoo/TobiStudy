package springbook.user.dao;

import springbook.user.domain.User;

import java.util.List;

/**
 * Created by Woo on 2015-02-07.
 */
public interface UserDao {
    void add(User user);
    User get(String id);
    List<User> getAll();
    void deleteAll();
    int getCount();
    void update(User user);
}
