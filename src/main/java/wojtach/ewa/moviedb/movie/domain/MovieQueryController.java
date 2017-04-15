package wojtach.ewa.moviedb.user.domain;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ewa on 15.04.2017.
 */

@RestController
@AllArgsConstructor
class UserQueryController {
    private UserAccountFacade userAccountFacade;

    @GetMapping("users")
    List<UserAccountDto> getUserAccounts(){
        return userAccountFacade.getAllUserAccounts();
    }

    @GetMapping("user/{name}")
    UserAccountDto getUserAccount(@PathVariable String name){
        return userAccountFacade.getUserAccountByName(name);
    }
}
