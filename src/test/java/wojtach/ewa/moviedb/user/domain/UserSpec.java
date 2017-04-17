package wojtach.ewa.moviedb.user.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.BDDMockito.given;


import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by ewojtach on 11/04/2017.
 */
public class UserSpec {

    @Mock
    private UserAccountRepository userAccountRepository;

    @Mock
    private PasswordEncoder passwordEncoder;


    UserAccountFacade facade = new UserAccountConfiguration().userAccountFacade();

    UserAccountDto ewa = createUserAccountDto("ewa","test");
    UserAccountDto lukasz = createUserAccountDto("lukasz","test");

    UserAccount testAccount = createUserAccount("ewa","test");

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        facade.setUserAccountRepository(this.userAccountRepository);
        facade.setPasswordEncoder(this.passwordEncoder);

        given(this.userAccountRepository.save(any(UserAccount.class)))
                .willReturn(testAccount);

        given(this.passwordEncoder.encode(any(CharSequence.class)))
                .willReturn("hashedval");

    }

    @Test
    public void shouldAddUser() throws UserAlreadyRegisteredException {
        // when: user creates account

        UserAccountDto user = facade.createUserAccount(ewa);

        given(this.userAccountRepository.findByName(ewa.getName()))
                .willReturn(UserAccount.builder().id(1).name("ewa").password("test").build());


        //then: system has this user
        assertNotNull(facade.getUserAccountByName(ewa.getName()));

    }

    @Test
    public void shouldNotReturnPassword() throws UserAlreadyRegisteredException {
        // when: user creates account
        UserAccountDto user = facade.createUserAccount(ewa);

        given(this.userAccountRepository.findByName(ewa.getName()))
                .willReturn(UserAccount.builder().id(1).name("ewa").password("test").build());


        //then: system returns this user with empty password
        assertNull(facade.getUserAccountByName(ewa.getName()).getPassword());

    }

    @Test (expected=UserAlreadyRegisteredException.class)
    public void shouldNotAllowToAddTwoUsersWithSameLogin() throws UserAlreadyRegisteredException {
        // when: user creates account

        UserAccountDto user = facade.createUserAccount(ewa);

        given(this.userAccountRepository.findByName(ewa.getName()))
                .willReturn(UserAccount.builder().id(1).name("ewa").password("test").build());


        UserAccountDto sameUser = facade.createUserAccount(ewa);
    }

    @Test
    public void shouldRemoveUser() throws UserAlreadyRegisteredException, UserNotFoundException {
        // when: user creates account
        UserAccountDto user = facade.createUserAccount(lukasz);

        given(this.userAccountRepository.findByName(lukasz.getName()))
                .willReturn(UserAccount.builder().id(1).name("lukasz").password("test").build());

        //then: system has this user
        assertNotNull(facade.getUserAccountByName(lukasz.getName()));

        // when: user removes account
        facade.removeUserAccount(lukasz.getName());

    }

    @Test (expected=UserNotFoundException.class)
    public void shouldNotAllowToRemoveUnregisteredUser() throws UserNotFoundException {
        // when: user removes account

        facade.removeUserAccount("xxx");
    }

    private UserAccountDto createUserAccountDto(String name, String password) {
        return UserAccountDto.builder().name(name).password(password).build();
    }

    private UserAccount createUserAccount(String name, String password) {
        return UserAccount.builder().name(name).password(password).id(1).build();
    }

}