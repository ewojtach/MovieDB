package wojtach.ewa.moviedb.user.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ewa on 12.04.2017.
 */
// @Repository("userAccountRepository")
public class InMemoryUserAccountRepositoryImpl implements UserAccountRepository {

    private Map<String, UserAccount> userMap = new HashMap<>();

    @Override
    public List<UserAccount> findAll() {
        return null;
    }

    @Override
    public UserAccount findByName(String name) {
        return userMap.get(name);
    }

    @Override
    public UserAccount create(UserAccount userAccount) {
        userMap.put(userAccount.getName(), userAccount);
        return userAccount;
    }

    @Override
    public void delete(long userId) {
    }
}
