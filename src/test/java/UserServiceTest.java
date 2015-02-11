import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springbook.user.dao.UserDao;
import springbook.user.domain.Level;
import springbook.user.domain.User;
import springbook.user.service.UserService;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by Woo on 2015-02-11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserServiceTest {

    @Resource(name="userService") UserService userService;
    @Resource(name="userDao") UserDao userDao;

    List<User> users;

    @Before
    public void setUp() {
        users = Arrays.asList(
                new User("bewoo","우병은","1234", Level.BASIC, 49, 0),
                new User("arlee","이아람","2345",Level.BASIC, 50, 0),
                new User("mslee","이명선","3456",Level.SILVER, 60, 29),
                new User("sywoo","우세연","4567",Level.SILVER, 60, 30),
                new User("jkwoo","우정규","5678",Level.GOLD, 100, 100));
    }

    @Test
    public void upgradeLevels() {
        userDao.deleteAll();
        for(User user : users) userDao.add(user);

        userService.upgradeLevels();

        checkLevel(users.get(0), Level.BASIC);
        checkLevel(users.get(1), Level.SILVER);
        checkLevel(users.get(2), Level.SILVER);
        checkLevel(users.get(3), Level.GOLD);
        checkLevel(users.get(4), Level.GOLD);
    }

    @Test
    public void add() {
        userDao.deleteAll();

        User userWithLevel = users.get(4);
        User userWithoutLevel = users.get(0);

        userService.add(userWithLevel);
        userService.add(userWithoutLevel);

        User userWithLevelRead = userDao.get(userWithLevel.getId());
        User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());

        assertThat(userWithLevel.getLevel(), is(userWithLevelRead.getLevel()));
        assertThat(userWithoutLevel.getLevel(), is(Level.BASIC));

    }

    private void checkLevel(User user, Level expectedLevel) {
        User userUpgrade = userDao.get(user.getId());
        assertThat(userUpgrade.getLevel(), is(expectedLevel));
    }
}
