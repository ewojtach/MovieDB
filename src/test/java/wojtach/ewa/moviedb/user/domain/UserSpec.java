package wojtach.ewa.moviedb.user.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.given;


import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by ewojtach on 11/04/2017.
 */
public class UserSpec {

    @Mock
    private UserAccountRepository userAccountRepository;

    UserAccountFacade facade = new UserAccountConfiguration().userAccountFacade();

    UserAccountDto ewa = createUserAccountDto("ewa","test");

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        facade.setUserAccountRepository(this.userAccountRepository);
        System.out.println("user acc: "+this.userAccountRepository);

        given(this.userAccountRepository.findByName(ewa.getName()))
                .willReturn(UserAccount.builder().id(1).name("ewa").password("test").build());


    }

    @Test
    public void shouldAddUser() {
        // when: user creates account

        UserAccountDto user = facade.createUserAccount(ewa);

        //then: system has this user
        assertNotNull(facade.getUserAccountByName(ewa.getName()));

    }

    @Test
    public void shouldNotReturnPassword() {
        // when: user creates account
        UserAccountDto user = facade.createUserAccount(ewa);

        //then: system returns this user with empty password
        assertNull(facade.getUserAccountByName(ewa.getName()).getPassword());

    }

    private UserAccountDto createUserAccountDto(String name, String password) {
        return UserAccountDto.builder().name(name).password(password).build();
    }

}