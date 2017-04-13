package wojtach.ewa.moviedb.user.domain;

import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by ewa on 11.04.2017.
 */
public interface UserAccountRepository {

    List<UserAccount> findAll();

    UserAccount findByName(String name);

    UserAccount create(UserAccount userAccount);

    void delete(long userId);

}
