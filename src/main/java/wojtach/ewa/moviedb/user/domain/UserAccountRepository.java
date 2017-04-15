package wojtach.ewa.moviedb.user.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by ewa on 11.04.2017.
 */
@org.springframework.stereotype.Repository ("userAccountRepository")
interface UserAccountRepository extends CrudRepository<UserAccount, Long>{

    UserAccount findByName(String name);

}
