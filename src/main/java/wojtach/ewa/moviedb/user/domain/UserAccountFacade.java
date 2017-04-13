package wojtach.ewa.moviedb.user.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ewojtach on 07/04/2017.
 */
// @Service("userAccountFacade")
public class UserAccountFacade {

    private final UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountFacade(UserAccountRepository userAccountRepository){
        this.userAccountRepository = userAccountRepository;
    }

    public UserAccountDto createUserAccount(UserAccountDto user) {
        return convertToDto(userAccountRepository.create(convertToEntity(user)));
    }

    public UserAccountDto getUserAccountByName(String name) {
        return convertToDto(userAccountRepository.findByName(name));
    }

    public List<UserAccountDto> getAllUserAccounts() {
        return userAccountRepository.findAll().stream().map(entity -> convertToDto(entity)).collect(Collectors.toList());
    }

    public void removeUserAccount(UserAccountDto user) {
        userAccountRepository.delete(user.getId());
    }


    private UserAccountDto convertToDto(UserAccount userAccount){
        return UserAccountDto.builder().name(userAccount.getName()).password(null).id(userAccount.getId()).build();
    }

    private UserAccount convertToEntity(UserAccountDto userAccountDto){
        return UserAccount.builder()
                .name(userAccountDto.getName())
                .password(userAccountDto.getPassword())
                .id(userAccountDto.getId()).build();
    }
}
