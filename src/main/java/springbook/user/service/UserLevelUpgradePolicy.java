package springbook.user.service;

import springbook.user.domain.User;

/**
 * Created by Woo on 2015-02-13.
 */
public interface UserLevelUpgradePolicy {
    boolean canUpgradeLevel(User user);
    void upgradeLevel(User user);
}
