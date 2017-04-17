package wojtach.ewa.moviedb.user.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by ewojtach on 07/04/2017.
 */
public class UserAccountFacade {

    private UserAccountRepository userAccountRepository;

    private PasswordEncoder passwordEncoder;


    @Autowired
    public void setPasswordEncoder (PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserAccountRepository(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public UserAccountFacade(){
    }

    public UserAccountFacade(UserAccountRepository userAccountRepository){
        this.userAccountRepository = userAccountRepository;
    }

    public UserAccountDto createUserAccount(UserAccountDto user) throws UserAlreadyRegisteredException {

        //check if user with same login does not exist!
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userExists(user)) {
            throw new UserAlreadyRegisteredException();
        }

        return convertToDto(userAccountRepository.save(convertToEntity(user)));
    }

    public UserAccountDto getUserAccountByName(final String name) {
        return convertToDto(userAccountRepository.findByName(name));
    }

    public UserAccountDto getUserAccountByNameWithPassword(final String name) {
        return convertToDtoWithPassword(userAccountRepository.findByName(name));
    }

    public List<UserAccountDto> getAllUserAccounts() {

        return StreamSupport.stream(userAccountRepository.findAll().spliterator(), false)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void removeUserAccount(final String userName) throws UserNotFoundException {

       final UserAccount user  = Optional.ofNullable(userAccountRepository.findByName(userName))
                .orElseThrow(()->new UserNotFoundException());

       userAccountRepository.delete(user.getId());
    }


    private UserAccountDto convertToDto(final UserAccount userAccount){
        return UserAccountDto.builder()
                .name(userAccount.getName())
                .password(null)
                .id(userAccount.getId())
                .build();
    }

    private UserAccountDto convertToDtoWithPassword(final UserAccount userAccount){
        return UserAccountDto.builder()
                .name(userAccount.getName())
                .password(userAccount.getPassword())
                .id(userAccount.getId())
                .build();
    }

    private UserAccount convertToEntity(final UserAccountDto userAccountDto){
        return UserAccount.builder()
                .name(userAccountDto.getName())
                .password(userAccountDto.getPassword())
                .id(userAccountDto.getId()).build();
    }

    private boolean userExists(final UserAccountDto user) {
        return userAccountRepository.findByName(user.getName()) != null;
    }
}
