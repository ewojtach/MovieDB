package wojtach.ewa.moviedb.user.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ewojtach on 07/04/2017.
 */
// @Service("userAccountFacade")
public class UserAccountFacade {

    private final UserAccountRepository userAccountRepository;

//    public UserAccountFacade(){
//        userAccountRepository = new InMemoryUserAccountRepositoryImpl();
//    }

    @Autowired
    public UserAccountFacade(UserAccountRepository userAccountRepository){
        this.userAccountRepository = userAccountRepository;
    }

    public String createUserAccount(UserAccountDto ewa) {
        return null;
    }

    public UserAccountDto getUserAccountById(Object name) {
        userAccountRepository.findAll();
        return null;
    }

    public void removeUserAccount(UserAccountDto ewa) {
    }
}
