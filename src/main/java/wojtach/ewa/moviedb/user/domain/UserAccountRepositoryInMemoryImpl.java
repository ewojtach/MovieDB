package wojtach.ewa.moviedb.user.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ewa on 12.04.2017.
 */
// @Repository("userAccountRepository")
public class UserAccountRepositoryInMemoryImpl implements UserAccountRepository {

    @Override
    public List<UserAccount> findAll() {
        return null;
    }
}
