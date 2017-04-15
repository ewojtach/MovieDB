package wojtach.ewa.moviedb.user.domain;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ewa on 15.04.2017.
 */

@RestController
@AllArgsConstructor
class UserCommandController {
    private UserAccountFacade userAccountFacade;

    @PutMapping("user")
    @ResponseStatus(code = HttpStatus.CREATED)
    UserAccountDto registerUser(@RequestBody UserAccountDto user){
        return userAccountFacade.createUserAccount(user);
    }

    @DeleteMapping("user/{userName}")
    @ResponseStatus(code = HttpStatus.OK)
    void deleteUser(@PathVariable("userName") String userName){
        userAccountFacade.removeUserAccount(userName);
    }

}
