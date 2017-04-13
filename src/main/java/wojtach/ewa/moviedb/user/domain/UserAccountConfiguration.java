package wojtach.ewa.moviedb.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ewojtach on 07/04/2017.
 */
@Configuration
class UserAccountConfiguration {

    @Bean(name="userAccountFacade")
    public UserAccountFacade userAccountFacade() {

        return new UserAccountFacade(userAccountRepository());
    }

    @Bean (name="userAccountRepository")
    public UserAccountRepository userAccountRepository(){
        return new InMemoryUserAccountRepositoryImpl();
    }
}
