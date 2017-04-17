package wojtach.ewa.moviedb.user.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by ewojtach on 07/04/2017.
 */
@Configuration
@ComponentScan({"wojtach.ewa.moviedb.user.domain"})
class UserAccountConfiguration {


    @Bean
    public UserAccountFacade userAccountFacade() {
        return new UserAccountFacade();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
