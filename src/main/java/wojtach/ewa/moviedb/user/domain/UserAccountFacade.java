package wojtach.ewa.moviedb.user.domain;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by ewojtach on 07/04/2017.
 */
// @Service("userAccountFacade")
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

    public UserAccountDto createUserAccount(UserAccountDto user) {

        //check if user with same login does not exist!
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userExists(user)) throw new UserAlreadyRegisteredException();

        return convertToDto(userAccountRepository.save(convertToEntity(user)));
    }

    public UserAccountDto getUserAccountByName(String name) {
        return convertToDto(userAccountRepository.findByName(name));
    }

    public UserAccountDto getUserAccountByNameWithPassword(String name) {
        return convertToDtoWithPassword(userAccountRepository.findByName(name));
    }

    public List<UserAccountDto> getAllUserAccounts() {

        return StreamSupport.stream(userAccountRepository.findAll().spliterator(), false)
                .map(entity -> convertToDto(entity)).collect(Collectors.toList());
    }

    public void removeUserAccount(String userName) {

        UserAccount user = userAccountRepository.findByName(userName);
        if (user == null) throw new UserNotFoundException();

        userAccountRepository.delete(user.getId());
    }


    private UserAccountDto convertToDto(UserAccount userAccount){
        return UserAccountDto.builder().name(userAccount.getName()).password(null).id(userAccount.getId()).build();
    }

    private UserAccountDto convertToDtoWithPassword(UserAccount userAccount){
        return UserAccountDto.builder().name(userAccount.getName()).password(userAccount.getPassword()).id(userAccount.getId()).build();
    }

    private UserAccount convertToEntity(UserAccountDto userAccountDto){
        return UserAccount.builder()
                .name(userAccountDto.getName())
                .password(userAccountDto.getPassword())
                .id(userAccountDto.getId()).build();
    }

    private boolean userExists(UserAccountDto user) {
        return userAccountRepository.findByName(user.getName()) != null;
    }
}
