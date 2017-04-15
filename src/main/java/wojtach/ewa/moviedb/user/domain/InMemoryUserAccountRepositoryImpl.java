package wojtach.ewa.moviedb.user.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ewa on 12.04.2017.
 */

// @Repository("userAccountRepository")
class InMemoryUserAccountRepositoryImpl {

    private Map<String, UserAccount> userMap = new HashMap<>();

    public List<UserAccount> findAll() {
        return null;
    }

    public UserAccount findByName(String name) {
        return userMap.get(name);
    }

    public UserAccount save(UserAccount userAccount) {
        System.out.println("in memory save");
        userMap.put(userAccount.getName(), userAccount);
        return userAccount;
    }

    public void delete(long userId) {
    }
}
