package springbook.user.service;

import springbook.user.domain.User;

/**
 * Created by Woo on 2015-02-25.
 */
public interface UserService {
    void add(User user);
    void upgradeLevels();
}
