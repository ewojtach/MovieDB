package wojtach.ewa.moviedb.user.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ewa on 12.04.2017.
 */
public class InMemoryUserAccountRepositoryImpl implements UserAccountRepository{

    public List<UserAccount> findAll() {
        List<UserAccount> userList = new ArrayList<>();

        UserAccount account = new UserAccount();
        userList.add(account);

        return userList;
    }
}
