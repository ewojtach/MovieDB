package wojtach.ewa.moviedb.user.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ewojtach on 07/04/2017.
 */
@Configuration
@ComponentScan({"wojtach.ewa.moviedb.user.domain"})
class UserAccountConfiguration {


    @Bean(name="userAccountFacade")
    public UserAccountFacade userAccountFacade() {
        return new UserAccountFacade();
    }

}
