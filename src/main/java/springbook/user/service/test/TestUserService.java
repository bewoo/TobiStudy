package springbook.user.service.test;

import springbook.user.domain.User;
import springbook.user.service.UserServiceImpl;

import java.util.List;

public class TestUserService extends UserServiceImpl {
    private String id = "sywoo";

    @Override
    protected void upgradeLevel(User user) {
        if (user.getId().equals(this.id)) throw new TestUserServiceException();
        super.upgradeLevel(user);
    }

    @Override
    public List<User> getAll() {
        for(User user : super.getAll()) {
            super.update(user);
        }
        return null;
    }
}