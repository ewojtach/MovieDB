package wojtach.ewa.moviedb.user.domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ewojtach on 11/04/2017.
 */
public class UserSpec {

    UserAccountFacade facade = new UserAccountConfiguration().userAccountFacade();
    UserAccountDto ewa = createUserAccountDto("ewa","test");
    

    @Test
    public void shouldAddUser() {
        // when: user creates account
        String userId = facade.createUserAccount(ewa);

        //then: system has this user
        assertEquals(userId, facade.getUserAccountById(ewa.getName()));
    }



    private UserAccountDto createUserAccountDto(String name, String password) {
        return UserAccountDto.builder().name(name).password(password).build();
    }

}