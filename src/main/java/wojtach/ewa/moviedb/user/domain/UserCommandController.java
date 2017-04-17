package wojtach.ewa.moviedb.user.domain;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by ewa on 15.04.2017.
 */

@RestController
@AllArgsConstructor
class UserCommandController {
    private UserAccountFacade userAccountFacade;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @PutMapping("api/user")
    @ResponseStatus(code = HttpStatus.CREATED)
    ResponseEntity<UserAccountDto> registerUser(@RequestBody UserAccountDto user){

        try {
            final UserAccountDto response = userAccountFacade.createUserAccount(user);
            return new ResponseEntity<UserAccountDto>(response, HttpStatus.CREATED);
        } catch (UserAlreadyRegisteredException e) {
            log.error("user already exist: "+user.getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("api/user/{userName}")
    @ResponseStatus(code = HttpStatus.OK)
    ResponseEntity<UserAccountDto> deleteUser(@PathVariable("userName") String userName){

        try {
            userAccountFacade.removeUserAccount(userName);
            return ResponseEntity.ok(null);
        } catch (UserNotFoundException e) {
            log.error("user account not found: "+userName);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
